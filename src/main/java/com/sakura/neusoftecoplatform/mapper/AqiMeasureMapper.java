package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.AqiMeasure;
import org.apache.ibatis.annotations.Mapper;

/**
 * AQI 实测数据表 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface AqiMeasureMapper extends BaseMapper<AqiMeasure> {
}
