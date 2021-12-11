package com.mxt.service;

import com.mxt.bean.SysUser;
import com.mxt.core.util.page.Condition;
import com.mxt.core.util.page.JqGridModel;
import com.mxt.core.util.page.PagePara;
import com.mxt.core.util.view.InvokeResult;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Set;

public interface SysUserService extends BaseService<SysUser> {
    InvokeResult doLogin(HttpSession httpSession, String name, String pwd);
    
    InvokeResult savePwdUpdate(Integer id, String newPwd);
    
    Set<String> getPermissionSets(Integer id);
    
    JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBy);
    
    InvokeResult setVisible(String ids, Integer visible);
    
    SysUser selectByPrimaryKey(Integer id);
    
    Integer updateByPrimaryKey(SysUser sysUser);
    
    Integer insert(SysUser sysUser);
    
    Boolean deleteByPrimaryKey(Integer id);
    
    InvokeResult getRoleZtreeViewList(Integer uid);
    
    InvokeResult changeUserRoles(Integer id, String roleIds);
}
