package com.mxt.service;

import com.mxt.bean.SysRes;
import com.mxt.core.util.view.ZtreeView;

import java.util.List;

public interface SysResService extends BaseService<SysRes> {
    List<SysRes> selectAll();
    
    String getSysUserMenuView(Integer uid, String url);
    
    Integer insert(SysRes sysRes);
    
    SysRes selectByPrimaryKey(Integer id);
    
    void deleteByResId(Integer id);
    
    String getTreeGridView();
    
    List<ZtreeView> getZtreeViewList();
    
    List<SysRes> getSysResList(int uid, Integer type);
    
    Integer setEnabled(Integer resId, Integer status);
    
    void deleteRes(Integer id);
    
}
