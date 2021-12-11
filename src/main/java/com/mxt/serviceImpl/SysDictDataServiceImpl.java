package com.mxt.serviceImpl;

import com.mxt.bean.DictData;
import com.mxt.dao.DictDataMapper;
import com.mxt.service.SysDictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sysDictDataService")
public class SysDictDataServiceImpl extends BaseServiceImpl<DictData, DictDataMapper> implements SysDictDataService {
    @Resource
    private DictDataMapper dictDataMapper;
    @Override
    public int deleteByTypeId(Integer typeId) {
       return dictDataMapper.deleteByTypeId(typeId);
    }
}
