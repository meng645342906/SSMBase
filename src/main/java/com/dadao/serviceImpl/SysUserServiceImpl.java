package com.dadao.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysRes;
import com.dadao.bean.SysRole;
import com.dadao.bean.SysUser;
import com.dadao.bean.SysUserRole;
import com.dadao.core.util.CommonUtils;
import com.dadao.core.util.IWebUtils;
import com.dadao.core.util.MyDigestUtils;
import com.dadao.core.util.StringUtils;
import com.dadao.core.util.view.InvokeResult;
import com.dadao.core.util.view.ZtreeView;
import com.dadao.dao.SysRoleMapper;
import com.dadao.dao.SysUserMapper;
import com.dadao.dao.SysUserRoleMapper;
import com.dadao.service.SysResService;
import com.dadao.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserMapper> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysResService sysResService;
    
    @Override
    public List<SysUser> selectAll() {
        return null;
    }
    
    @Override
    public InvokeResult setVisible(String bids, Integer visible) {
        List<Integer> ids = new ArrayList<>();
        if (bids.contains(",")) {
            for (String aid : bids.split(",")) {
                if (StringUtils.notBlank(aid)) {
                    ids.add(Integer.valueOf(aid));
                }
            }
        } else {
            if (StringUtils.notBlank(bids)) {
                ids.add(Integer.valueOf(bids));
            }
        }
        if (bids.lastIndexOf(",")>-1) {
            bids = bids.substring(0, bids.length() - 1);
        }
        if (ids.contains(1)) {
            return InvokeResult.failure("超级管理员不能被修改");
        }
        sysUserMapper.setVisible(visible, bids);
        return InvokeResult.success();
        
    }
    
    @Override
    public SysUser selectByPrimaryKey(Integer id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Integer updateByPrimaryKey(SysUser sysUser) {
        return sysUserMapper.updateByPrimaryKey(sysUser);
    }
    
    @Override
    public Integer insert(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }
    
    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        sysUserMapper.deleteByPrimaryKey(id);
        sysUserRoleMapper.deleteByUserId(id);
        return true;
        
    }
    
    /**
     * 登录操作
     *
     * @param session
     * @param name
     * @param pwd
     * @return
     */
    @Override
    public InvokeResult doLogin(HttpSession session, String name, String pwd) {
        SysUser sysUser = this.doLogin(name, MyDigestUtils.shaDigestForPasswrod(pwd));
        if (sysUser == null) {
            return InvokeResult.failure("用户名或密码不对");
        }
        if (sysUser.getStatus() == 2) {
            return InvokeResult.failure("用户被冻结，请联系管理员");
        }
        IWebUtils.setCurrentLoginSysUserToSession(session, sysUser);
        
        return InvokeResult.success();
    }
    
    
    /**
     * 登录操作
     *
     * @param name
     * @param pwd
     * @return
     */
    public SysUser doLogin(String name, String pwd) {
        return sysUserMapper.selectByUserAndPwd(name, pwd);
    }
    
    /**
     * 修改密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    @Override
    public InvokeResult savePwdUpdate(Integer id, String newPwd) {
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
        if (sysUser != null) {
            sysUserMapper.savePwdUpdate(new SysUser(id, newPwd));
            return InvokeResult.success("修改成功");
        } else {
            return InvokeResult.failure(-2, "修改失败");
        }
    }
    
    /**
     * 获取权限集
     *
     * @param id
     * @return
     */
    @Override
    public Set<String> getPermissionSets(Integer id) {
        
        List<SysRes> list = sysResService.getSysResList(id, null);
        Set<String> sets = new HashSet<String>();
        for (SysRes item : list) {
            sets.add(item.getUrl());
        }
        return sets;
    }
    
    @Override
    public InvokeResult getRoleZtreeViewList(Integer uid) {
        List<SysRole> list = sysRoleMapper.getSysRoles(uid);
        List<ZtreeView> ztreeViews = new ArrayList<>();
        ztreeViews.add(new ZtreeView(10000, null, "角色列表", true));
        for (SysRole role : list) {
            ZtreeView ztreeView = new ZtreeView();
            ztreeView.setId(role.getId());
            ztreeView.setName(role.getName());
            ztreeView.setOpen(true);
            ztreeView.setpId(10000);
            ztreeView.setChecked(role.getSelected() == 1);
            ztreeViews.add(ztreeView);
        }
        return InvokeResult.success(JSON.toJSONString(ztreeViews));
    }
    
    /**
     * 修改用户角色
     *
     * @param uid
     * @param roleIds
     * @return
     */
    @Override
    public InvokeResult changeUserRoles(Integer uid, String roleIds) {
        sysUserRoleMapper.deleteByUserId(uid);
        List<SysUserRole> sysUserRoleList = new ArrayList<>();
        for (String id : roleIds.split(",")) {
            if (CommonUtils.isNotEmpty(id)) {
                if (Integer.valueOf(id) != 10000)
                    sysUserRoleList.add(new SysUserRole(uid, Integer.valueOf(id)));
            }
        }
        sysUserRoleMapper.insertByBatch(sysUserRoleList);
        return InvokeResult.success();
    }
    
}
