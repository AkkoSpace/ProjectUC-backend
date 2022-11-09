package com.akko.projectucbackend.common;

/**
 * 错误码
 *
 * @author akko
 */

public enum ErrorCode {
    /**
     * 通用错误码
     * 参考 Java 开发手册
     */
    SUCCESS(0, "success", "一切 OK!"),
    ERROR(1, "error", "出错啦!"),
    REGISTER_FAILED(10100, "register failed", "注册失败"),
    USERNAME_VERIFICATION_FAILURE(10110, "username verification failure", "用户名验证失败"),
    USERNAME_ALREADY_EXISTS(10111, "username already exists", "用户名已存在"),
    USERNAME_WITH_SPECIAL_CHARACTERS(10113, "username with special characters", "用户名包含特殊字符"),
    PASSWORD_VERIFICATION_FAILED(10120, "password verification failed", "密码验证失败"),
    PASSWORD_INCONSISTENCY(10123, "password inconsistency", "密码不一致"),
    KEY_VERIFICATION_FAILURE(10130, "key verification failure", "密钥验证失败"),
    KEY_ALREADY_EXISTS(10134, "key already exists", "密钥已存在"),
    USER_LOGIN_ERROR(10200, "user login error", "用户登录异常"),
    USER_LOGIN_EXPIRED(10230, "user login expired", "用户登录过期"),
    ACCESS_NOT_AUTHORIZED(10301, "access not authorized", "访问未授权"),
    PARAMETER_NULL(10410, "parameter null", "请求参数为空"),
    USER_REQUEST_SERVICE_EXCEPTION(10500, "user request service exception", "用户请求服务异常");

    private final int code;
    /**
     * 错误信息
     */
    private final String message;
    /**
     * 错误码描述(详情)
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
