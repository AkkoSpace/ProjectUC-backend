package com.akko.projectucbackend.exception;

import com.akko.projectucbackend.common.BaseResponse;
import com.akko.projectucbackend.common.ErrorCode;
import com.akko.projectucbackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author akko
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public BaseResponse businessException(BusinessException e) {
        log.error("businessException: {}" + e.getMessage(), e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeException(RuntimeException e) {
        log.error("runtimeException: {}", e);
        return ResultUtils.error(ErrorCode.USER_REQUEST_SERVICE_EXCEPTION, e.getMessage(), "");
    }
}
