-- ============================================================
-- 示例数据（幂等：INSERT IGNORE 依赖 username 唯一索引，重复执行不会报错）
-- Spring Boot 启动时按 spring.sql.init.data-locations 自动执行
-- ============================================================

INSERT IGNORE INTO eco_user (username, password, nickname, email, status)
VALUES ('admin', 'admin123', '管理员', 'admin@neusoft.edu.cn', 1),
       ('test', 'test123', '测试用户', 'test@neusoft.edu.cn', 1);
