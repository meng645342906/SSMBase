package com.dadao.controller;

import com.alibaba.fastjson.JSON;
import com.dadao.bean.SysUser;
import com.dadao.core.util.IWebUtils;
import com.dadao.core.util.MyDigestUtils;
import com.dadao.core.util.view.InvokeResult;
import com.dadao.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @Resource
    private SysUserService sysUserService;
    
    @RequestMapping("/")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletRequest request, String username, String password, Model model) {
        InvokeResult result = sysUserService.doLogin(request.getSession(), username, password);
        return JSON.toJSONString(result);
    }
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("sysUser", IWebUtils.getCurrentSysUser(request));
        return "index";
    }
    
    @RequestMapping("/home")
    public String home() {
        return "home";
    }
    
    
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request, Model model) {
        IWebUtils.removeCurrentSysUserFromSession(request.getSession());
        return "redirect:/";
    }
    
    @RequestMapping("/pwdSetting")
    public String pwdSetting(HttpServletRequest request, Model model) {
        try {
            SysUser sysUser = IWebUtils.getCurrentSysUser(request);
            model.addAttribute("sysUser", sysUser);
            return "sys/pwd_setting";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping("/savePwdUpdate")
    @ResponseBody
    public String savePwdUpdate(HttpServletRequest request, HttpServletResponse response, String oldPwd, String newPwd, String pwdRepeat) {
        oldPwd = MyDigestUtils.shaDigestForPasswrod(oldPwd);
        newPwd = MyDigestUtils.shaDigestForPasswrod(newPwd);
        pwdRepeat = MyDigestUtils.shaDigestForPasswrod(pwdRepeat);
        InvokeResult result = new InvokeResult();
        try {
            SysUser sysUser = IWebUtils.getCurrentSysUser(request);
            if (!sysUser.getPwd().equals(oldPwd)) {
                result = InvokeResult.failure(-3, "旧密码不正确");
            } else {
                if (newPwd.equals(pwdRepeat)) {
                    result = sysUserService.savePwdUpdate(sysUser.getId(), newPwd);
                } else {
                    result = InvokeResult.failure(-1, "两次密码不一致");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = InvokeResult.failure(-1, "修改密码发生异常，请联系管理员。");
        }

        return JSON.toJSONString(result);
    }
}
