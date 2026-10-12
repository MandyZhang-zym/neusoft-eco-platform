package com.sakura.neusoftecoplatform;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.sakura.neusoftecoplatform.entity.FeedbackOrder;
import com.sakura.neusoftecoplatform.mapper.AqiLevelMapper;
import com.sakura.neusoftecoplatform.mapper.AqiMeasureMapper;
import com.sakura.neusoftecoplatform.mapper.FeedbackOrderMapper;
import com.sakura.neusoftecoplatform.mapper.SysGridMapper;
import com.sakura.neusoftecoplatform.mapper.SysRoleMapper;
import com.sakura.neusoftecoplatform.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 数据库初始化冒烟测试：
 * 启动 Spring 上下文 → spring.sql.init 自动执行 schema.sql / data.sql → 用 Mapper 校验数据。
 * 跑通说明「建表 + 字典 + 演示数据 + MyBatis-Plus 映射」整条链路正常。
 */
@SpringBootTest
class DatabaseInitTests {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private AqiLevelMapper aqiLevelMapper;
    @Autowired
    private SysGridMapper sysGridMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private FeedbackOrderMapper feedbackOrderMapper;
    @Autowired
    private AqiMeasureMapper aqiMeasureMapper;

    /** 字典表：4 种角色、6 个 AQI 等级 */
    @Test
    void dictionaryTablesAreInitialized() {
        assertEquals(4L, sysRoleMapper.selectCount(null).longValue(), "sys_role 应有 4 条角色");
        assertEquals(6L, aqiLevelMapper.selectCount(null).longValue(), "aqi_level 应有 6 个等级");
    }

    /** 演示数据：6 个账号、12 个网格 */
    @Test
    void demoDataIsLoaded() {
        assertEquals(6L, sysUserMapper.selectCount(null).longValue(), "sys_user 应有 6 个演示账号");
        assertEquals(12L, sysGridMapper.selectCount(null).longValue(), "sys_grid 应有 12 个网格");
    }

    /** 工单流转：待指派 / 已指派 / 已核查 三种状态都存在，且已核查工单都有实测数据 */
    @Test
    void workOrderFlowIsConsistent() {
        long pending = feedbackOrderMapper.selectCount(
                Wrappers.<FeedbackOrder>lambdaQuery().eq(FeedbackOrder::getStatus, "PENDING_ASSIGN"));
        long assigned = feedbackOrderMapper.selectCount(
                Wrappers.<FeedbackOrder>lambdaQuery().eq(FeedbackOrder::getStatus, "ASSIGNED"));
        long checked = feedbackOrderMapper.selectCount(
                Wrappers.<FeedbackOrder>lambdaQuery().eq(FeedbackOrder::getStatus, "CHECKED"));

        assertTrue(pending > 0, "应存在【待指派】工单");
        assertTrue(assigned > 0, "应存在【已指派】工单");
        assertTrue(checked > 0, "应存在【已核查】工单");
        assertEquals(checked, aqiMeasureMapper.selectCount(null).longValue(),
                "已核查工单数应等于实测数据条数（一个工单一条实测）");

        List<FeedbackOrder> sample = feedbackOrderMapper.selectList(
                Wrappers.<FeedbackOrder>lambdaQuery().eq(FeedbackOrder::getStatus, "CHECKED").last("LIMIT 3"));
        sample.forEach(System.out::println);
    }
}
