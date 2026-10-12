-- ============================================================
-- 建表脚本（幂等：可重复执行）
-- Spring Boot 启动时按 spring.sql.init.schema-locations 自动执行
-- 库本身由 JDBC 参数 createDatabaseIfNotExist=true 自动创建
-- ============================================================

CREATE TABLE IF NOT EXISTS eco_user
(
    id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    username    VARCHAR(50)  NOT NULL COMMENT '用户名',
    password    VARCHAR(100) NOT NULL COMMENT '密码',
    nickname    VARCHAR(50)           DEFAULT NULL COMMENT '昵称',
    email       VARCHAR(100)          DEFAULT NULL COMMENT '邮箱',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1=正常，0=禁用',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_eco_user_username (username)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='示例用户表';
