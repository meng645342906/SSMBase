package com.mxt.service;

import com.mxt.bean.DictData;

public interface SysDictDataService extends BaseService<DictData> {
    
    int deleteByTypeId(Integer id);
}
