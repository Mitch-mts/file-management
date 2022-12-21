package mts.mtech.filemanagement.utils;

import java.io.Serializable;

/**
 * @author Mitchell Tawanda Severa
 * 10/17/18
 * 11:26 PM
 */

public class Response<T> implements Serializable {

    private int statusCode;
    private boolean success;
    private String message;
    private T result;

    public  Response<T> buildSuccessResponse(String message) {
        this.statusCode = Constants.SUCCESS_INT_VALUE;
        this.success = true;
        this.message = message;
        this.result = null;
        return this;
    }

    public Response<T> buildSuccessResponse(String message, final T result) {
        this.statusCode = Constants.SUCCESS_INT_VALUE;
        this.success = true;
        this.message = message;
        this.result = result;
        return this;
    }

    public Response<T> buildErrorResponse(String message) {
        this.statusCode = Constants.FAILURE_INT_VALUE;
        this.success = false;
        this.message = message;
        this.result = null;
        return this;
    }

    public Response<T> buildErrorResponse(String message, final T result) {
        this.statusCode = Constants.FAILURE_INT_VALUE;
        this.success = false;
        this.message = message;
        this.result = result;
        return this;
    }

    public Response() {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "statusCode=" + statusCode +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}