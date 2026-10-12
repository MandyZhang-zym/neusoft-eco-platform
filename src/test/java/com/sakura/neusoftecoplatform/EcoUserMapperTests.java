package com.sakura.neusoftecoplatform;

import com.sakura.neusoftecoplatform.entity.EcoUser;
import com.sakura.neusoftecoplatform.mapper.EcoUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 验证数据库连通性的冒烟测试：
 * 启动 Spring 上下文 → 自动建表/插示例数据 → 用 Mapper 查出来。
 * 跑通说明 MySQL 连接、MyBatis-Plus 映射、建表脚本都正常。
 */
@SpringBootTest
class EcoUserMapperTests {

    @Autowired
    private EcoUserMapper ecoUserMapper;

    @Test
    void selectAllReturnsSeedData() {
        List<EcoUser> users = ecoUserMapper.selectList(null);
        assertNotNull(users);
        assertFalse(users.isEmpty(),
                "eco_user 表里没有数据，先检查 application-local.properties 里的密码是否改成你本机的密码");
        users.forEach(System.out::println);
    }
}
