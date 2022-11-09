package com.akko.projectucbackend.controller;

import com.akko.projectucbackend.model.domain.User;
import com.akko.projectucbackend.model.domain.request.UserLoginRequest;
import com.akko.projectucbackend.model.domain.request.UserRegisterRequest;
import com.akko.projectucbackend.service.UserService;
import com.akko.projectucbackend.utils.common.Desensitization;
import com.akko.projectucbackend.utils.common.Permission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.akko.projectucbackend.utils.common.Desensitization.getSafeUser;
import static com.akko.projectucbackend.utils.constant.CommonConstant.ZERO;
import static com.akko.projectucbackend.utils.constant.UserConstant.USER_LOGIN_SESSION_KEY;

/**
 * 用户控制器
 *
 * @author akko
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String lazyKey = userRegisterRequest.getLazyKey();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, lazyKey)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
    }

    @PostMapping("/login")
    public User userRegister(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @PostMapping("/logout")
    public Integer userRegister(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return userService.userLogout(request);
    }

    @GetMapping("/current")
    public User getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_SESSION_KEY);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        //TODO 用户合法性校验
        User user = userService.getById(userId);
        return getSafeUser(user);

    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request) {
        if (!Permission.isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        return userList.stream().map(Desensitization::getSafeUser).collect(Collectors.toList());

    }

    @PostMapping("/delete")
    public boolean deleteUsers(@RequestBody long id, HttpServletRequest request) {
        if (!Permission.isAdmin(request)) {
            return false;
        }
        if (id <= ZERO) {
            return false;
        }
        return userService.removeById(id);
    }
}
