package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.entity.SysUsergroupEntity;
import io.renren.modules.sys.entity.SysUsergroupRoleEntity;
import io.renren.modules.sys.entity.SysUsergroupUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.renren.modules.sys.service.SysUsergroupRoleService;
import io.renren.modules.sys.service.SysUsergroupService;
import io.renren.modules.sys.service.SysUsergroupUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 用户组
 *
 * @author JackLee
 * @email
 * @date 2018-11-08 09:45:05
 */
@RestController
@RequestMapping("sys/usergroup")
public class SysUsergroupController {
    @Autowired
    private SysUsergroupService sysUsergroupService;

    @Autowired
    private SysUsergroupUserService sysUsergroupUserService;

    @Autowired
    private SysUsergroupRoleService sysUsergroupRoleService;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 所有用户列表
     */
    @RequestMapping("/userList1")
    @RequiresPermissions("sys:user:list")
    public R userList1(@RequestParam Map<String, Object> params){
        PageUtils page = sysUserService.queryPage(params);

        return R.ok().put("count", page.getTotalCount()).put("data",page.getList());
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:usergroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysUsergroupService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{ugId}")
    @RequiresPermissions("sys:usergroup:info")
    public R info(@PathVariable("ugId") Long ugId){
        SysUsergroupEntity sysUsergroup = sysUsergroupService.selectById(ugId);

        return R.ok().put("sysUsergroup", sysUsergroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:usergroup:save")
    public R save(@RequestBody SysUsergroupEntity sysUsergroup){
        ValidatorUtils.validateEntity(sysUsergroup);

        sysUsergroupService.save(sysUsergroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:usergroup:update")
    public R update(@RequestBody SysUsergroupEntity sysUsergroup){
        ValidatorUtils.validateEntity(sysUsergroup);
        sysUsergroupService.updateById(sysUsergroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:usergroup:delete")
    @Transactional
    public R delete(@RequestBody Long[] ugIds){
        //删除主表
        sysUsergroupService.deleteBatchIds(Arrays.asList(ugIds));
        //删除子表
        sysUsergroupUserService.delete(new EntityWrapper<SysUsergroupUserEntity>().in("ug_id", ugIds));
        //删除子表
        sysUsergroupRoleService.delete(new EntityWrapper<SysUsergroupRoleEntity>().in("ug_id", ugIds));

        return R.ok();
    }

    /**
     * 所有用户列表
     */
    @RequestMapping("/userList")
    @RequiresPermissions("sys:user:list")
    public R userList(@RequestParam Map<String, Object> params){
        PageUtils page = sysUsergroupService.queryUserPage(params);

        return  R.ok().put("page", page);
    }

    /**
     * 保存用户组用户
     */
    @RequestMapping("/ugUser/save")
    @RequiresPermissions("sys:usergroup:save")
    public R saveUgUser(@RequestBody List<SysUsergroupUserEntity> list){

        sysUsergroupUserService.insertOrUpdateBatch(list);

        return R.ok();
    }

    /**
     * 用户组用户列表
     * @param params
     * @return
     */
    @RequestMapping("/ugUser/list")
    @RequiresPermissions("sys:usergroup:list")
    public R listUgUser(@RequestParam Map<String, Object> params){

        PageUtils page = sysUsergroupUserService.queryUgUserPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 用户组用户删除
     */
    @RequestMapping("/ugUser/delete")
    @RequiresPermissions("sys:usergroup:delete")
    public R deleteUgUser(@RequestBody Map<String, Object> params){

        sysUsergroupUserService.deleteUgUser(params);

        return R.ok();
    }

    /**
     * 所有角色列表
     */
    @RequestMapping("/roleList")
    @RequiresPermissions("sys:user:list")
    public R roleList(@RequestParam Map<String, Object> params){
        PageUtils page = sysUsergroupService.queryRolePage(params);

        return  R.ok().put("page", page);
    }

    /**
     * 保存用户组用户
     */
    @RequestMapping("/ugRole/save")
    @RequiresPermissions("sys:usergroup:save")
    public R saveUgRole(@RequestBody List<SysUsergroupRoleEntity> list){

        sysUsergroupRoleService.insertOrUpdateBatch(list);

        return R.ok();
    }

    /**
     * 用户组角色删除
     */
    @RequestMapping("/ugRole/delete")
    @RequiresPermissions("sys:usergroup:delete")
    public R deleteUgRole(@RequestBody Map<String, Object> params){

        sysUsergroupRoleService.deleteUgRole(params);

        return R.ok();
    }

    /**
     * 用户组角色列表
     * @param params
     * @return
     */
    @RequestMapping("/ugRole/list")
    @RequiresPermissions("sys:usergroup:list")
    public R listUgRole(@RequestParam Map<String, Object> params){

        PageUtils page = sysUsergroupRoleService.queryUgRolePage(params);

        return R.ok().put("page", page);
    }
}
