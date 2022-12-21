package mts.mtech.filemanagement.service.upload;


import mts.mtech.filemanagement.model.File;

/**
 * @author Mitch
 *
 */
public interface FileUploadService {
    File uploadFile(FileUploadRequest fileUploadRequest);
    File uploadUserFile(FileUploadRequest fileUploadRequest, Long userId);
}
