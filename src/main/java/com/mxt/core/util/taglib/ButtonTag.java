package com.mxt.core.util.taglib;

import com.mxt.bean.SysUser;
import com.mxt.service.SysUserService;
import com.mxt.core.util.IWebUtils;
import com.mxt.core.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Set;

/**
 * 功能按钮
 */
public class ButtonTag extends TagSupport {
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String className;
    /**
     * 需要的权限
     */
    private String permission;
    
    private String style;
    
    private String name;
    
    private String textName;
    
    @Override
    
    public int doStartTag() throws JspException {
        try {
            boolean hasPermission = true;
            StringBuilder button = new StringBuilder("<button");
            if (StringUtils.notBlank(permission)) {// 判断有没有权限
                HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
                SysUser sysUser = IWebUtils.getCurrentSysUser(request);
                
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                SysUserService sysUserService = (SysUserService) webApplicationContext.getBean("sysUserService");
                
                Set<String> permissionSets = sysUserService.getPermissionSets(sysUser.getId());
                
                if (sysUser == null || !permissionSets.contains(permission)) {
//				if (sysUser == null) {
                    hasPermission = false;
                }
            }
            if (hasPermission) {
                if (StringUtils.notBlank(className)) {
                    button.append(" class=\"" + className + "\"");
                }
                if (StringUtils.notBlank(id)) {
                    button.append(" id=\"" + id + "\"");
                }
                if (StringUtils.notBlank(name)) {
                    button.append(" name=\"" + name + "\"");
                }
                if (StringUtils.notBlank(style)) {
                    button.append(" style=\"" + style + "\"");
                }
                button.append(">");
                if (StringUtils.notBlank(textName)) {
                    button.append(textName);
                }
                button.append("</button>");
                pageContext.getOut().print(button.toString());
            } else {
                pageContext.getOut().print("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getStyle() {
        return style;
    }
    
    public void setStyle(String style) {
        this.style = style;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getTextName() {
        return textName;
    }
    
    public void setTextName(String textName) {
        this.textName = textName;
    }
    
}
