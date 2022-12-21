package mts.mtech.filemanagement.download;

import mts.mtech.filemanagement.model.File;
import org.springframework.core.io.Resource;

import java.util.List;

/**
 * @author Mitch
 *
 */
public interface DownloadFileService {
    Resource downloadFile(DownloadFileRequest downloadFileRequest);
    Resource getFile(DownloadFileRequest downloadFileRequest);
    Resource getUserFile(Long userId);
    List<File> getFiles();
}
