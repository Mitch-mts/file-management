package mts.mtech.filemanagement.service.delete;
/**
 * @author Mitch
 *
 */

@FunctionalInterface
public interface DeleteFileService {
    DeleteFileResponse deleteFile(DeleteFileRequest deleteFileRequest);
}
