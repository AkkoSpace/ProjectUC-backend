package com.akko.projectucbackend.service;

import com.akko.projectucbackend.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();
        user.setUsername("akkoyan");
        user.setUserAccount("123456");
        user.setAvatarUrl("https://s2.loli.net/2022/10/17/2nvGVExDA1WrpJl.png");
        user.setGender(0);
        user.setUserPassword("Yan");
        user.setEmail("123");
        user.setPhone("456");
        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "akko";
        String userPassword = "";
        String checkPassword = "123456";
        String lazyKey = "1234";
        long result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        userAccount = "ak";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        userAccount = "akko";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        userAccount = "ak ko";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        userAccount = "akkoyan";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertEquals(-1, result);

        // ?????????????????????????????????
        userAccount = "akko1111";
        result = userService.userRegister(userAccount, userPassword, checkPassword, lazyKey);
        Assertions.assertTrue(result > 0);
    }
}