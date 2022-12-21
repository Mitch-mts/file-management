package mts.mtech.filemanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.filemanagement.download.DownloadFileRequest;
import mts.mtech.filemanagement.download.DownloadFileService;
import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.service.store.FileStorageException;
import mts.mtech.filemanagement.utils.Constants;
import mts.mtech.filemanagement.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author Mitch
 *
 */

@RestController
@RequestMapping("/v1/files")
@Tag(name = "Download files Api", description = "Apis for downloading files")
public class DownloadFileRestController {
    private final Logger log = LoggerFactory.getLogger(DownloadFileRestController.class);
    private final DownloadFileService downloadFileService;

    public DownloadFileRestController(DownloadFileService downloadFileService) {
        this.downloadFileService = downloadFileService;
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId, HttpServletRequest request){
        DownloadFileRequest downloadFileRequest = DownloadFileRequest.of(fileId);
        Resource resource = downloadFileService.downloadFile(downloadFileRequest);
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Resource> downloadUserFile(@PathVariable("userId") Long userId, HttpServletRequest request){
        Resource resource = downloadFileService.getUserFile(userId);
        String contentTypee = "";

        try{
            contentTypee = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentTypee == null) {
            contentTypee = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentTypee))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping("/view/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable("fileId") Long fileId, HttpServletRequest request){
        DownloadFileRequest downloadFileRequest = DownloadFileRequest.of(fileId);
        Resource resource = downloadFileService.getFile(downloadFileRequest);
        String contentType = "";

        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

    @GetMapping
    public Response<List<File>> getFiles(){
        List<File> fileList = downloadFileService.getFiles();
        return new Response<List<File>>().buildSuccessResponse(Constants.SUCCESS, fileList);
    }

    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public mts.mtech.filemanagement.error.Error fileStorageError(FileStorageException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public mts.mtech.filemanagement.error.Error fileNotFoundException(FileNotFoundException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

}
