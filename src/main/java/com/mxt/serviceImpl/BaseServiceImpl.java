package com.mxt.serviceImpl;

import com.mxt.core.util.StringUtils;
import com.mxt.core.util.page.Condition;
import com.mxt.core.util.page.JqGridModel;
import com.mxt.core.util.page.PagePara;
import com.mxt.core.util.page.PageUtils;
import com.mxt.dao.BaseMapper;
import com.mxt.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class BaseServiceImpl<T, M extends BaseMapper<T>> implements BaseService<T> {
    
    @Autowired
    protected M mapper;
    
    
    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id) > -1;
    }
    
    @Override
    public Integer insert(T record) {
        return mapper.insert(record);
    }
    
    @Override
    public T selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }
    
    @Override
    public Integer updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }
    
    @Override
    public Integer updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
    
    @Override
    public JqGridModel selectByPara(PagePara page, Set<Condition> conditions, LinkedHashMap<String, String> orderBys) {
        if (StringUtils.notBlank(page.getSidx())) {
            orderBys.put(page.getSidx(), page.getSord());
        }
        Integer count = mapper.selectCount();
        List<T> tList = mapper.selectByPara(PageUtils.getWhereSql(conditions), PageUtils.getOrderBy(orderBys), PageUtils.getPage(page,count));

        return new JqGridModel(count.longValue(), page.getPage(), (count / page.getRows()) + 1, tList);
    }
    
}
