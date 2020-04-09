package com.dadao.serviceImpl;

import com.dadao.bean.DictType;
import com.dadao.dao.DictTypeMapper;
import com.dadao.service.SysDictTypeService;
import org.springframework.stereotype.Service;

@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends BaseServiceImpl<DictType, DictTypeMapper> implements SysDictTypeService {
}
