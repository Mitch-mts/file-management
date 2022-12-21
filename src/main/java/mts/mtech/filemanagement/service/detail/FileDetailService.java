package mts.mtech.filemanagement.service.detail;


import mts.mtech.filemanagement.model.File;

/**
 * @author Mitch
 *
 */
public interface FileDetailService {
    File getFile(FileDetailRequest fileDetailRequest);
    File getUserFileId(FileDetailRequest fileDetailRequest, Long userId);
}
