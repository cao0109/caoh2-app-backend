create table tb_role
(
    id          bigint auto_increment comment '主键'
        primary key,
    name        varchar(50)  not null comment '角色名称',
    description varchar(200) null comment '角色描述',
    create_time datetime     not null comment '创建时间',
    update_time datetime     not null comment '更新时间'
)
    comment '角色表' charset = utf8mb4;

create table tb_user
(
    id          bigint auto_increment comment '用户ID'
        primary key,
    username    varchar(50)          not null comment '用户名',
    password    varchar(255)         not null comment '密码',
    nickname    varchar(50)          null comment '昵称',
    avatar      varchar(255)         null comment '头像',
    email       varchar(50)          null comment '邮箱',
    phone       varchar(20)          null comment '手机号码',
    status      tinyint(1) default 1 not null comment '用户状态：0->禁用；1->启用',
    create_time datetime             not null comment '创建时间',
    update_time datetime             null comment '更新时间',
    constraint uk_username
        unique (username)
)
    comment '用户表' charset = utf8mb4;

create table tb_user_role
(
    id      bigint auto_increment comment '主键'
        primary key,
    user_id bigint not null comment '用户id',
    role_id bigint not null comment '角色id',
    constraint fk_role_id
        foreign key (role_id) references tb_role (id)
            on delete cascade,
    constraint fk_user_id
        foreign key (user_id) references tb_user (id)
            on delete cascade
)
    comment '用户角色关联表' charset = utf8mb4;

create index idx_role_id
    on tb_user_role (role_id);

create index idx_user_id
    on tb_user_role (user_id);


