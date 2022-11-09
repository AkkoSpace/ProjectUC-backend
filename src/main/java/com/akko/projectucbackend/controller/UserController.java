package com.akko.projectucbackend.controller;

import com.akko.projectucbackend.common.BaseResponse;
import com.akko.projectucbackend.common.ErrorCode;
import com.akko.projectucbackend.common.ResultUtils;
import com.akko.projectucbackend.exception.BusinessException;
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
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String lazyKey = userRegisterRequest.getLazyKey();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, lazyKey)) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User> userRegister(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userRegister(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_SESSION_KEY);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.USER_LOGIN_EXPIRED);
        }
        long userId = currentUser.getId();
        //TODO 用户合法性校验
        User user = userService.getById(userId);
        User safeUser = getSafeUser(user);
        return ResultUtils.success(safeUser);

    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (!Permission.isAdmin(request)) {
            throw new BusinessException(ErrorCode.ACCESS_NOT_AUTHORIZED);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(Desensitization::getSafeUser).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUsers(@RequestBody long id, HttpServletRequest request) {
        if (!Permission.isAdmin(request)) {
            throw new BusinessException(ErrorCode.ACCESS_NOT_AUTHORIZED);
        }
        if (id <= ZERO) {
            throw new BusinessException(ErrorCode.PARAMETER_NULL);
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }
}
