package mts.mtech.filemanagement.service.update;

/**
 * @author Mitch
 *
 */
public class InvalidFileUpdateRequestException extends RuntimeException{

    public InvalidFileUpdateRequestException(String message) {
        super(message);
    }
}
