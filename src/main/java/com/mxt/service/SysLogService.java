package com.mxt.service;

import com.mxt.bean.SysLog;
import com.mxt.core.util.page.Condition;
import com.mxt.core.util.page.JqGridModel;
import com.mxt.core.util.page.PagePara;

import java.util.LinkedHashMap;
import java.util.Set;

public interface SysLogService extends BaseService<SysLog> {
    int deleteAll();
    
    Integer insert(SysLog record);
    
    JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBys);
}
