package com.akko.projectucbackend.service.impl;

import com.akko.projectucbackend.mapper.UserMapper;
import com.akko.projectucbackend.model.domain.User;
import com.akko.projectucbackend.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author akko
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-11-03 16:26:22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




