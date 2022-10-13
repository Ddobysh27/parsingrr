package com.trustpilot.parsingrr.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ApiRequestException extends RuntimeException {

    private int code;

    public ApiRequestException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ApiRequestException(String message) {
        super(message);
    }

}
