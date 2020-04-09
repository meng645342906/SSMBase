package com.dadao.service;

import com.dadao.bean.SysLog;
import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.PagePara;

import java.util.LinkedHashMap;
import java.util.Set;

public interface SysLogService extends BaseService<SysLog> {
    int deleteAll();
    
    Integer insert(SysLog record);
    
    JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBys);
}
