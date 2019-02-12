package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUsergroupUserEntity;

import java.util.Map;

/**
 * 用户组用户对应关系
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 10:01:47
 */
public interface SysUsergroupUserService extends IService<SysUsergroupUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryUgUserPage(Map<String, Object> params);

    void deleteUgUser(Map<String, Object> params);

}

