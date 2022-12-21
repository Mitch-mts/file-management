package mts.mtech.filemanagement.service.detail;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Mitch
 *
 */

public class FileDetailRequest {
    private  final Long fileId;

    private FileDetailRequest(Long fileId) {
        this.fileId = fileId;
    }

    public static FileDetailRequest createFileDetailRequest(Long fileId) {
        checkNotNull(fileId,"Filee id cannot be null");
        return new FileDetailRequest(fileId);
    }

    public Long getFileId() {
        return fileId;
    }

    @Override
    public String toString() {
        return "FileDetailRequest{" +
                "fileId=" + fileId +
                '}';
    }
}
