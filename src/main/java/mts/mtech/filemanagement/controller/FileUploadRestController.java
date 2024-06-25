package mts.mtech.filemanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.filemanagement.dto.FileDto;
import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.service.store.FileStorageException;
import mts.mtech.filemanagement.service.upload.FileUploadRequest;
import mts.mtech.filemanagement.service.upload.FileUploadService;
import mts.mtech.filemanagement.service.upload.InvalidFileUploadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mitch
 *
 */

@RestController
@RequestMapping("/v1/files")
@Tag(name = "File upload Apis", description = "Apis for uploading files")
@CrossOrigin
@Slf4j
public class FileUploadRestController {
    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadRestController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto uploadFile(@RequestParam("file") MultipartFile file){
        log.info("Uploading file: {} ", file);
        FileUploadRequest fileUploadRequest = FileUploadRequest.of(file);
        File savedFile = fileUploadService.uploadFile(fileUploadRequest);
        return FileDto.of(savedFile);
    }

    @PostMapping(value = "/user-id/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto uploadUserFile(@RequestParam("file") MultipartFile file, @PathVariable("userId")Long userId){
        log.info("Uploading file: {} ", file);
        FileUploadRequest fileUploadRequest = FileUploadRequest.of(file);
        File savedFile = fileUploadService.uploadUserFile(fileUploadRequest, userId);
        return FileDto.of(savedFile);
    }


    @ExceptionHandler(FileStorageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public mts.mtech.filemanagement.error.Error fileStorageError(FileStorageException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

    @ExceptionHandler(InvalidFileUploadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public mts.mtech.filemanagement.error.Error invalidFileUploadRequestError(InvalidFileUploadRequestException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

}

