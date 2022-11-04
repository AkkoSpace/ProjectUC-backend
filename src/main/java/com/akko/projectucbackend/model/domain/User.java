package com.akko.projectucbackend.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 *
 * @author akko
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别(0 - 女, 1 - 男)
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户角色(0 - 默认角色, 1 - 管理员)
     */
    private Integer userRole;

    /**
     * 状态(0 - 正常)
     */
    private Integer userStatus;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer delFlag;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}