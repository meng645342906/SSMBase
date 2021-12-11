package com.mxt.serviceImpl;

import com.mxt.bean.SysLog;
import com.mxt.dao.SysLogMapper;
import com.mxt.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sysLogService")
public class SysLogServiceImpl extends BaseServiceImpl<SysLog, SysLogMapper> implements SysLogService {
    
    @Resource
    private SysLogMapper sysLogMapper;
    
    @Override
    public int deleteAll() {
        return sysLogMapper.removeAllLog();
    }
    
    @Override
    public Integer insert(SysLog record) {
        return sysLogMapper.insert(record);
    }
    
}
