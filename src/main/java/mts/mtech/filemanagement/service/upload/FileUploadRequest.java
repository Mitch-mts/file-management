package mts.mtech.filemanagement.service.upload;

import org.springframework.web.multipart.MultipartFile;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Mitch
 *
 */
public class FileUploadRequest {
    private final MultipartFile file;

    private FileUploadRequest(MultipartFile file) {
        this.file = file;
    }

    public static FileUploadRequest of(MultipartFile file) {
        checkNotNull(file,"Filee is required");
        return new FileUploadRequest(file);
    }

    public MultipartFile getFile() {
        return file;
    }


    @Override
    public String toString() {
        return "FileUploadRequest{" +
                "file=" + file +
                '}';
    }
}
