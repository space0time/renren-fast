package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.annotation.DataFilter;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUsergroupRoleDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUsergroupRoleEntity;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUsergroupRoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysUsergroupRoleService")
public class SysUsergroupRoleServiceImpl extends ServiceImpl<SysUsergroupRoleDao, SysUsergroupRoleEntity> implements SysUsergroupRoleService {

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysDeptService sysDeptService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysUsergroupRoleEntity> page = this.selectPage(
                new Query<SysUsergroupRoleEntity>(params).getPage(),
                new EntityWrapper<SysUsergroupRoleEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 查询指定用户组关联的角色
     * @param params
     * @return
     */
    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryUgRolePage(Map<String, Object> params) {
        String ugId = (String)params.get("ugId");
        String roleName = (String)params.get("rolename");

        Page<SysRoleEntity> page = sysRoleService.selectPage(
                new Query<SysRoleEntity>(params).getPage(),
                new EntityWrapper<SysRoleEntity>()
                        .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                        .addFilter("exists (select * from sys_usergroup p,sys_usergroup_role u where p.ug_id=u.ug_id and u.role_id=sys_role.role_id and p.ug_id={0})",ugId)
        );

        for(SysRoleEntity sysRoleEntity : page.getRecords()){
            SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysRoleEntity.getDeptId());
            if(sysDeptEntity != null){
                sysRoleEntity.setDeptName(sysDeptEntity.getName());
            }
        }

        return new PageUtils(page);
    }

    /**
     * 删除用户组和角色的关联
     * @param params
     */
    @Override
    public void deleteUgRole(Map<String, Object> params) {
        String ugId = (String)params.get("ugId");
        List roleIds = (List)params.get("roleIds");
        this.delete(new EntityWrapper<SysUsergroupRoleEntity>()
                .eq("ug_id", ugId)
                .in("role_id",roleIds)
        );
    }

}
