package mts.mtech.filemanagement.service.update;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mitch
 *
 */
public class FileUpdateRequest {
    private  Long fileId;
    private final MultipartFile file;

    private FileUpdateRequest(Long fileId, MultipartFile file) {
        this.fileId = fileId;
        this.file = file;
    }

    public static FileUpdateRequest createFileUpdateRequest(Long fileId, MultipartFile file) {
        return new FileUpdateRequest(fileId, file);
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public MultipartFile getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "FileUpdateRequest{" +
                "fileId=" + fileId +
                ", file=" + file +
                '}';
    }
}
