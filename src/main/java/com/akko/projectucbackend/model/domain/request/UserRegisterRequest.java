package com.akko.projectucbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求
 *
 * @author akko
 */
@Data
public class UserRegisterRequest implements Serializable {
    /**
     * 序列化 UID
     */
    private static final long serialVersionUID = 3881110054296987441L;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 专属密钥
     */
    private String lazyKey;
    /**
     * 校验密码
     */
    private String checkPassword;
}