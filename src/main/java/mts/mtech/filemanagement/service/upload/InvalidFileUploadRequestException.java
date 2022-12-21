package mts.mtech.filemanagement.service.upload;

/**
 * @author Mitch
 *
 */
public class InvalidFileUploadRequestException extends RuntimeException{

    public InvalidFileUploadRequestException(String message) {
        super(message);
    }
}
