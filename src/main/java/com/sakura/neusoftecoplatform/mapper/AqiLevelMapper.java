package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.AqiLevel;
import org.apache.ibatis.annotations.Mapper;

/**
 * 空气质量指数等级分类表 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface AqiLevelMapper extends BaseMapper<AqiLevel> {
}
