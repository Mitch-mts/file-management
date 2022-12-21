package mts.mtech.filemanagement.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import mts.mtech.filemanagement.service.delete.DeleteFileRequest;
import mts.mtech.filemanagement.service.delete.DeleteFileResponse;
import mts.mtech.filemanagement.service.delete.DeleteFileService;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.service.store.FileStorageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mitch
 *
 */
@RestController
@RequestMapping("/v1/files")
@Tag(name = "File Delete Api", description = "Delete files Apis")
@CrossOrigin
public class DeleteFileRestController {

    private final DeleteFileService deleteFileService;

    public DeleteFileRestController(DeleteFileService deleteFileService) {
        this.deleteFileService = deleteFileService;
    }

    @DeleteMapping("/{fileId}")
    public DeleteFileResponse deleteFile(@PathVariable("fileId") Long fileId){
        DeleteFileRequest deleteFileContext = new DeleteFileRequest(fileId);
        return deleteFileService.deleteFile(deleteFileContext);
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
