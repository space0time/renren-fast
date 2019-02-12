package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.SysUsergroupUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户组用户对应关系
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 10:01:47
 */
@Mapper
public interface SysUsergroupUserDao extends BaseMapper<SysUsergroupUserEntity> {

}
