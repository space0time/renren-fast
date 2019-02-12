package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUsergroupEntity;

import java.util.Map;

/**
 * 用户组
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 09:45:05
 */
public interface SysUsergroupService extends IService<SysUsergroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void save(SysUsergroupEntity userGroup);

    PageUtils queryUserPage(Map<String, Object> params);

    PageUtils queryRolePage(Map<String, Object> params);

}

