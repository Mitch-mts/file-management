package mts.mtech.filemanagement.service.detail;

import mts.mtech.filemanagement.model.File;
import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.store.FileNotFoundException;
import mts.mtech.filemanagement.utils.Constants;
import org.springframework.stereotype.Service;

/**
 * @author Mitch
 *
 */
@Service
public class FileDetailServiceImpl implements FileDetailService {
    private final FileRepository fileRepository;

    public FileDetailServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getFile(FileDetailRequest fileDetailRequest) {
        return fileRepository.findById(fileDetailRequest.getFileId())
                            .orElseThrow(()->new FileNotFoundException(Constants.FILE_NOT_FOUND));
    }

    @Override
    public File getUserFileId(FileDetailRequest fileDetailRequest, Long userId) {
        return fileRepository.findByIdAndUserId(fileDetailRequest.getFileId(), userId)
                            .orElseThrow(()-> new FileNotFoundException(Constants.FILE_NOT_FOUND));
    }
}
