package mts.mtech.filemanagement.service.upload;

import mts.mtech.filemanagement.repository.FileRepository;
import mts.mtech.filemanagement.service.configuration.FileStorageProperties;
import mts.mtech.filemanagement.service.store.FileStorageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FileUploadServiceImplTest {
    private FileUploadServiceImpl underTest;

    @Mock
    private FileRepository fileRepository;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private FileStorageProperties fileStorageProperties;

    @BeforeEach
    void setUp() {
        underTest = new FileUploadServiceImpl(fileRepository, fileStorageService, fileStorageProperties);
    }

    @Test
    void uploadFile() {
    }

    @Test
    void uploadUserFile() {
    }

    @Test
    @Disabled
    void checkIfFileTypeIsAccepted() {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

        given(multipartFile.getOriginalFilename()).willReturn("test.pdf");

        underTest.checkIfFileTypeIsAccepted(multipartFile);

        verify(multipartFile, Mockito.times(1)).getOriginalFilename();
    }
}