package com.akko.projectucbackend.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求
 *
 * @author akko
 */
@Data
public class UserLoginRequest implements Serializable {
    /**
     * 序列化 UID
     */
    private static final long serialVersionUID = -3085664740442714543L;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户密码
     */
    private String userPassword;
}