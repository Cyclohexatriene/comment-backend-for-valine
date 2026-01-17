CREATE TABLE comment
(
    id          BIGINT(20)  AUTO_INCREMENT COMMENT '自增主键',
    object_id   VARCHAR(32)   NOT NULL DEFAULT '' COMMENT '原始对象ID',
    nick        VARCHAR(100)  NOT NULL DEFAULT 'ANONYMOUS' COMMENT '昵称',
    ip          VARCHAR(45)            DEFAULT '' COMMENT 'IP地址（支持IPv6）',
    mail        VARCHAR(100)           DEFAULT '' COMMENT '邮箱',
    link        VARCHAR(100)           DEFAULT '' COMMENT '链接',
    url         VARCHAR(100)           DEFAULT '' COMMENT '页面URL',
    comment     VARCHAR(2048) NOT NULL COMMENT '评论内容（HTML格式）',
    qq_avatar   VARCHAR(100)           DEFAULT '' COMMENT 'QQ头像链接',
    ua          VARCHAR(1000)          DEFAULT '' COMMENT '用户代理字符串',
    acl         VARCHAR(500)           DEFAULT '' COMMENT '访问控制列表（JSON字符串）',
    inserted_at VARCHAR(100)  NOT NULL DEFAULT '' COMMENT '插入时间（JSON字符串）',

    -- 关联关系字段
    pid         VARCHAR(32)            DEFAULT '' COMMENT '父评论object_id',
    rid         VARCHAR(32)            DEFAULT '' COMMENT '根评论object_id',

    -- 时间字段
    created_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at  DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 索引
    PRIMARY KEY (`Id`),
    UNIQUE KEY uk_object_id (object_id),
    INDEX       idx_created_at (created_at),
    INDEX       idx_updated_at (updated_at),
    INDEX       idx_inserted_at (inserted_at),
    INDEX       idx_pid (pid),
    INDEX       idx_rid (rid),
    INDEX       idx_url (url(100))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';