package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.AqiStatSummary;
import org.apache.ibatis.annotations.Mapper;

/**
 * 统计指标汇总表（大屏指标卡片） 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface AqiStatSummaryMapper extends BaseMapper<AqiStatSummary> {
}
