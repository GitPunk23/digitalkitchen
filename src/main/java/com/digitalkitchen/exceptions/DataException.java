package com.digitalkitchen.exceptions;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class DataException extends RuntimeException {
    private final String errorCode;
    private final String errorMessage;
    private static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public DataException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
