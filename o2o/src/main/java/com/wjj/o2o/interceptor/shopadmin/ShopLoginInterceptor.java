package com.wjj.o2o.interceptor.shopadmin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wjj.o2o.entity.PersonInfo;

public class ShopLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			PersonInfo user = (PersonInfo) userObj;
			if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1) {
				return true;
			}
		}
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<script>");
		out.print("window.open('"+request.getContextPath() +"/local/login?usertype=2','_self')");
		out.print("</script>");
		out.print("</html>");
		return false;

	}

}
