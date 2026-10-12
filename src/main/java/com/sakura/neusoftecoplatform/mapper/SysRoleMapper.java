package com.sakura.neusoftecoplatform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sakura.neusoftecoplatform.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表 的 Mapper
 * <p>继承 BaseMapper 后即可使用 selectList / selectById / insert / updateById / deleteById 等</p>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
}
