package mts.mtech.filemanagement.service.store;

import lombok.SneakyThrows;
import mts.mtech.filemanagement.service.configuration.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Mitch
 *
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final FileStorageProperties fileStorageProperties;

    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        checkIfFileNameContainsInvalidCharacters(fileName);
        fileName = generateUniqueFileName(fileName);
        Path targetLocation = getFileStorageLocation().resolve(fileName);
        createDirectoriesIfThereDoNotExist(targetLocation);
        persistFile(file, targetLocation);
        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {
        try {
            Path filePath = this.getFileStorageLocation().resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

    @Override
    @SneakyThrows
    /**
     * @param fileName The name of the file to be deleted
     *
     * @return {@code true} if the file was deleted and {@code false} if the file was not found
     */
    public Boolean deleteFile(String fileName) {
        Path filePath = getFileStorageLocation().resolve(fileName).normalize();
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new FileStorageException("Failed to delete file : " + e.getMessage());
        }
    }

    private void persistFile(MultipartFile file, Path path) {

        try {

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getName() + ". Please try again!");
        }

    }
    private String generateUniqueFileName(String fileName){
        return fileName;
    }


    private void checkIfFileNameContainsInvalidCharacters(String fileName) {
        if (fileName.contains("..")) {
            throw new FileStorageException("Filename contains invalid path sequence " + fileName);
        }
    }


    private Path getFileStorageLocation() {
        return Paths.get(this.fileStorageProperties.getUploadRoute())
                .toAbsolutePath().normalize();
    }

    private void createDirectoriesIfThereDoNotExist(Path fileStorageLocation) {
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored." + ex);
        }

    }


}
