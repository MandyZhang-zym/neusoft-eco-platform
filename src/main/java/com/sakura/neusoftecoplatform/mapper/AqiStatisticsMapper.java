package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.AqiStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * AQI 统计数据表（供大屏调用） 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface AqiStatisticsMapper extends BaseMapper<AqiStatistics> {
}
