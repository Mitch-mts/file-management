package mts.mtech.filemanagement.service.delete;

import lombok.Getter;

/**
 * @author Mitch
 *
 */
public final class DeleteFileResponse {

    @Getter
    private final Boolean success;

    @Getter
    private final String message;

    public DeleteFileResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
