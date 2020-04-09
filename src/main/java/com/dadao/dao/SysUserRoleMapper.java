package com.dadao.dao;

import com.dadao.bean.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(SysUserRole record);
    
    SysUserRole selectByPrimaryKey(Integer id);
    
    List<SysUserRole> selectAll();
    
    int updateByPrimaryKey(SysUserRole record);
    
    Integer deleteByUserId(Integer id);
    
    Integer deleteByRoleId(Integer id);
    
    void insertByBatch(List<SysUserRole> sysUserRoleList);
}