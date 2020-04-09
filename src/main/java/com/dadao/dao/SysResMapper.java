package com.dadao.dao;

import com.dadao.bean.SysRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResMapper extends BaseMapper<SysRes> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(SysRes record);
    
    SysRes selectByPrimaryKey(Integer id);
    
    List<SysRes> selectAll();
    
    int updateByPrimaryKey(SysRes record);
    
    List<SysRes> findResByRoleId(String ids, Integer type);
    
    List<SysRes> findResByAdmin(@Param("type") Integer type);
    
    SysRes getByUrl(String url);
    
    List<SysRes> getSysMenus(Integer roleId);
}