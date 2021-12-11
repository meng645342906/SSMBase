package com.mxt.core.interceptor;

import com.mxt.bean.SysLog;
import com.mxt.bean.SysUser;
import com.mxt.core.util.CommonUtils;
import com.mxt.core.util.IWebUtils;
import com.mxt.core.util.StringUtils;
import com.mxt.service.SysLogService;
import com.mxt.service.SysUserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Set;


public class SysLogInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService sysUserService;
    public static final String SEPARATE_SERVER_PORT = ":";
    
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        
        if (!methodName.equals("index") && className.equals("com.dadao.controller.IndexController")) {
            return true;
        }
        
        String requestUri = request.getRequestURI();
        
        SysUser sysUser = IWebUtils.getCurrentSysUser(request);
        
        if (sysLogService == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            sysUserService = (SysUserService) factory.getBean("sysUserService");
        }
        
        if (sysUser == null && StringUtils.notBlank(requestUri) && !requestUri.equals("/") && !requestUri.equals("/doLogin")) {// 没有登陆
            request.getRequestDispatcher("/admin/login.jsp").forward(request, response);
            return false;
        } else if (sysUser != null && !methodName.equals("index")) {
            Set<String> permissionSets = sysUserService.getPermissionSets(sysUser.getId());
            if (!permissionSets.contains(requestUri)) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter out = response.getWriter();
                out.append("没有权限");
                out.close();
            }
        }
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        StringBuilder ctx = new StringBuilder();
        ctx.append(request.getScheme());        //  http
        ctx.append("://");                      //      ://
        ctx.append(request.getServerName());    // ip地址（域名）
        if (request.getServerPort() != 80) {
            ctx.append(SEPARATE_SERVER_PORT);
            ctx.append(request.getServerPort());
        }
        ctx.append(request.getContextPath());//添加站点的根目录
        
        String ip = CommonUtils.getIP(request);
        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();
        Integer uid = null;
        SysUser sysUser = IWebUtils.getCurrentSysUser(request);
        if (sysUser != null) {
            uid = sysUser.getId();
        }
        long startTime = System.currentTimeMillis();
        try {
            
            SysLog sysLog = new SysLog();
            sysLog.setUid(uid);
            sysLog.setIp(ip);
            sysLog.setFrom(ctx.toString());
            sysLog.setUrl(request.getRequestURI());
            sysLog.setClassName(className);
            sysLog.setMethodName(methodName);
            sysLog.setErrCode(0);
            sysLog.setErrMsg("");
            sysLog.setStartTime(new Date(startTime));
            sysLog.setSpendTime(System.currentTimeMillis() - startTime);
            
            if (sysLogService == null) {
                BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                sysLogService = (SysLogService) factory.getBean("sysLogService");
            } else {
                sysLogService.insert(sysLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}