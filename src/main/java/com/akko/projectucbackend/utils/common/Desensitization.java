package com.akko.projectucbackend.utils.common;


import com.akko.projectucbackend.model.domain.User;

/**
 * 脱敏工具类
 *
 * @author akko
 */
public class Desensitization {

    private Desensitization() {
        // do nothing
    }

    /**
     * 脱敏用户信息
     *
     * @param originUser 原始用户信息
     * @return safeUser 脱敏后的用户信息
     */
    public static User getSafeUser(User originUser) {
        User safeUser = new User();
        safeUser.setId(originUser.getId());
        safeUser.setUsername(originUser.getUsername());
        safeUser.setGender(originUser.getGender());
        safeUser.setPhone(originUser.getPhone());
        safeUser.setEmail(originUser.getEmail());
        safeUser.setUserAccount(originUser.getUserAccount());
        safeUser.setUserPassword(null);
        safeUser.setUserStatus(originUser.getUserStatus());
        safeUser.setUserRole(originUser.getUserRole());
        safeUser.setAvatarUrl(originUser.getAvatarUrl());
        safeUser.setCreateTime(originUser.getCreateTime());
        return safeUser;
    }

}