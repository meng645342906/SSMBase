package com.dadao.dao;

import com.dadao.bean.DictType;

import java.util.List;

public interface DictTypeMapper extends BaseMapper<DictType> {
    int deleteByPrimaryKey(Integer id);

    int insert(DictType record);

    DictType selectByPrimaryKey(Integer id);

    List<DictType> selectAll();

    int updateByPrimaryKey(DictType record);
}