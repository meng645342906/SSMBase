package com.mxt.serviceImpl;

import com.mxt.bean.DictType;
import com.mxt.dao.DictTypeMapper;
import com.mxt.service.SysDictTypeService;
import org.springframework.stereotype.Service;

@Service("sysDictTypeService")
public class SysDictTypeServiceImpl extends BaseServiceImpl<DictType, DictTypeMapper> implements SysDictTypeService {
}
