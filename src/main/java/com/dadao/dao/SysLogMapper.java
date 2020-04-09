package com.dadao.dao;

import com.dadao.bean.SysLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper extends BaseMapper<SysLog> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(SysLog record);
    
    SysLog selectByPrimaryKey(Integer id);
    
    List<SysLog> selectAll();
    
    List<SysLog> selectByPara(@Param("para") String para, @Param("orderBy") String orderBy,@Param("page") String page);
    
    Integer selectCount();
    
    Integer removeAllLog();
}