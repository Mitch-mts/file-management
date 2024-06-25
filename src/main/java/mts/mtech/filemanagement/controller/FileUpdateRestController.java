package mts.mtech.filemanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import mts.mtech.filemanagement.dto.FileDto;
import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.service.update.FileUpdateRequest;
import mts.mtech.filemanagement.service.update.FileUpdateService;
import mts.mtech.filemanagement.service.update.InvalidFileUpdateRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

/**
 * @author Mitch
 *
 */
@RestController
@RequestMapping("/v1/files")
@Tag(name = "File Update Apis", description = "Apis for deleting files")
@CrossOrigin
@Slf4j
public class FileUpdateRestController {
    private final FileUpdateService fileUpdateService;

    @Autowired
    public FileUpdateRestController(FileUpdateService fileUpdateService) {
        this.fileUpdateService = fileUpdateService;
    }

    @PutMapping(value = "/{fileId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto updateFile(@RequestParam("file") MultipartFile file, @PathVariable("fileId")Long fileId) {
        log.info("Uploading file: {} ", file);
        FileUpdateRequest fileUpdateRequest = FileUpdateRequest.createFileUpdateRequest(fileId, file);
        File savedFilee = fileUpdateService.updateFile(fileUpdateRequest);
        return FileDto.of(savedFilee);
    }

    @PutMapping(value = "/file-id/{fileId}/user-id/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public FileDto updateUserFile(@RequestParam("file") MultipartFile file,
                                  @PathVariable("userId") Long userId,
                                  @PathVariable("fileId") Long fileId) {
        log.info("Uploading file: {} ", file);
        log.info("Uploading userid: {} ", userId);
        log.info("Uploading fileid: {} ", fileId);

        FileUpdateRequest fileUpdateRequest = FileUpdateRequest.createFileUpdateRequest(fileId, file);
        File savedFile = fileUpdateService.updateUserFile(fileUpdateRequest, userId);
        return FileDto.of(savedFile);
    }


    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public mts.mtech.filemanagement.error.Error fileNotFound(FileNotFoundException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

    @ExceptionHandler(InvalidFileUpdateRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public mts.mtech.filemanagement.error.Error invalidFileUpdateRequestException(InvalidFileUpdateRequestException e) {
        return new mts.mtech.filemanagement.error.Error(4,e.getMessage());
    }

}
