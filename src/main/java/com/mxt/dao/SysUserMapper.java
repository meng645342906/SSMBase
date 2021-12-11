package com.mxt.dao;

import com.mxt.bean.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);
    
    SysUser selectByUserAndPwd(String name, String pwd);
    
    int savePwdUpdate(SysUser sysUser);
    
    void setVisible(@Param("visible") Integer visible, @Param("ids") String ids);
    
    List<SysUser> selectByPara(@Param("para") String para, @Param("orderBy") String orderBy, @Param("page") String page);
    
    Integer selectCount();
}