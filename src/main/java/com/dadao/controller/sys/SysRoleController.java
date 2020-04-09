package com.dadao.controller.sys;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysRole;
import com.dadao.service.SysRoleService;
import com.dadao.core.util.CommonUtils;
import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.Operators;
import com.dadao.core.util.page.PagePara;
import com.dadao.core.util.view.InvokeResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

@Controller
@RequestMapping("/sys/role")
public class SysRoleController {
    
    @Resource
    private SysRoleService sysRoleService;
    
    @RequestMapping("")
    public String index() {
        return "/sys/role_index";
    }
    
    @RequestMapping("/add")
    public String add(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("item", sysRoleService.selectByPrimaryKey(id));
        }
        model.addAttribute("id", id);
        return "/sys/role_add";
    }
    
    @RequestMapping("/getListData")
    @ResponseBody
    public String getListData(PagePara page, String name) {
        Set<Condition> conditions = new HashSet<>();
        if (CommonUtils.isNotEmpty(name)) {
            conditions.add(new Condition("name", Operators.LIKE, name));
        }
        LinkedHashMap<String, String> orderBy = new LinkedHashMap<>();
        orderBy.put("id", "desc");
        
        JqGridModel jqGridModel = sysRoleService.selectByPara(page, conditions, orderBy);
        
        return JSON.toJSONString(jqGridModel);
    }
    
    @RequestMapping("/setVisible")
    @ResponseBody
    public String setVisible(Integer visible, String ids) {
        InvokeResult result = sysRoleService.setVisible(ids, visible);
        return JSON.toJSONString(result);
    }
    
    @RequestMapping("/getZtree")
    public String getZtree(Integer type, Integer roleId, Model model) {
        InvokeResult result = sysRoleService.getZtreeViewList(type, roleId);
        model.addAttribute("jsonTree", result);
        model.addAttribute("roleId", roleId);
        return "/sys/menu_assign";
    }
    
    /**
     * 角色绑定资源.
     */
    @RequestMapping("/saveMenuAssign")
    @ResponseBody
    public String saveMenuAssign(Integer roleId, String menuIds) {
        InvokeResult result = sysRoleService.saveMenuAssign(menuIds, roleId);
        return JSON.toJSONString(result);
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public String save(SysRole sysRole) {
        if (sysRole != null) {
            if (sysRole.getId() != null) {
                sysRoleService.updateByPrimaryKeySelective(sysRole);
                return JSON.toJSONString(InvokeResult.success("修改成功"));
            } else {
                sysRole.setStatus(1);
                sysRoleService.insert(sysRole);
                return JSON.toJSONString(InvokeResult.success("保存成功"));
            }
        } else {
            return JSON.toJSONString(InvokeResult.failure("操作失败"));
        }
    }
    
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id) {
       boolean flag = sysRoleService.deleteByPrimaryKey(id);
        if (flag) {
            return JSON.toJSONString(InvokeResult.success());
        } else {
            return JSON.toJSONString(InvokeResult.failure(-1, "删除失败,请联系管理员"));
        }
    }
}
