package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.EcoUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * eco_user 表的 Mapper。
 * 继承 BaseMapper 后即可直接使用 selectList / selectById / insert / updateById / deleteById 等方法。
 */
@Mapper
public interface EcoUserMapper extends BaseMapper<EcoUser> {
}
