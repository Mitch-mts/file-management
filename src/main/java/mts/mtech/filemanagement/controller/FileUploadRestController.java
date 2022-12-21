package mts.mtech.filemanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.filemanagement.dto.FileDto;
import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.service.store.FileStorageException;
import mts.mtech.filemanagement.service.upload.FileUploadRequest;
import mts.mtech.filemanagement.service.upload.FileUploadService;
import mts.mtech.filemanagement.service.upload.InvalidFileUploadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class FileUploadRestController {
    private static final Logger LOGGER= LoggerFactory.getLogger(FileUploadRestController.class);

    private final FileUploadService fileUploadService;

    @Autowired
    public FileUploadRestController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping
    public FileDto uploadFile(@RequestParam("file") MultipartFile file){
        LOGGER.info("Uploading file: {} ", file);

        FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file);

        File savedFilee = fileUploadService.uploadFile(fileUploadRequest);

        return FileDto.of(savedFilee);
    }

    @PostMapping(value = "/user-id/{userId}")
    public FileDto uploadUserFile(@RequestParam("file") MultipartFile file, @PathVariable("userId")Long userId){
        LOGGER.info("Uploading file: {} ", file);

        FileUploadRequest fileUploadRequest = FileUploadRequest.createFileUploadRequest(file);

        File savedFilee = fileUploadService.uploadUserFile(fileUploadRequest, userId);

        return FileDto.of(savedFilee);
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

