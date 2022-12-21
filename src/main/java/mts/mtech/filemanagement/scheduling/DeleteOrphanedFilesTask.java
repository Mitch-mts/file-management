package mts.mtech.filemanagement.scheduling;

import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.store.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Fact S Musingarimi
 * 7/27/18
 * 11:40 AM
 */
@Component
public class DeleteOrphanedFilesTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteOrphanedFilesTask.class);

    private final FileStorageService fileStorageService;
    private final FileRepository fileRepository;

    @Autowired
    public DeleteOrphanedFilesTask(FileStorageService fileStorageService, FileRepository fileRepository) {
        this.fileStorageService = fileStorageService;
        this.fileRepository = fileRepository;
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void deleteOrphanedFiles() {

        LOGGER.info(">>>>>> Running scheduler for deleting orphaned filees: {}", LocalDateTime.now());

        List<File> filees = this.getOrphanedFiles();

        filees.stream().forEach(this::deleteFile);

    }

    private void deleteFile(File filee) {
        try {
            this.deleteFilesFromFileSystem(filee);
            this.deleteFilesFromDatabase(filee);
        } catch (Exception e) {
            LOGGER.error("Failed to delete filee: {} because of: {}", filee.getFileName(),e);
        }
    }

    private void deleteFilesFromFileSystem(File filee) {
        fileStorageService.deleteFile(filee.getFileName());
    }

    private void deleteFilesFromDatabase(File filee) {
        fileRepository.delete(filee);
    }

    private List<File> getOrphanedFiles() {
        Date datePosted = java.sql.Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        return fileRepository.findByContentIdIsNullAndDatePostedBefore(datePosted);
    }


}
