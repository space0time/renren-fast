package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysUsergroupRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户组角色对应关系
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 10:01:47
 */
@Mapper
public interface SysUsergroupRoleDao extends BaseMapper<SysUsergroupRoleEntity> {

}
