package mts.mtech.filemanagement.service.store;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Mitch
 *
 */
public interface FileStorageService {
    String storeFile(MultipartFile file);
    Resource downloadFile(String fileName);
    Boolean deleteFile(String fileName);

}
