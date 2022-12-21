package mts.mtech.filemanagement.repository;

import mts.mtech.filemanagement.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Fact S Musingarimi
 * 7/26/18
 * 10:22 AM
 */
public interface FileRepository extends JpaRepository<File, Long> {

    Optional<File> findOneById(Long id);

    Optional<File> findById(Long id);

    Optional<File> findByUserId(Long userId);

    List<File> findByContentIdIsNullAndDatePostedBefore(Date datePosted);

    Optional<File> findByIdAndUserId(Long fileId, Long userId);
}
