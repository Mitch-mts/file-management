package mts.mtech.filemanagement.service.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Mitch
 *
 */

@Component
public class FileStorageProperties {
    @Getter
    @Value("${file.uploadDir}")
    private String uploadRoot="";

    @Value("${file.fileTypes}")
    private String fileTypes="";

    public List<String> getFileTypes() {
        return Arrays.asList(fileTypes.split(","));
    }
}
