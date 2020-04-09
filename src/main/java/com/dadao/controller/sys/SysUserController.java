package com.dadao.controller.sys;


import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysRole;
import com.dadao.bean.SysUser;
import com.dadao.core.util.CommonUtils;
import com.dadao.core.util.MyDigestUtils;
import com.dadao.core.util.page.Condition;
import com.dadao.core.util.page.JqGridModel;
import com.dadao.core.util.page.Operators;
import com.dadao.core.util.page.PagePara;
import com.dadao.core.util.view.InvokeResult;
import com.dadao.service.SysRoleService;
import com.dadao.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统用户管理.
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRoleService sysRoleService;
    
    @RequestMapping("")
    public String index() {
        return "/sys/user_index";
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
        JqGridModel jqGridModel = sysUserService.selectByPara(page, conditions, orderBy);
        return JSON.toJSONString(jqGridModel);
    }
    
    @RequestMapping("/setVisible")
    @ResponseBody
    public String setVisible(Integer visible, String ids) {
        InvokeResult result = sysUserService.setVisible(ids, visible);
        return JSON.toJSONString(result);
    }
    
    @RequestMapping("/add")
    public String add(Integer id, Model model) {
        if (id != null) {
            model.addAttribute("item", sysUserService.selectByPrimaryKey(id));
        }
        List<SysRole> list = sysRoleService.getSysRoleNameList();
        model.addAttribute("roleList", list);
        model.addAttribute("id", id);
        return "/sys/user_add";
    }
    
    @RequestMapping("/save")
    @ResponseBody
    public String save(SysUser sysUser) {
        if (sysUser != null) {
            if (sysUser.getId() != null) {
                sysUserService.updateByPrimaryKeySelective(sysUser);
                return JSON.toJSONString(InvokeResult.success("用户修改成功"));
            } else {
                sysUser.setPwd(MyDigestUtils.shaDigestForPasswrod("123456"));
                sysUser.setCreateDate(new Date());
                sysUserService.insert(sysUser);
                return JSON.toJSONString(InvokeResult.success("用户保存成功"));
            }
        } else {
            return JSON.toJSONString(InvokeResult.failure("操作失败"));
        }
    }
    
    @RequestMapping("/userRoleSetting")
    public String userRoleSetting(Integer uid, Model model) {
        model.addAttribute("item", sysUserService.selectByPrimaryKey(uid));
        
        InvokeResult result = sysUserService.getRoleZtreeViewList(uid);
        model.addAttribute("jsonTree", result);
        return "/sys/user_role_setting";
    }
    
    @RequestMapping("/saveUserRoles")
    @ResponseBody
    public String saveUserRoles(Integer id, String roleIds) {
        InvokeResult result = sysUserService.changeUserRoles(id, roleIds);
        return JSON.toJSONString(result);
    }
    
    @RequestMapping("/del")
    @ResponseBody
    public String del(Integer id) {
        boolean flag = sysUserService.deleteByPrimaryKey(id);
        if (flag) {
            return JSON.toJSONString(InvokeResult.success());
        } else {
            return JSON.toJSONString(InvokeResult.failure(-1, "删除失败,请联系管理员"));
        }
    }
    
}
