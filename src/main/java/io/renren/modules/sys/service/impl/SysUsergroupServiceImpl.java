package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.annotation.DataFilter;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.dao.SysUsergroupDao;
import io.renren.modules.sys.entity.SysDeptEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUsergroupEntity;
import io.renren.modules.sys.service.SysDeptService;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.SysUsergroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;


@Service("sysUsergroupService")
public class SysUsergroupServiceImpl extends ServiceImpl<SysUsergroupDao, SysUsergroupEntity> implements SysUsergroupService {

    @Autowired
    private SysDeptService sysDeptService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 分页查询用户组
     * @param params
     * @return
     */
    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryPage(Map<String, Object> params) {
        String userGroupName = (String)params.get("ugName");
        Page<SysUsergroupEntity> page = this.selectPage(
                new Query<SysUsergroupEntity>(params).getPage(),
                new EntityWrapper<SysUsergroupEntity>()
                .like(StringUtils.isNotBlank(userGroupName), "ug_name", userGroupName)
                .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );

        for(SysUsergroupEntity sysUsergroupEntity : page.getRecords()) {
            SysDeptEntity sysDeptEntity = sysDeptService.selectById(sysUsergroupEntity.getDeptId());
            if(sysDeptEntity != null){
                sysUsergroupEntity.setDeptName(sysDeptEntity.getName());
            }
        }

        return new PageUtils(page);
    }

    /**
     * 新增保存
     * @param userGroup
     */
    @Override
    public void save(SysUsergroupEntity userGroup) {

        userGroup.setCreateTime(new Date());

        this.insert(userGroup);
    }

    /**
     * 分页查询用户组中不存在的用户
     * @param params
     * @return
     */
    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryUserPage(Map<String, Object> params) {

        String username = (String)params.get("username");
        String deptId = (String)params.get("deptId");
        String ugId = (String)params.get("ugId");

        Page<SysUserEntity> page = sysUserService.selectPage(
                new Query<SysUserEntity>(params).getPage(),
                new EntityWrapper<SysUserEntity>()
                        .like(StringUtils.isNotBlank(username),"username", username)
                        .eq(StringUtils.isNotBlank(deptId), "dept_id", deptId)
                        .addFilter("not exists(select * from sys_usergroup_user r where r.user_id=sys_user.user_id and r.ug_id={0})", ugId)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );


        return new PageUtils(page);
    }

    /**
     * 分页查询用户组中不存在的角色
     * @param params
     * @return
     */
    @Override
    @DataFilter(subDept = true, user = false)
    public PageUtils queryRolePage(Map<String, Object> params) {

        String roleName = (String)params.get("roleName");
        String deptId = (String)params.get("deptId");
        String ugId = (String)params.get("ugId");

        Page<SysRoleEntity> page = sysRoleService.selectPage(
                new Query<SysRoleEntity>(params).getPage(),
                new EntityWrapper<SysRoleEntity>()
                        .like(StringUtils.isNotBlank(roleName), "role_name", roleName)
                        .eq(StringUtils.isNotBlank(deptId), "dept_id", deptId)
                        .addFilter("not exists(select * from sys_usergroup_role r where r.role_id=sys_role.role_id and r.ug_id={0})", ugId)
                        .addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
        );


        return new PageUtils(page);
    }

}
