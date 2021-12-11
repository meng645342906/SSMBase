package com.mxt.dao;

import com.mxt.bean.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(SysRole record);
    
    SysRole selectByPrimaryKey(Integer id);
    
    List<SysRole> selectAll();
    
    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> getSysRoleIdList(Integer uid);
    
    List<SysRole> selectByPara(@Param("para") String para, @Param("orderBy") String orderBy, @Param("page") String page);
    
    Integer selectCount();
    
    Integer setVisible(@Param("status") Integer status, @Param("ids") String ids);
    
    List<SysRole> getSysRoleNamelist();
    
    List<SysRole> getSysRoles(Integer uid);
    
}