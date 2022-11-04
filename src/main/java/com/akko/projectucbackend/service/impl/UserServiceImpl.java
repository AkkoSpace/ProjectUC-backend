package com.akko.projectucbackend.service.impl;

import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.akko.projectucbackend.mapper.UserMapper;
import com.akko.projectucbackend.model.domain.User;
import com.akko.projectucbackend.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现类
 *
 * @author akko
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 校验
        // 1.1 非空校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        // 1.2 长度校验
        // 1.2.1 用户名长度校验
        if (userAccount.length() < 4 || userAccount.length() > 16) {
            return -1;
        }
        // 1.2.2 密码长度校验
        if (userPassword.length() < 8 || userPassword.length() > 16) {
            return -1;
        }
        if (checkPassword.length() < 8 || checkPassword.length() > 16) {
            return -1;
        }
        // 1.3 账户名称特殊字符校验
        boolean isMatch = ReUtil.isMatch("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", userAccount);
        if (!isMatch) {
            return -1;
        }
        // 1.4 账户重复校验
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 1.5 密码一致性校验
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 2. 密码加密
        String md5Password = DigestUtil.md5Hex((userPassword + "SALT").getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(md5Password);
        boolean saveResult = save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }

}




