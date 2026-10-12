# neusoft-eco-platform

大四实验项目：Spring Boot 3.2.0 + MyBatis-Plus 3.5.5 + MySQL 8。

## 环境要求

| 组件 | 版本 |
| --- | --- |
| JDK | 17 |
| MySQL | 8.0（本机安装即可） |
| 构建 | 用项目自带的 `mvnw`，无需单独装 Maven |

## 第一次运行（组员按这 3 步走）

```bash
git clone https://github.com/diaosakura/neusoft-eco-platform.git
cd neusoft-eco-platform
```

**1. 准备你自己的数据库密码**

复制 `src/main/resources/application-local.properties.example` 为
`src/main/resources/application-local.properties`，把 `CHANGE_ME` 改成**你本机 MySQL 的 root 密码**。

> `application-local.properties` 已被 `.gitignore` 忽略，**不会被提交**，所以每个人的密码互不影响。

**2. 直接启动项目**

```bash
mvnw spring-boot:run
```

**不需要手动建库建表**：

- 库 `neusoft_eco` 由 JDBC 参数 `createDatabaseIfNotExist=true` 自动创建
- 表结构和示例数据由 `spring.sql.init` 在启动时自动执行 `schema.sql` + `data.sql`（脚本均为幂等写法，重复启动不会报错）

**3. 验证**

```bash
mvnw test -Dtest=DatabaseInitTests
```

4 个测试全绿即说明「建表 + 字典 + 演示数据 + MyBatis-Plus 映射」整条链路正常。
默认演示数据里有 6 个账号（`admin` / `supervisor01` / `supervisor02` / `manager01` / `manager02` / `decision01`，密码统一 `123456`）。

## 关于数据库：组员怎么用

本项目采用**每人本地各跑一份数据库**的方式：

- 每个人在自己电脑上装一个 MySQL，各自建库建表（脚本自动执行，零手动操作）
- 好处：不依赖谁开机、互不干扰、答辩时任何一台机器都能跑
- 代价：数据不同步（组内调试需要共享数据时，把 host 改成同一台机器的 IP 即可）

**修改数据库地址**：在 `application-local.properties` 里覆盖即可，例如

```properties
spring.datasource.url=jdbc:mysql://192.168.1.100:3306/neusoft_eco?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
spring.datasource.password=xxx
```

### 手动建库 + 建表（可选）

想不启动项目、直接用命令行把库和表一次建好：

```bash
mysql -u root -p < sql/air_quality_schema.sql
```

（该脚本 = 建库 + 15 张表 + 字典数据。应用启动时自动执行的是 `src/main/resources/sql/schema.sql` + `data.sql`，
`air_quality_schema.sql` 只是「手动一键版」的便捷副本，两者表结构保持一致。）

只想建库不想建表：`mysql -u root -p < sql/init.sql`

## 在 IDEA 里写 SQL

IDEA 的 Database 工具窗口连接是**每台机器各自的本地配置**（存在 `.idea/`，已被忽略），别人不配也不影响运行项目。

配一次即可：`View → Tool Windows → Database` → `+` → `Data Source` → `MySQL`，填
Host `127.0.0.1`、Port `3306`、User `root`、你自己的密码；驱动缺失时点 "Download missing driver files"。

之后右键数据源 → **New → Query Console**，写 SQL 按 `Ctrl+Enter` 执行。

## 目录结构

```
sql/
  ├─ air_quality_schema.sql                 手动一键脚本：建库 + 15 张表 + 字典（可选）
  └─ init.sql                               只建库（可选）
src/main/java/com/sakura/neusoftecoplatform/
  ├─ NeusoftEcoPlatformApplication.java     启动类
  ├─ entity/                                15 个 MyBatis-Plus 实体
  └─ mapper/                                15 个 Mapper
src/main/resources/
  ├─ application.properties                 公共配置（提交）
  ├─ application-local.properties           本机密码（不提交）
  ├─ application-local.properties.example   模板（提交）
  └─ sql/
      ├─ schema.sql                         15 张表 DDL + 字典数据（启动时自动执行）
      └─ data.sql                           演示数据（启动时自动执行）
```

## 数据库表清单（15 张）

| 分类 | 表名 | 说明 |
| --- | --- | --- |
| 字典 | `sys_role` | 角色：公众监督员 / 网格员 / 系统管理员 / 决策者 |
| 字典 | `aqi_level` | 空气质量指数分类（优~严重污染，含大屏配色） |
| 字典 | `sys_province` | 省表（34 个省级行政区） |
| 字典 | `sys_city` | 市表（示例 30 条，完整数据需另行导入） |
| 字典 | `sys_district` | 区/县表 |
| 字典 | `sys_grid` | 网格表（省-市-区县-网格） |
| 用户 | `sys_user` | 账号信息（用户名/手机号均可登录） |
| 用户 | `supervisor_info` | 公众监督员信息（性别、年龄） |
| 用户 | `grid_manager_info` | 网格员信息 |
| 业务 | `feedback_order` | 公众反馈工单（待指派 / 已指派 / 已核查） |
| 业务 | `feedback_assign` | 工单指派记录 |
| 业务 | `aqi_measure` | AQI 实测数据（SO₂ / CO / PM2.5，工单唯一） |
| 统计 | `aqi_statistics` | 统计数据（等级分布 / 各省超标 / 12 个月趋势 / 覆盖率 / 污染物超标） |
| 统计 | `aqi_stat_summary` | 大屏指标卡片汇总 |
| 日志 | `oper_log` | 操作日志 |

详细字段含义见 `src/main/resources/sql/schema.sql` 中的中文注释。

## 常见问题

**启动报 `Failed to determine a suitable driver class`**
数据库配置没读到，检查 `application-local.properties` 是否创建、内容是否正确。

**启动报 `Access denied for user 'root'@'localhost'`**
`application-local.properties` 里的密码不对。

**IDEA 里 `org.springframework` 报红 / 依赖解析不出来**
在 Maven 面板点 ↻ **Reload All Maven Projects**。
若反复无效，检查 `Settings → Build, Execution, Deployment → Build Tools → Maven → Maven home path`
不要选 Maven 3.10.0（IDEA 2024.1 的内嵌 Maven 服务器不支持它），用 **Bundled (Maven 3)**。

**`git push` 报 `Recv failure: Connection was reset`**
直连 GitHub 被重置，需要让 git 走代理：

```bash
git config --global http.https://github.com.proxy http://127.0.0.1:7890
```

（端口按你本地代理软件的实际端口改）
