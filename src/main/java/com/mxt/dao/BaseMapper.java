package com.mxt.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMapper<T> {
    
    int deleteByPrimaryKey(Integer id);
    
    int insert(T record);
    
    T selectByPrimaryKey(Integer id);
    
    List<T> selectAll();
    
    List<T> selectByPara(@Param("para") String para, @Param("orderBy") String orderBy, @Param("page") String page);
    
    Integer selectCount();
    
    int updateByPrimaryKey(T record);
    
    int updateByPrimaryKeySelective(T record);
}
