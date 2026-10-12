-- ============================================================================
--  东软空气质量环保公众监督系统 —— 建表脚本（应用启动时自动执行）
--  依据：《需求进度管理表》
--
--  由 Spring Boot 的 spring.sql.init 在启动时执行：
--    spring.sql.init.schema-locations=classpath:sql/schema.sql
--    spring.sql.init.data-locations=classpath:sql/data.sql
--
--  注意：这里不含 CREATE DATABASE / USE，因为数据源已经连到 neusoft_eco，
--        库由 JDBC 参数 createDatabaseIfNotExist=true 自动创建。
--        需要"手动一键建库建表"的完整脚本见仓库根目录 sql/air_quality_schema.sql
--
--  脚本可重复执行：DROP TABLE IF EXISTS 重建 + INSERT IGNORE 幂等字典数据
-- ============================================================================
-- ============================================================================
--  数据库结构（DDL）
--  数据库：MySQL 8.0   字符集：utf8mb4   引擎：InnoDB
-- ============================================================================


SET FOREIGN_KEY_CHECKS = 0;


-- ============================================================================
--  一、基础字典
-- ============================================================================

-- ---------------------------------------------------------------------------
-- 1. sys_role  角色表            【需求 NO.6~10 权限管理】
--    角色标识：公众监督员 / 网格员 / 系统管理员 / 决策者
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    rid         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    role_code   VARCHAR(32)  NOT NULL COMMENT '角色标识（英文 code）',
    role_name   VARCHAR(32)  NOT NULL COMMENT '角色名称',
    home_page   VARCHAR(100) DEFAULT NULL COMMENT '登录后跳转的首页路由（前端根据角色跳转）',
    description VARCHAR(200) DEFAULT NULL COMMENT '角色说明',
    sort_no     INT          NOT NULL DEFAULT 0 COMMENT '排序',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (rid),
    UNIQUE KEY uk_role_code (role_code)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

INSERT IGNORE INTO sys_role (rid, role_code, role_name, home_page, description, sort_no)
VALUES (1, 'SUPERVISOR', '公众监督员', '/supervisor/grid', '公众端：选择网格、上报 AQI 监督信息、查看历史反馈', 1),
       (2, 'GRID_MANAGER', '网格员', '/manager/task', '网格员端：查询指派任务、实地检测并录入实测数据', 2),
       (3, 'ADMIN', '系统管理员', '/admin/feedback', '管理端：查询反馈工单、指派任务、查询实测数据、生成统计', 3),
       (4, 'DECISION_MAKER', '决策者', '/screen', '大屏端：查看可视化大屏（只读）', 4);


