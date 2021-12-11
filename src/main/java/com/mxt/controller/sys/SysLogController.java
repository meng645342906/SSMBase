package com.mxt.controller.sys;

import com.alibaba.fastjson.JSON;
import com.mxt.core.util.view.InvokeResult;
import com.mxt.service.SysLogService;
import com.mxt.core.util.page.Condition;
import com.mxt.core.util.page.JqGridModel;
import com.mxt.core.util.page.Operators;
import com.mxt.core.util.page.PagePara;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    
    @Resource
    private SysLogService sysLogService;
    
    @RequestMapping("")
    public String index() {
        return "/sys/log_index";
    }
    
    @RequestMapping("/getListData")
    @ResponseBody
    public String getListData(PagePara page, HttpServletRequest request) {
        String class_name = request.getParameter("class_name");
        Set<Condition> conditions = new HashSet<>();
        conditions.add(new Condition("class_name", Operators.LIKE, class_name));
        
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
        orderBy.put("date", "desc");
        
        JqGridModel jqGridModel = sysLogService.selectByPara(page, conditions, orderBy);
        
        return JSON.toJSONString(jqGridModel);
    }
    
    @RequestMapping("/removeAll")
    @ResponseBody
    public String removeAllLog() {
        sysLogService.deleteAll();
        return JSON.toJSONString(InvokeResult.success());
    }
    
}
