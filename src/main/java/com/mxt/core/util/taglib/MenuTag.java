package com.mxt.core.util.taglib;

import com.mxt.bean.SysUser;
import com.mxt.service.SysResService;
import com.mxt.core.util.IWebUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 系统菜单
 * 2014年8月9日
 */
public class MenuTag extends TagSupport {
    
    private static final long serialVersionUID = 1L;
    private Integer type = 1;
    private static SysResService sysResService;
    
    @Override
    public int doStartTag() throws JspException {
        try {
            HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
            SysUser sysUser = IWebUtils.getCurrentSysUser(request);
            if (sysUser != null) {
                int uid = sysUser.getId();
                WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
                sysResService = (SysResService) webApplicationContext.getBean("sysResService");
                String menuView = sysResService.getSysUserMenuView(uid, "/");
                pageContext.getOut().print(menuView);
            } else {
                pageContext.getOut().print("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
}