-- ---------------------------------------------------------------------------
-- 2. aqi_level 空气质量指数分类表   【需求 NO.1 基本信息：空气质量指数分类】
--    公众上报时"预估"取值，网格员实测时由三项污染物"自动计算"取值
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS aqi_level;
CREATE TABLE aqi_level
(
    lid         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    level_no    TINYINT      NOT NULL COMMENT '等级序号 1~6',
    level_name  VARCHAR(20)  NOT NULL COMMENT '等级名称',
    aqi_min     INT          NOT NULL COMMENT 'AQI 下限',
    aqi_max     INT          NOT NULL COMMENT 'AQI 上限（严重污染按 500 计）',
    is_exceed   TINYINT      NOT NULL DEFAULT 0 COMMENT '是否计入"超标"：1=是 0=否',
    color       VARCHAR(20)  DEFAULT NULL COMMENT '大屏配色（十六进制）',
    description VARCHAR(200) DEFAULT NULL COMMENT '健康影响说明',
    PRIMARY KEY (lid),
    UNIQUE KEY uk_aqi_level_no (level_no)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='空气质量指数等级分类表';

INSERT IGNORE INTO aqi_level (lid, level_no, level_name, aqi_min, aqi_max, is_exceed, color, description)
VALUES (1, 1, '优', 0, 50, 0, '#00E400', '空气质量令人满意，基本无空气污染'),
       (2, 2, '良', 51, 100, 0, '#FFFF00', '除少数敏感人群外可正常活动'),
       (3, 3, '轻度污染', 101, 150, 1, '#FF7E00', '敏感人群症状有轻度加剧，健康人群出现刺激症状'),
       (4, 4, '中度污染', 151, 200, 1, '#FF0000', '敏感人群症状明显加剧，可能影响健康人群心脏、呼吸系统'),
       (5, 5, '重度污染', 201, 300, 1, '#8F3F97', '健康人群普遍出现症状，敏感人群症状显著加剧'),
       (6, 6, '严重污染', 301, 500, 1, '#7E0023', '健康人群运动耐受力降低，有明显强烈症状');


-- ---------------------------------------------------------------------------
-- 3. sys_province 省表          【需求 NO.1 基本信息：省表】
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_province;
CREATE TABLE sys_province
(
    pid         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    province_code VARCHAR(12) NOT NULL COMMENT '省级行政区划代码',
    province_name VARCHAR(50) NOT NULL COMMENT '省/自治区/直辖市名称（大屏地图按此名称注册）',
    short_name    VARCHAR(20) DEFAULT NULL COMMENT '简称',
    longitude     DECIMAL(10, 6) DEFAULT NULL COMMENT '中心经度（如需地图打点，可后续导入）',
    latitude      DECIMAL(10, 6) DEFAULT NULL COMMENT '中心纬度（如需地图打点，可后续导入）',
    sort_no       INT         NOT NULL DEFAULT 0 COMMENT '排序',
    create_time   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (pid),
    UNIQUE KEY uk_province_code (province_code),
    UNIQUE KEY uk_province_name (province_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='省表';

INSERT IGNORE INTO sys_province (pid, province_code, province_name, short_name, sort_no)
VALUES (1, '110000', '北京市', '京', 1),
       (2, '120000', '天津市', '津', 2),
       (3, '130000', '河北省', '冀', 3),
       (4, '140000', '山西省', '晋', 4),
       (5, '150000', '内蒙古自治区', '蒙', 5),
       (6, '210000', '辽宁省', '辽', 6),
       (7, '220000', '吉林省', '吉', 7),
       (8, '230000', '黑龙江省', '黑', 8),
       (9, '310000', '上海市', '沪', 9),
       (10, '320000', '江苏省', '苏', 10),
       (11, '330000', '浙江省', '浙', 11),
       (12, '340000', '安徽省', '皖', 12),
       (13, '350000', '福建省', '闽', 13),
       (14, '360000', '江西省', '赣', 14),
       (15, '370000', '山东省', '鲁', 15),
       (16, '410000', '河南省', '豫', 16),
       (17, '420000', '湖北省', '鄂', 17),
       (18, '430000', '湖南省', '湘', 18),
       (19, '440000', '广东省', '粤', 19),
       (20, '450000', '广西壮族自治区', '桂', 20),
       (21, '460000', '海南省', '琼', 21),
       (22, '500000', '重庆市', '渝', 22),
       (23, '510000', '四川省', '川', 23),
       (24, '520000', '贵州省', '黔', 24),
       (25, '530000', '云南省', '滇', 25),
       (26, '540000', '西藏自治区', '藏', 26),
       (27, '610000', '陕西省', '陕', 27),
       (28, '620000', '甘肃省', '甘', 28),
       (29, '630000', '青海省', '青', 29),
       (30, '640000', '宁夏回族自治区', '宁', 30),
       (31, '650000', '新疆维吾尔自治区', '新', 31),
       (32, '710000', '台湾省', '台', 32),
       (33, '810000', '香港特别行政区', '港', 33),
       (34, '820000', '澳门特别行政区', '澳', 34);


-- ---------------------------------------------------------------------------
-- 4. sys_city 市表              【需求 NO.1 基本信息：市表】
--    说明：此处只给出辽宁省全部城市 + 部分主要城市作为示例，
--         完整的全国城市数据请从民政部行政区划数据导入后追加。
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_city;
CREATE TABLE sys_city
(
    cid         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    province_id BIGINT      NOT NULL COMMENT '所属省 id（sys_province.pid）',
    city_code   VARCHAR(12) NOT NULL COMMENT '市级行政区划代码',
    city_name   VARCHAR(50) NOT NULL COMMENT '城市名称',
    longitude   DECIMAL(10, 6) DEFAULT NULL COMMENT '中心经度',
    latitude    DECIMAL(10, 6) DEFAULT NULL COMMENT '中心纬度',
    sort_no     INT         NOT NULL DEFAULT 0 COMMENT '排序',
    create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (cid),
    UNIQUE KEY uk_city_code (city_code),
    KEY idx_city_province (province_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='市表';

INSERT IGNORE INTO sys_city (cid, province_id, city_code, city_name, sort_no)
VALUES
    -- 辽宁省（全部 14 个地级市）
    (1, 6, '210100', '沈阳市', 1),
    (2, 6, '210200', '大连市', 2),
    (3, 6, '210300', '鞍山市', 3),
    (4, 6, '210400', '抚顺市', 4),
    (5, 6, '210500', '本溪市', 5),
    (6, 6, '210600', '丹东市', 6),
    (7, 6, '210700', '锦州市', 7),
    (8, 6, '210800', '营口市', 8),
    (9, 6, '210900', '阜新市', 9),
    (10, 6, '211000', '辽阳市', 10),
    (11, 6, '211100', '盘锦市', 11),
    (12, 6, '211200', '铁岭市', 12),
    (13, 6, '211300', '朝阳市', 13),
    (14, 6, '211400', '葫芦岛市', 14),
    -- 直辖市
    (15, 1, '110100', '北京市', 1),
    (16, 2, '120100', '天津市', 1),
    (17, 9, '310100', '上海市', 1),
    (18, 22, '500100', '重庆市', 1),
    -- 部分主要城市（示例）
    (19, 19, '440100', '广州市', 1),
    (20, 19, '440300', '深圳市', 2),
    (21, 10, '320100', '南京市', 1),
    (22, 10, '320500', '苏州市', 2),
    (23, 11, '330100', '杭州市', 1),
    (24, 11, '330200', '宁波市', 2),
    (25, 15, '370100', '济南市', 1),
    (26, 15, '370200', '青岛市', 2),
    (27, 23, '510100', '成都市', 1),
    (28, 17, '420100', '武汉市', 1),
    (29, 27, '610100', '西安市', 1),
    (30, 16, '410100', '郑州市', 1);


-- ---------------------------------------------------------------------------
-- 5. sys_district 区/县表
--    需求 NO.14：监督员选择"省、市、区/县或具体网格名称"
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_district;
CREATE TABLE sys_district
(
    did         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    city_id       BIGINT      NOT NULL COMMENT '所属市 id（sys_city.cid）',
    district_code VARCHAR(12) NOT NULL COMMENT '区/县行政区划代码',
    district_name VARCHAR(50) NOT NULL COMMENT '区/县名称',
    sort_no       INT         NOT NULL DEFAULT 0 COMMENT '排序',
    create_time   DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (did),
    UNIQUE KEY uk_district_code (district_code),
    KEY idx_district_city (city_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='区/县表';

-- 示例：大连市下辖区县
INSERT IGNORE INTO sys_district (did, city_id, district_code, district_name, sort_no)
VALUES (1, 2, '210202', '中山区', 1),
       (2, 2, '210203', '西岗区', 2),
       (3, 2, '210204', '沙河口区', 3),
       (4, 2, '210211', '甘井子区', 4),
       (5, 2, '210213', '金州区', 5),
       (6, 2, '210214', '旅顺口区', 6);


-- ---------------------------------------------------------------------------
-- 6. sys_grid 网格表
--    需求 NO.14/15/28：网格地址选择、反馈记录网格 ID、管理端按网格统计
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_grid;
CREATE TABLE sys_grid
(
    gid         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    grid_code   VARCHAR(32)  NOT NULL COMMENT '网格编号',
    grid_name   VARCHAR(100) NOT NULL COMMENT '网格名称',
    province_id BIGINT       NOT NULL COMMENT '所属省 id',
    city_id     BIGINT       NOT NULL COMMENT '所属市 id',
    district_id BIGINT       DEFAULT NULL COMMENT '所属区/县 id',
    address     VARCHAR(200) DEFAULT NULL COMMENT '详细地址',
    longitude   DECIMAL(10, 6) DEFAULT NULL COMMENT '经度（示例值）',
    latitude    DECIMAL(10, 6) DEFAULT NULL COMMENT '纬度（示例值）',
    status      TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1=启用 0=停用',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (gid),
    UNIQUE KEY uk_grid_code (grid_code),
    KEY idx_grid_province (province_id),
    KEY idx_grid_city (city_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='网格表';

INSERT IGNORE INTO sys_grid (gid, grid_code, grid_name, province_id, city_id, district_id, address, longitude, latitude, status)
VALUES (1, 'GRID-DL-001', '大连市中山区人民路网格', 6, 2, 1, '辽宁省大连市中山区人民路', 121.639000, 38.918000, 1),
       (2, 'GRID-DL-002', '大连市西岗区香炉礁网格', 6, 2, 2, '辽宁省大连市西岗区香炉礁街道', 121.604000, 38.928000, 1),
       (3, 'GRID-DL-003', '大连市沙河口区星海广场网格', 6, 2, 3, '辽宁省大连市沙河口区星海广场', 121.587000, 38.880000, 1),
       (4, 'GRID-DL-004', '大连市甘井子区周水子网格', 6, 2, 4, '辽宁省大连市甘井子区周水子街道', 121.566000, 38.953000, 1),
       (5, 'GRID-DL-005', '大连市金州区开发区网格', 6, 2, 5, '辽宁省大连市金州区经济技术开发区', 121.783000, 39.050000, 1);



-- ============================================================================
--  二、用户与账号
-- ============================================================================

-- ---------------------------------------------------------------------------
-- 7. sys_user 账号信息表
--    需求 NO.1「账号信息」 / NO.7 注册输入（用户名、手机号、密码）
--    需求 NO.13 登录：支持用「用户名」或「手机号」登录
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    uid         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    username        VARCHAR(50)  NOT NULL COMMENT '用户名（登录账号）',
    password        VARCHAR(100) NOT NULL COMMENT '密码（建议存 BCrypt 哈希，勿存明文）',
    phone           VARCHAR(20)  NOT NULL COMMENT '手机号（亦可作为登录账号）',
    role_id         BIGINT       NOT NULL COMMENT '角色 id（sys_role.rid）',
    status          TINYINT      NOT NULL DEFAULT 1 COMMENT '状态：1=正常 0=禁用',
    last_login_time DATETIME     DEFAULT NULL COMMENT '最近登录时间',
    create_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (uid),
    UNIQUE KEY uk_user_username (username),
    UNIQUE KEY uk_user_phone (phone),
    KEY idx_user_role (role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='账号信息表';


-- ---------------------------------------------------------------------------
-- 8. supervisor_info 公众监督员信息表
--    需求 NO.1「公众监督员信息表」 / NO.7 注册输入（性别、年龄）
--    与 sys_user 是 1:1 关系：账号信息与业务信息分离
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS supervisor_info;
CREATE TABLE supervisor_info
(
    sid         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id     BIGINT      NOT NULL COMMENT '账号 id（sys_user.uid）',
    real_name   VARCHAR(50) DEFAULT NULL COMMENT '姓名',
    gender      TINYINT     DEFAULT NULL COMMENT '性别：1=男 2=女',
    age         INT         DEFAULT NULL COMMENT '年龄',
    create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (sid),
    UNIQUE KEY uk_sup_user (user_id),
    KEY idx_sup_phone (real_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='公众监督员信息表';


-- ---------------------------------------------------------------------------
-- 9. grid_manager_info 网格员信息表
--    需求 NO.1「网格员处理反馈信息表」中网格员侧的基础信息
--    （网格员"处理反馈"的过程记录见 aqi_measure 表）
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS grid_manager_info;
CREATE TABLE grid_manager_info
(
    gmid        BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id     BIGINT      NOT NULL COMMENT '账号 id（sys_user.uid）',
    grid_id     BIGINT      DEFAULT NULL COMMENT '负责的网格 id（sys_grid.gid，可为空表示多网格）',
    real_name   VARCHAR(50) DEFAULT NULL COMMENT '姓名',
    gender      TINYINT     DEFAULT NULL COMMENT '性别：1=男 2=女',
    work_no     VARCHAR(32) DEFAULT NULL COMMENT '工号',
    create_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (gmid),
    UNIQUE KEY uk_gmi_user (user_id),
    KEY idx_gmi_grid (grid_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='网格员信息表';


-- ============================================================================
--  三、业务主流程：公众上报 → 管理端指派 → 网格员核查
-- ============================================================================

-- ---------------------------------------------------------------------------
-- 10. feedback_order 公众反馈工单表
--     需求 NO.15/NO.28 公众上报：写 AQI 等级、网格ID、用户ID、反馈时间，初始状态「待指派」
--     需求 NO.30/NO.33 网格员按 assignee_id 查自己的任务（分页）
--     需求 NO.40/NO.43 管理端按 省份、城市、工单状态、时间范围 多条件过滤分页
--     需求 NO.45/NO.48 仅「待指派」可指派；指派后状态变「已指派」
--     需求 NO.38 核查后状态变「已核查」
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS feedback_order;
CREATE TABLE feedback_order
(
    oid         BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    order_no           VARCHAR(32)  NOT NULL COMMENT '工单编号',
    reporter_id        BIGINT       NOT NULL COMMENT '上报人 user_id（公众监督员）',
    grid_id            BIGINT       NOT NULL COMMENT '网格 id（sys_grid.gid）',
    province_id        BIGINT       NOT NULL COMMENT '冗余-省 id（管理端按省市筛选）',
    city_id            BIGINT       NOT NULL COMMENT '冗余-市 id',
    estimated_level_id BIGINT       NOT NULL COMMENT '公众预估 AQI 等级 id（aqi_level.lid）',
    feedback_content   VARCHAR(500) DEFAULT NULL COMMENT '反馈信息',
    feedback_time      DATETIME     NOT NULL COMMENT '反馈时间（公众填写/提交时间）',
    status             VARCHAR(20)  NOT NULL DEFAULT 'PENDING_ASSIGN' COMMENT '工单状态：PENDING_ASSIGN=待指派 ASSIGNED=已指派 CHECKED=已核查',
    assignee_id        BIGINT       DEFAULT NULL COMMENT '当前负责网格员 user_id',
    assign_time        DATETIME     DEFAULT NULL COMMENT '最近一次指派时间',
    check_time         DATETIME     DEFAULT NULL COMMENT '核查完成时间',
    create_time        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time        DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (oid),
    UNIQUE KEY uk_order_no (order_no),
    KEY idx_order_status_time (status, feedback_time),
    KEY idx_order_reporter (reporter_id),
    KEY idx_order_grid (grid_id),
    KEY idx_order_assignee (assignee_id),
    KEY idx_order_province_city (province_id, city_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='公众反馈工单表';


-- ---------------------------------------------------------------------------
-- 11. feedback_assign 工单指派记录表
--     需求 NO.45/NO.48：记录操作日志用于追溯 → 每次指派新增一条记录
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS feedback_assign;
CREATE TABLE feedback_assign
(
    aid         BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    order_id        BIGINT      NOT NULL COMMENT '工单 id（feedback_order.oid）',
    grid_manager_id BIGINT      NOT NULL COMMENT '目标网格员 user_id',
    assigner_id     BIGINT      NOT NULL COMMENT '操作人 user_id（系统管理员）',
    assign_time     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '指派时间',
    status          VARCHAR(20) NOT NULL DEFAULT 'EFFECTIVE' COMMENT '状态：EFFECTIVE=生效 REVOKED=已撤销',
    remark          VARCHAR(200) DEFAULT NULL COMMENT '备注',
    create_time     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (aid),
    KEY idx_assign_order (order_id),
    KEY idx_assign_manager (grid_manager_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='工单指派记录表';


-- ---------------------------------------------------------------------------
-- 12. aqi_measure AQI 实测数据表
--     需求 NO.16/NO.37/NO.38 网格员录入 SO2 / CO / PM2.5，系统自动判定实测 AQI 等级
--     需求 NO.16「同一工单不可重复提交实测数据」→ order_id 建唯一索引
--     需求 NO.50/NO.54 管理端按省市、确认日期筛选，展示确认时间、网格员、反馈者
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS aqi_measure;
CREATE TABLE aqi_measure
(
    mid         BIGINT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    order_id          BIGINT         NOT NULL COMMENT '关联工单 id（feedback_order.oid）',
    province_id       BIGINT         NOT NULL COMMENT '冗余-省 id（统计用）',
    city_id           BIGINT         NOT NULL COMMENT '冗余-市 id（统计用）',
    so2               DECIMAL(10, 2) DEFAULT NULL COMMENT 'SO2 浓度（ug/m³）',
    co                DECIMAL(10, 2) DEFAULT NULL COMMENT 'CO 浓度（ug/m³）',
    pm25              DECIMAL(10, 2) DEFAULT NULL COMMENT 'PM2.5 浓度（ug/m³）',
    measured_level_id BIGINT         NOT NULL COMMENT '自动计算出的实测 AQI 等级 id（aqi_level.lid）',
    grid_manager_id   BIGINT         NOT NULL COMMENT '录入网格员 user_id',
    confirm_date      DATE           NOT NULL COMMENT '确认日期（列表筛选用）',
    confirm_time      DATETIME       NOT NULL COMMENT '确认时间',
    remark            VARCHAR(200)   DEFAULT NULL COMMENT '备注',
    create_time       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time       DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (mid),
    UNIQUE KEY uk_measure_order (order_id),
    KEY idx_measure_confirm_date (confirm_date),
    KEY idx_measure_province_city (province_id, city_id),
    KEY idx_measure_manager (grid_manager_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='AQI 实测数据表';


-- ============================================================================
--  四、统计与大屏
-- ============================================================================

-- ---------------------------------------------------------------------------
-- 13. aqi_statistics 统计数据表（明细，供大屏各类图表调用）
--     需求 NO.55/NO.58：对实测数据做聚合，计算
--       - AQI 等级分布
--       - 各省污染物超标数量
--       - 12 个月超标趋势
--       - 网格覆盖率
--       不同统计口径用 stat_type 区分，写入同一张表，由接口按 type 查询
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS aqi_statistics;
CREATE TABLE aqi_statistics
(
    stid        BIGINT         NOT NULL AUTO_INCREMENT COMMENT '主键',
    stat_type      VARCHAR(30)    NOT NULL COMMENT '统计类型：AQI_LEVEL_DIST=等级分布 PROVINCE_EXCEED=各省超标 MONTH_TREND=12个月趋势 GRID_COVERAGE=网格覆盖率 POLLUTANT_EXCEED=污染物超标',
    stat_dimension VARCHAR(20)    NOT NULL COMMENT '统计维度：PROVINCE/CITY/GRID/MONTH/DAY/ALL',
    dimension_id   BIGINT         DEFAULT NULL COMMENT '维度对象 id（省/市/网格 id，按维度含义使用）',
    dimension_name VARCHAR(100)   DEFAULT NULL COMMENT '维度对象名称（省名/市名/网格名/月份）',
    stat_period    VARCHAR(20)    DEFAULT NULL COMMENT '统计周期，如 2026-10、2026',
    aqi_level_id   BIGINT         DEFAULT NULL COMMENT 'AQI 等级 id（等级分布统计时使用）',
    pollutant      VARCHAR(10)    DEFAULT NULL COMMENT '污染物种类：PM25 / SO2 / CO',
    metric_value   DECIMAL(14, 2) NOT NULL DEFAULT 0 COMMENT '指标值（数量或百分比，按 stat_type 解释）',
    create_time    DATETIME       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
    PRIMARY KEY (stid),
    KEY idx_stat_type_period (stat_type, stat_period),
    KEY idx_stat_dimension (stat_dimension, dimension_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='AQI 统计数据表（供大屏调用）';


-- ---------------------------------------------------------------------------
-- 14. aqi_stat_summary 统计指标汇总表（大屏顶部指标卡片）
--     需求 NO.58：检测总量 / 污染 / 向好数量；网格覆盖率
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS aqi_stat_summary;
CREATE TABLE aqi_stat_summary
(
    smid        BIGINT        NOT NULL AUTO_INCREMENT COMMENT '主键',
    stat_period       VARCHAR(20)   NOT NULL COMMENT '统计周期，如 2026-10',
    measure_total     INT           NOT NULL DEFAULT 0 COMMENT '检测总量',
    pollution_count   INT           NOT NULL DEFAULT 0 COMMENT '污染数量',
    improvement_count INT           NOT NULL DEFAULT 0 COMMENT '向好数量',
    grid_total        INT           NOT NULL DEFAULT 0 COMMENT '网格总数',
    grid_covered      INT           NOT NULL DEFAULT 0 COMMENT '已覆盖网格数',
    grid_coverage     DECIMAL(6, 2) NOT NULL DEFAULT 0 COMMENT '网格覆盖率（%）',
    create_time       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time       DATETIME      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (smid),
    UNIQUE KEY uk_summary_period (stat_period)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='统计指标汇总表（大屏指标卡片）';


-- ============================================================================
--  五、操作日志
-- ============================================================================

-- ---------------------------------------------------------------------------
-- 15. oper_log 操作日志表
--     需求 NO.45/NO.48：「记录操作日志用于追溯」
--     需求 NO.22 管理员可查看各用户的登录时段及操作记录
-- ---------------------------------------------------------------------------
DROP TABLE IF EXISTS oper_log;
CREATE TABLE oper_log
(
    opid        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    user_id       BIGINT       DEFAULT NULL COMMENT '操作人 user_id',
    username      VARCHAR(50)  DEFAULT NULL COMMENT '操作人用户名（冗余，便于直接展示）',
    module        VARCHAR(50)  DEFAULT NULL COMMENT '所属模块，如 反馈工单/指派/核查',
    operation     VARCHAR(100) DEFAULT NULL COMMENT '操作描述，如「指派工单」',
    method        VARCHAR(200) DEFAULT NULL COMMENT '请求接口',
    request_param TEXT         COMMENT '请求参数',
    order_id      BIGINT       DEFAULT NULL COMMENT '关联工单 id（按工单追溯用）',
    ip            VARCHAR(64)  DEFAULT NULL COMMENT '操作 IP',
    status        TINYINT      NOT NULL DEFAULT 1 COMMENT '结果：1=成功 0=失败',
    error_msg     VARCHAR(500) DEFAULT NULL COMMENT '失败原因',
    oper_time     DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (opid),
    KEY idx_log_order (order_id),
    KEY idx_log_user (user_id),
    KEY idx_log_time (oper_time)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='操作日志表';


-- ============================================================================
--  六、可选扩展（需求 NO.6~10「权限管理」如需数据库驱动菜单/按钮权限，再启用）
--      sys_menu / sys_role_menu —— 当前需求由前端 Vue 路由 + 后端接口校验实现，
--      故不建表。若后续要做动态菜单，可在此处补充。
-- ============================================================================


SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================================
--  建表完成，共 15 张表
--    字典：sys_role / aqi_level / sys_province / sys_city / sys_district / sys_grid
--    用户：sys_user / supervisor_info / grid_manager_info
--    业务：feedback_order / feedback_assign / aqi_measure
--    统计：aqi_statistics / aqi_stat_summary
--    日志：oper_log
-- ============================================================================
