package com.dadao.core.util;

import com.dadao.bean.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 */
public class IWebUtils {

	/**
	 * 获取系统用户
	 * 
	 * @param httpSession
	 * @return
	 */
	public static SysUser getCurrentSysUser(HttpSession httpSession) {
		return (SysUser) httpSession.getAttribute("sysUser");
	}
	/**
	 * 获取系统用户
	 *
	 * @param httpServletRequest
	 * @return
	 */
	public static SysUser getCurrentSysUser(HttpServletRequest httpServletRequest) {
		return (SysUser) httpServletRequest.getSession().getAttribute("sysUser");
	}

	/**
	 * 保存登陆用户
	 * 
	 * @param httpSession
	 * @param sysUser
	 */
	public static void setCurrentLoginSysUserToSession(HttpSession httpSession, SysUser sysUser) {
		httpSession.setAttribute("sysUser", sysUser);
	}
	
	/**
	 * 变更用户登录token
	 * @param response
	 * @param httpSession
	 * @param sysUser
	 */
	public static void setCurrentLoginSysUser(HttpServletResponse response, HttpSession httpSession, SysUser sysUser) {
		String token = RandomUtils.getRandomString(64);
		sysUser.setToken(token);
//		sysUser.update();
		CookieUtils.addCookie(response, "token", token, 60 * 60 * 24);
		setCurrentLoginSysUserToSession(httpSession, sysUser);
	}

	/**
	 * 清除session中的系统用户
	 * 
	 * @param httpSession
	 */
	public static void removeCurrentSysUserFromSession(HttpSession httpSession) {
		httpSession.removeAttribute("sysUser");
	}

	public static void removeCurrentSysUser(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.delCookie("", request, response);
		removeCurrentSysUserFromSession(request.getSession());
	}

}
