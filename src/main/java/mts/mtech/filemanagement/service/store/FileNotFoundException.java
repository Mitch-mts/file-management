package mts.mtech.filemanagement.service.store;

/**
 * @author Mitch
 *
 */
public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String message) {
        super(message);
    }
}
