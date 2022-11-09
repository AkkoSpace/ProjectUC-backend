package com.akko.projectucbackend.service.impl;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.akko.projectucbackend.mapper.UserMapper;
import com.akko.projectucbackend.model.domain.User;
import com.akko.projectucbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.akko.projectucbackend.utils.common.Desensitization.getSafeUser;
import static com.akko.projectucbackend.utils.constant.CommonConstant.*;
import static com.akko.projectucbackend.utils.constant.UserConstant.SALT;
import static com.akko.projectucbackend.utils.constant.UserConstant.USER_LOGIN_SESSION_KEY;

/**
 * 用户服务实现类
 *
 * @author akko
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String lazyKey) {
        // 1. 校验
        // 1.1 非空校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, lazyKey)) {
            return -1;
        }
        // 1.2 长度校验
        // 1.2.1 用户名长度校验
        if (userAccount.length() < FOUR || userAccount.length() > SIXTEEN) {
            return -1;
        }
        // 1.2.2 密码长度校验
        if (userPassword.length() < EIGHT || userPassword.length() > SIXTEEN) {
            return -1;
        }
        if (checkPassword.length() < EIGHT || checkPassword.length() > SIXTEEN) {
            return -1;
        }
        // 1.2.3 专属密钥长度校验
        if (lazyKey.length() < FOUR || lazyKey.length() > EIGHT) {
            return -1;
        }
        // 1.3 账户名称特殊字符校验
        boolean isMatch = ReUtil.isMatch("^[A-Za-z0-9]+$", userAccount);
        if (!isMatch) {
            return -1;
        }
        // 1.4 重复校验
        // 1.4.1 账号重复校验
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 1.4.2 专属密钥重复校验
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("lazy_key", lazyKey);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 1.5 密码一致性校验
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 2. 密码加密
        String md5Password = DigestUtil.md5Hex((userPassword + SALT).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(md5Password);
        user.setLazyKey(lazyKey);
        boolean saveResult = save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        // 1.1 非空校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            // TODO 修改为自定义异常
            return null;
        }
        // 1.2 长度校验
        // 1.2.1 用户名长度校验
        if (userAccount.length() < FOUR || userAccount.length() > SIXTEEN) {
            return null;
        }
        // 1.2.2 密码长度校验
        if (userPassword.length() < FOUR || userPassword.length() > SIXTEEN) {
            return null;
        }
        // 1.3 账户名称特殊字符校验
        boolean isMatch = ReUtil.isMatch("^[A-Za-z0-9]+$", userAccount);
        if (!isMatch) {
            return null;
        }
        // 2. 密码加密
        String md5Password = DigestUtil.md5Hex((userPassword + SALT).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        queryWrapper.eq("user_password", md5Password);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            UserServiceImpl.log.info("user login failed, userAccount doesn't match userPassword");
            return null;
        }
        // 3. 用户脱敏
        User safeUser = getSafeUser(user);
        // 4. 记录用户登录态
        request.getSession().setAttribute(USER_LOGIN_SESSION_KEY, safeUser);
        return safeUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_LOGIN_SESSION_KEY);
        return 1;
    }

}




