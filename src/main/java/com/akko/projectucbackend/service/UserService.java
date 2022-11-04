package com.akko.projectucbackend.service;

import com.akko.projectucbackend.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author akko
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2022-11-03 16:26:22
 */
public interface UserService extends IService<User> {

    /**
     * 用户校验
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount 用户账号
     * @param userPasswd  用户密码
     * @param request     用户请求
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPasswd, HttpServletRequest request);
}
