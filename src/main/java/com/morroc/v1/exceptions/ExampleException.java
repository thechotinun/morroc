package com.morroc.v1.exceptions;

import org.springframework.http.HttpStatus;

public class ExampleException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detailMessage;

    public ExampleException(String message, String detailMessage, ErrorCode errorCode) {
        super(message);
        this.detailMessage = detailMessage;
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

    public String getErrorTitle() {
        return errorCode.getErrorTitle();
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public enum ErrorCode {
        EXAMPLE_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
        INVALID_INPUT(HttpStatus.BAD_REQUEST, "Invalid Input");

        private final HttpStatus httpStatus;
        private final String errorTitle;

        ErrorCode(HttpStatus httpStatus, String errorTitle) {
            this.httpStatus = httpStatus;
            this.errorTitle = errorTitle;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public String getErrorTitle() {
            return errorTitle;
        }
    }
}