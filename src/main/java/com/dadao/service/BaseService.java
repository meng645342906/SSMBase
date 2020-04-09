package com.dadao.service;

import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.PagePara;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface BaseService<T> {
    Boolean deleteByPrimaryKey(Integer id);
    
    Integer insert(T record);
    
    T selectByPrimaryKey(Integer id);
    
    List<T> selectAll();
    
    Integer updateByPrimaryKey(T record);
    
    Integer updateByPrimaryKeySelective(T record);
    
    JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBys);
}
