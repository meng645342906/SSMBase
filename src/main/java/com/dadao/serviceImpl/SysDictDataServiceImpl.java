package com.dadao.serviceImpl;

import com.dadao.bean.DictData;
import com.dadao.dao.DictDataMapper;
import com.dadao.service.SysDictDataService;
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
