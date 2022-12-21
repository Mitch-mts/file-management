package mts.mtech.filemanagement.service.update;


import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.configuration.FileStorageProperties;
import mts.mtech.filemanagement.service.detail.FileDetailRequest;
import mts.mtech.filemanagement.service.detail.FileDetailService;
import mts.mtech.filemanagement.service.store.FileStorageService;
import mts.mtech.filemanagement.service.upload.InvalidFileUploadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Mitch
 *
 */
@Service
public class FileUpdateServiceImpl implements FileUpdateService {
    private final Logger logger = LoggerFactory.getLogger(FileUpdateServiceImpl.class);
    private final FileRepository fileRepository;
    private final FileDetailService fileDetailService;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties fileStorageProperties;

    public FileUpdateServiceImpl(FileRepository fileRepository,
                                 FileDetailService fileDetailService,
                                 FileStorageService fileStorageService, FileStorageProperties fileStorageProperties) {
        this.fileRepository = fileRepository;
        this.fileDetailService = fileDetailService;
        this.fileStorageService = fileStorageService;
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public File updateFile(FileUpdateRequest fileUpdateRequest) {
        Optional.ofNullable(fileUpdateRequest).orElseThrow(() -> new InvalidFileUpdateRequestException("File update request cannot be null"));
        File file = fileDetailService.getFile(FileDetailRequest.createFileDetailRequest(fileUpdateRequest.getFileId()));
        this.checkIfFileTypeIsAccepted(fileUpdateRequest.getFile());
        String fileName = fileStorageService.storeFile(fileUpdateRequest.getFile());
        file.setFileName(fileName);
        return fileRepository.save(file);
    }

    @Override
    public File updateUserFile(FileUpdateRequest fileUpdateRequest, Long userId) {
        logger.info("user id::{}", userId);
        logger.info("fileUpdateRequest::{}", fileUpdateRequest.getFileId());
        File file = fileDetailService.getUserFileId(FileDetailRequest.createFileDetailRequest(fileUpdateRequest.getFileId()), userId);
        this.checkIfFileTypeIsAccepted(fileUpdateRequest.getFile());
        String fileName = fileStorageService.storeFile(fileUpdateRequest.getFile());
        file.setFileName(fileName);
        file.setUserId(userId);
        logger.info("file::{}", file);
        return fileRepository.save(file);
    }

    private void checkIfFileTypeIsAccepted(MultipartFile file) {
        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileStorageProperties.getFileTypes().stream().filter(fileName::endsWith).findFirst()
                .orElseThrow(() -> new InvalidFileUploadRequestException("Filee type is not accepted"));
    }

}
