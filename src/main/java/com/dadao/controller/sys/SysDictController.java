package com.dadao.controller.sys;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.DictData;
import com.dadao.bean.DictType;
import com.dadao.core.util.CommonUtils;
import com.dadao.core.util.DateUtils;
import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.Operators;
import com.dadao.core.util.page.PagePara;
import com.dadao.core.util.view.InvokeResult;
import com.dadao.service.SysDictDataService;
import com.dadao.service.SysDictTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
@RequestMapping("/dict")
public class SysDictController {
    
    @Resource
    private SysDictDataService sysDictDataService;
    @Resource
    private SysDictTypeService sysDictTypeService;
    
    
    @RequestMapping("")
    public String index() {
        return "/sys/dict/type_index";
    }
    
    @RequestMapping("/add_type")
    public String add_type(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("item", sysDictTypeService.selectByPrimaryKey(id));
        }
        return "/sys/dict/type_add";
    }
    
    @RequestMapping("/add_data")
    public String add_data(Integer id, Integer typeId, Model model) {
        if (id != null) {
            model.addAttribute("item", sysDictDataService.selectByPrimaryKey(id));
        }
        model.addAttribute("typeId", typeId);
        return "/sys/dict/data_add";
    }
    
    @RequestMapping("/data_index")
    public String data_index(Integer typeId, Model model) {
        model.addAttribute("typeId", typeId);
        return "/sys/dict/data_index";
    }
    
    @RequestMapping("/saveType")
    @ResponseBody
    public String saveType(DictType dictType) {
        dictType.setUpdateTime(DateUtils.formatDateToUnixTimestamp(new Date()));
        InvokeResult invokeResult = null;
        if (dictType.getId() != null) {
            Integer update = sysDictTypeService.updateByPrimaryKeySelective(dictType);
            invokeResult = update > 0 ? InvokeResult.success("保存成功") : InvokeResult.failure("保存失败");
        } else {
            Integer insert = sysDictTypeService.insert(dictType);
            invokeResult = insert > 0 ? InvokeResult.success("保存成功") : InvokeResult.failure("保存失败");
        }
        return JSON.toJSONString(invokeResult);
    }
    
    @RequestMapping("/saveData")
    @ResponseBody
    public String saveData(DictData dictData) {
        dictData.setUpdateTime(DateUtils.formatDateToUnixTimestamp(new Date()));
        InvokeResult invokeResult = null;
        if (dictData.getId() != null) {
            Integer update = sysDictDataService.updateByPrimaryKeySelective(dictData);
            invokeResult = update > 0 ? InvokeResult.success("修改成功") : InvokeResult.failure("修改失败");
        } else {
            Integer insert = sysDictDataService.insert(dictData);
            invokeResult = insert > 0 ? InvokeResult.success("保存成功") : InvokeResult.failure("保存失败");
        }
        return JSON.toJSONString(invokeResult);
    }
    
    @RequestMapping("/deleteData")
    @ResponseBody
    public String deleteData(Integer id) {
        Boolean deleteByPrimaryKey = sysDictDataService.deleteByPrimaryKey(id);
        InvokeResult invokeResult = deleteByPrimaryKey ? InvokeResult.success("删除成功") : InvokeResult.failure("删除失败");
        return JSON.toJSONString(invokeResult);
    }
    
    @RequestMapping("/getTypeListData")
    @ResponseBody
    public String getTypeListData(PagePara page, String keyword) {
        Set<Condition> conditions = new HashSet<Condition>();
        if (CommonUtils.isNotEmpty(keyword)) {
            conditions.add(new Condition("name", Operators.LIKE, keyword));
        }
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
        orderBy.put("id", "desc");
        JqGridModel jqGridModel = sysDictTypeService.selectByPara(page, conditions, orderBy);
        
        return JSON.toJSONString(jqGridModel);
    }
    
    @RequestMapping("/getListData")
    @ResponseBody
    public String getListData(PagePara page, Integer typeId, String keyword) {
        Set<Condition> conditions = new HashSet<Condition>();
        if (CommonUtils.isNotEmpty(keyword)) {
            conditions.add(new Condition("name", Operators.LIKE, keyword));
        }
        conditions.add(new Condition("dict_type_id", Operators.EQ, typeId));
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
        orderBy.put("id", "desc");
        JqGridModel jqGridModel = sysDictDataService.selectByPara(page, conditions, orderBy);
        return JSON.toJSONString(jqGridModel);
    }
    
    /**
     * 删除数据字典
     */
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id) {
        sysDictDataService.deleteByTypeId(id);
        boolean typeFlag = sysDictTypeService.deleteByPrimaryKey(id);
        if (typeFlag) {
            return JSON.toJSONString(InvokeResult.success());
        } else {
            return JSON.toJSONString(InvokeResult.failure(-1, "删除失败,请联系管理员"));
        }
    }
}
