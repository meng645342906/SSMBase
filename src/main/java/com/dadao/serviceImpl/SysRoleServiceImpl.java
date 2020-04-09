package com.dadao.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysRes;
import com.dadao.bean.SysRole;
import com.dadao.bean.SysRoleRes;
import com.dadao.core.util.CommonUtils;
import com.dadao.core.util.StringUtils;
import com.dadao.core.util.view.InvokeResult;
import com.dadao.core.util.view.ZtreeView;
import com.dadao.dao.SysResMapper;
import com.dadao.dao.SysRoleMapper;
import com.dadao.dao.SysRoleResMapper;
import com.dadao.dao.SysUserRoleMapper;
import com.dadao.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("sysRoleService")
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, SysRoleMapper> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysResMapper sysResMapper;
    @Resource
    private SysRoleResMapper sysRoleResMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    
    
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
        if (ids.contains(1)) {
            return InvokeResult.failure("超级管理员不能被修改");
        }
        if (bids.lastIndexOf(",") > -1) {
            bids = bids.substring(0, bids.length() - 1);
        }
        
        sysRoleMapper.setVisible(visible, bids);
        return InvokeResult.success();
    }
    
    @Override
    public InvokeResult getZtreeViewList(Integer type, Integer roleId) {
        List<SysRes> curMenuList = this.getSysMenus(roleId);
        List<ZtreeView> zTreeViews = new ArrayList<>();
        zTreeViews.add(new ZtreeView(10000, null, "资源列表", true));
        for (SysRes SysRes : curMenuList) {
            ZtreeView ztreeView = new ZtreeView();
            ztreeView.setId(SysRes.getId());
            ztreeView.setName(SysRes.getName());
            ztreeView.setOpen(true);
            if (SysRes.getPid() == null) {
                ztreeView.setpId(10000);
            } else {
                ztreeView.setpId(SysRes.getPid());
            }
            ztreeView.setChecked(SysRes.getSelected() == 1);
            zTreeViews.add(ztreeView);
        }
        return InvokeResult.success(JSON.toJSONString(zTreeViews));
    }
    
    /**
     * 查询系统菜单
     *
     * @param roleId
     * @return
     */
    public List<SysRes> getSysMenus(Integer roleId) {
        SysRole role = sysRoleMapper.selectByPrimaryKey(roleId);
        if (role != null) {
            return sysResMapper.getSysMenus(roleId);
        }
        return new ArrayList<SysRes>();
    }
    
    /**
     * 保存菜单分配
     *
     * @param menuIds
     * @param roleId
     * @return
     */
    @Override
    public InvokeResult saveMenuAssign(String menuIds, Integer roleId) {
        if (roleId == 1) {
            return InvokeResult.failure("超级管理员不能被修改");
        }
        sysRoleResMapper.deleteByRoleId(roleId);
        if (CommonUtils.isNotEmpty(menuIds)) {// 改成批量插入
            List<SysRoleRes> sysRoleResList = new ArrayList<>();
            for (String id : menuIds.split(",")) {
                if (CommonUtils.isNotEmpty(id)) {
                    if (Integer.valueOf(id) != 10000)
                        sysRoleResList.add(new SysRoleRes(Integer.valueOf(id), roleId));
                }
            }
            sysRoleResMapper.insertByBatch(sysRoleResList);
        }
        return InvokeResult.success();
    }
    
    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        sysRoleMapper.deleteByPrimaryKey(id);
        sysRoleResMapper.deleteByRoleId(id);
        sysUserRoleMapper.deleteByRoleId(id);
        return true;
    }
    
    @Override
    public List<SysRole> getSysRoleNameList() {
        return sysRoleMapper.getSysRoleNamelist();
    }
}
