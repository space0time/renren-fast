package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.annotation.DataFilter;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUsergroupUserDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUsergroupUserEntity;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.SysUsergroupUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysUsergroupUserService")
public class SysUsergroupUserServiceImpl extends ServiceImpl<SysUsergroupUserDao, SysUsergroupUserEntity> implements SysUsergroupUserService {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysDeptService sysDeptService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysUsergroupUserEntity> page = this.selectPage(
                new Query<SysUsergroupUserEntity>(params).getPage(),
                new EntityWrapper<SysUsergroupUserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询指定用户组关联的用户
     * @param params
     * @return
     */
    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryUgUserPage(Map<String, Object> params) {
        String ugId = (String)params.get("ugId");
        String userName = (String)params.get("username");
        Page<SysUserEntity> page = sysUserService.selectPage(
                new Query<SysUserEntity>(params).getPage(),
                new EntityWrapper<SysUserEntity>()
                        .like(StringUtils.isNotBlank(userName),"username", userName)
                        .addFilter("exists (select * from sys_usergroup p,sys_usergroup_user u where p.ug_id=u.ug_id and u.user_id=sys_user.user_id and p.ug_id={0})", ugId)
        );
        for(SysUserEntity sysUserEntity : page.getRecords()){
            SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysUserEntity.getDeptId());
            sysUserEntity.setDeptName(sysDeptEntity.getName());
        }
        return new PageUtils(page);
    }

    /**
     * 删除用户组和用户的关联
     * @param params
     */
    @Override
    public void deleteUgUser(Map<String, Object> params) {
        String ugId = (String)params.get("ugId");
        List userIds = (List)params.get("userIds");
        this.delete(new EntityWrapper<SysUsergroupUserEntity>()
                .eq("ug_id", ugId)
                .in("user_id",userIds)
        );
    }

}
