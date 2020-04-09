package com.dadao.controller.sys;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysRes;
import com.dadao.service.SysResService;
import com.dadao.core.util.view.InvokeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sys/res")
public class SysResController {
    @Autowired
    private SysResService sysResService;
    
    
    @RequestMapping("")
    public String index(HttpServletRequest request, Model model) {
        return "/sys/res_index";
    }
    
    @RequestMapping("/getTreeGridView")
    @ResponseBody
    public String getTreeGridView(HttpServletRequest request) {
        return sysResService.getTreeGridView();
    }
    
    @RequestMapping("/add")
    public String add(Integer id, Model model) {
        if (id != null) {
            SysRes sysRes = sysResService.selectByPrimaryKey(id);
            if (sysRes != null) {
                Integer pid = sysRes.getPid();
                if (pid != null) {// 获取父资源
                    SysRes pRes = sysResService.selectByPrimaryKey(pid);
                    model.addAttribute("pRes", pRes);
                }
            }
            model.addAttribute("sysRes", sysRes);
        }
        
        model.addAttribute("jsonTree", JSON.toJSONString(sysResService.getZtreeViewList()));
        return "/sys/res_add";
    }
    
    @RequestMapping("/saveRes")
    @ResponseBody
    public String saveRes(SysRes sysRes) {
        Integer result = 0;
        if (sysRes != null) {
            if (sysRes.getId() != null) {
                result = sysResService.updateByPrimaryKeySelective(sysRes) > 0 ? 2 : -1;
            } else {
                result = sysResService.insert(sysRes) > 0 ? 1 : -1;
            }
        }
        Map<String, Object> mapData = new HashMap<String, Object>();
        mapData.put("code", "success");
        if (result == 1) {
            mapData.put("result", "添加成功！！");
        } else if (result == 2) {
            mapData.put("result", "编辑成功！！");
        } else {
            mapData.put("code", "failure");
            mapData.put("result", "操作失败，请联系管理员");
        }
        return JSON.toJSONString(mapData);
    }
    
    @RequestMapping("/setEnabled")
    @ResponseBody
    public String setEnabled(Integer resId, Integer status) {
        sysResService.setEnabled(resId, status);
        return JSON.toJSONString(InvokeResult.success("成功"));
    }
    
    
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        sysResService.deleteRes(id);
        sysResService.deleteByResId(id);
        return JSON.toJSONString(InvokeResult.success("成功"));
    }
}
