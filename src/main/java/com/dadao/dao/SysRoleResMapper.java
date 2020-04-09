package com.dadao.dao;

import com.dadao.bean.SysRoleRes;

import java.util.List;

public interface SysRoleResMapper extends BaseMapper<SysRoleRes> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(SysRoleRes record);
    
    SysRoleRes selectByPrimaryKey(Integer id);
    
    List<SysRoleRes> selectAll();
    
    int updateByPrimaryKey(SysRoleRes record);
    
    Integer deleteByResId(Integer id);
    
    Integer deleteByRoleId(Integer id);
    
    void insertByBatch(List<SysRoleRes> sysRoleResList);
}