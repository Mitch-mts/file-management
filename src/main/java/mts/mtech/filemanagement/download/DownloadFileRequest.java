package mts.mtech.filemanagement.download;

/**
 * @author Mitch
 *
 */

public final class DownloadFileRequest {
    private final Long fileId;

    private DownloadFileRequest(Long fileId) {
        this.fileId = fileId;
    }

    public static DownloadFileRequest of(Long fileId) {
        return new DownloadFileRequest(fileId);
    }

    public Long getFileId() {
        return fileId;
    }

    @Override
    public String toString() {
        return "DownloadFileRequest{" +
                "fileId=" + fileId +
                '}';
    }
}
