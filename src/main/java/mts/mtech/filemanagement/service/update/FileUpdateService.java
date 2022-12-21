package mts.mtech.filemanagement.service.update;


import mts.mtech.filemanagement.model.File;

/**
 * @author Mitch
 *
 */
public interface FileUpdateService {
    File updateFile(FileUpdateRequest fileUpdateRequest);
    File updateUserFile(FileUpdateRequest fileUpdateRequest, Long userId);
}
