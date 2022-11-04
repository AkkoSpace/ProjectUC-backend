package com.akko.projectucbackend.utils.constant;

/**
 * 用户常量
 *
 * @author akko
 */

public final class UserConstant {

    private UserConstant() {
        // do nothing
    }

    /**
     * 盐值,用于加密
     */
    public static final String SALT = "akko";
    /**
     * 用户登录态键
     */
    public static final String USER_LOGIN_SESSION_KEY = "userLoginSessionKey";
    /**
     * 角色类型
     * 0 - 默认用户
     * 1 - 管理员
     */
    public static final Integer DEFAULT_ROLE_TYPE = 0;
    public static final Integer ADMIN_ROLE_TYPE = 1;

}