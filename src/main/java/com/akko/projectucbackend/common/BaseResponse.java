package com.akko.projectucbackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T> 返回数据类型
 * @author akko
 */
@Data
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -2189471388417893964L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 消息
     */
    private String message;
    /**
     * 描述
     */
    private String description;
    /**
     * 数据
     */
    private T data;

    BaseResponse(int code, String message, String description, T data) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    BaseResponse(int code, String message, T data) {
        this(code, message, "", data);
    }

    BaseResponse(int code, T data) {
        this(code, "", "", data);
    }

    BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage(), errorCode.getDescription(), null);
    }

}
