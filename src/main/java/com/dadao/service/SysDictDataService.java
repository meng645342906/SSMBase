package com.dadao.service;

import com.dadao.bean.DictData;

public interface SysDictDataService extends BaseService<DictData> {
    
    int deleteByTypeId(Integer id);
}
