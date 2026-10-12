-- ============================================================
-- 手动建库脚本（可选，平时不需要）
--
-- 正常情况下 JDBC 参数 createDatabaseIfNotExist=true 会自动建库，
-- 只有下面两种情况才需要手动执行：
--   1. 你的 MySQL 账号没有 CREATE DATABASE 权限
--   2. 想显式指定库的字符集
--
-- 用法（在项目根目录执行）：
--   "D:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -p < sql\init.sql
--
-- 表结构和示例数据不用在这里建，Spring Boot 启动时会自动执行
-- src/main/resources/sql/schema.sql 和 data.sql
-- ============================================================

CREATE DATABASE IF NOT EXISTS neusoft_eco
    DEFAULT CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
