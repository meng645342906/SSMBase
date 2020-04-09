package com.dadao.dao;

import com.dadao.bean.DictData;

import java.util.List;

public interface DictDataMapper extends BaseMapper<DictData> {
    int deleteByPrimaryKey(Integer id);
    
    int insert(DictData record);
    
    DictData selectByPrimaryKey(Integer id);
    
    List<DictData> selectAll();
    
    int updateByPrimaryKey(DictData record);
    
    int deleteByTypeId(Integer id);
}