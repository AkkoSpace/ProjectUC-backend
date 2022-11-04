package com.akko.projectucbackend.utils.common;


import com.akko.projectucbackend.model.domain.User;

import javax.servlet.http.HttpServletRequest;

import static com.akko.projectucbackend.utils.constant.UserConstant.ADMIN_ROLE_TYPE;
import static com.akko.projectucbackend.utils.constant.UserConstant.USER_LOGIN_SESSION_KEY;

/**
 * 权限工具类
 *
 * @author akko
 */
public class Permission {

    private Permission() {
        // do nothing
    }

    /**
     * 判断用户是否为管理员
     *
     * @param request 用户请求
     * @return true - 是管理员, false - 不是管理员
     */
    public static boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_SESSION_KEY);
        User user = (User) userObj;
        return user != null && user.getUserRole().equals(ADMIN_ROLE_TYPE);
    }

}