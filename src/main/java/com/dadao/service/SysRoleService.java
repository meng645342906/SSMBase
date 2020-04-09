package com.dadao.service;

import com.dadao.bean.SysRole;
import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.PagePara;
import com.dadao.core.util.view.InvokeResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface SysRoleService extends BaseService<SysRole> {
    InvokeResult setVisible(String bids, Integer visible);
    
    InvokeResult getZtreeViewList(Integer type, Integer roleId);
    
    JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBys);
    
    InvokeResult saveMenuAssign(String menuIds, Integer roleId);
    
    List<SysRole> getSysRoleNameList();
}
