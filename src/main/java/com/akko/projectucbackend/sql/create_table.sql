-- auto-generated definition
create table user
(
    id            bigint auto_increment comment 'Id'
        primary key,
    username      varchar(256)                       null comment '用户昵称',
    avatar_url    varchar(1024)                      null comment '用户头像',
    gender        tinyint                            null comment '性别(0 - 女, 1 - 男)',
    email         varchar(512)                       null comment '邮箱',
    phone         varchar(128)                       null comment '电话',
    user_role     tinyint  default 0                 not null comment '用户角色(0 - 默认角色, 1 - 管理员)',
    user_status   int      default 0                 null comment '状态(0 - 正常)',
    del_flag      tinyint  default 0                 not null comment '是否删除',
    user_account  varchar(256)                       null comment '账号',
    user_password varchar(512)                       not null comment '密码',
    lazy_key      varchar(255)                       null comment '专属密钥',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户表';

