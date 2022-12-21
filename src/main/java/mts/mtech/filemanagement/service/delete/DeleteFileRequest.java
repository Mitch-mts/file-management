package mts.mtech.filemanagement.service.delete;
/**
 * @author Mitch
 *
 */
public class DeleteFileRequest {

    private final Long fileId;

    public DeleteFileRequest(Long fileId) {
        this.fileId = fileId;
    }

    public Long getFileId() {
        return fileId;
    }

    @Override
    public String toString() {
        return "DeleteFileRequest{" +
                "fileId=" + fileId +
                '}';
    }
}
