package mts.mtech.filemanagement.service.delete;

import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.service.store.FileStorageService;
import org.springframework.stereotype.Service;

/**
 * @author Mitch
 *
 */

@Service
public class DeleteFileServiceImpl implements DeleteFileService {

    private final FileRepository fileRepository;

    private final FileStorageService fileStorageService;

    public DeleteFileServiceImpl(FileRepository fileRepository,
                                 FileStorageService fileStorageService) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public DeleteFileResponse deleteFile(final DeleteFileRequest deleteFileRequest) {

        File fileRecord = fileRepository.findOneById(deleteFileRequest.getFileId())
                .orElseThrow(() -> new FileNotFoundException("File record selected was not found"));
        String fileName = fileRecord.getFileName();
        Boolean deleteResponse = fileStorageService.deleteFile(fileName);
        String message = deleteResponse ? "File deleted successfully" : "Failed to delete Filee, file does not exist";
        if (deleteResponse) fileRepository.deleteById(deleteFileRequest.getFileId());
        return new DeleteFileResponse(deleteResponse, message);
    }
}
