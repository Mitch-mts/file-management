package mts.mtech.filemanagement.service.upload;

import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.configuration.FileStorageProperties;
import mts.mtech.filemanagement.service.store.FileStorageService;
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
public class FileUploadServiceImpl implements FileUploadService {
    private final FileRepository fileRepository;
    private final FileStorageService fileStorageService;
    private final FileStorageProperties fileStorageProperties;

    public FileUploadServiceImpl(FileRepository fileRepository, FileStorageService fileStorageService, FileStorageProperties fileStorageProperties) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public File uploadFile(FileUploadRequest fileUploadRequest) {
        Optional.ofNullable(fileUploadRequest).orElseThrow(() -> new InvalidFileUploadRequestException("File upload request cannot be null"));
        this.checkIfFileTypeIsAccepted(fileUploadRequest.getFile());
        String fileName = fileStorageService.storeFile(fileUploadRequest.getFile());
        File file = File.createFile(fileName);
        return fileRepository.save(file);
    }

    @Override
    public File uploadUserFile(FileUploadRequest fileUploadRequest, Long userId) {
        Optional.ofNullable(fileUploadRequest).orElseThrow(() -> new InvalidFileUploadRequestException("File upload request cannot be null"));
        this.checkIfFileTypeIsAccepted(fileUploadRequest.getFile());
        String fileName = fileStorageService.storeFile(fileUploadRequest.getFile());
        File filee = File.createUserFile(userId, fileName);
        return fileRepository.save(filee);
    }

    private void checkIfFileTypeIsAccepted(MultipartFile file) {
        final String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        fileStorageProperties.getFileTypes().stream().filter(fileName::endsWith).findFirst()
                .orElseThrow(() -> new InvalidFileUploadRequestException("File type is not accepted"));
    }

}
