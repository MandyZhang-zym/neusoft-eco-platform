package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.GridManagerInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 网格员信息表 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface GridManagerInfoMapper extends BaseMapper<GridManagerInfo> {
}
