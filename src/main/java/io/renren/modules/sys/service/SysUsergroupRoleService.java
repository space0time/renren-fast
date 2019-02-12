package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUsergroupRoleEntity;

import java.util.Map;

/**
 * 用户组角色对应关系
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 10:01:47
 */
public interface SysUsergroupRoleService extends IService<SysUsergroupRoleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUgRolePage(Map<String, Object> params);

    void deleteUgRole(Map<String, Object> params);
}

