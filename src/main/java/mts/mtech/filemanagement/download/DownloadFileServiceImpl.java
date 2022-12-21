package mts.mtech.filemanagement.download;

import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.service.store.FileStorageService;
import mts.mtech.filemanagement.utils.Constants;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mitch
 *
 */
@Service
public class DownloadFileServiceImpl implements DownloadFileService {

    private final FileRepository fileRepository;

    private final FileStorageService fileStorageService;

    public DownloadFileServiceImpl(FileRepository fileRepository, FileStorageService fileStorageService) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Resource downloadFile(DownloadFileRequest downloadFileRequest) {
        Long fileId = downloadFileRequest.getFileId();
        File fileRecord = fileRepository.findOneById(fileId)
                                        .orElseThrow(()-> new FileNotFoundException(Constants.FILE_NOT_FOUND));
        String fileName = fileRecord.getFileName();
        return fileStorageService.downloadFile(fileName);
    }

    @Override
    public Resource getFile(DownloadFileRequest downloadFileRequest) {
        Long fileId = downloadFileRequest.getFileId();
        File fileRecord = fileRepository.findById(fileId)
                                        .orElseThrow(()-> new FileNotFoundException(Constants.FILE_NOT_FOUND));
        String fileName = fileRecord.getFileName();
        return fileStorageService.downloadFile(fileName);
    }

    @Override
    public Resource getUserFile(Long userId) {
        File fileRecord = fileRepository.findByUserId(userId)
                                        .orElseThrow(()-> new FileNotFoundException(Constants.FILE_NOT_FOUND));
        String fileName = fileRecord.getFileName();
        return fileStorageService.downloadFile(fileName);
    }

    @Override
    public List<File> getFiles() {
        return fileRepository.findAll();
    }
}
