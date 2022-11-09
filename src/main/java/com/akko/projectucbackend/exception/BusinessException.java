package com.akko.projectucbackend.exception;

import com.akko.projectucbackend.common.ErrorCode;

/**
 * @author akko
 */

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 7255842063758090772L;
    private final int code;
    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        code = errorCode.getCode();
        description = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        code = errorCode.getCode();
        this.description = description;
    }

    int getCode() {
        return code;
    }

    String getDescription() {
        return description;
    }
}
