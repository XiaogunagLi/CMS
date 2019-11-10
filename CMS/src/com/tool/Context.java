package com.tool;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.model.Sys_User;

public class Context {
	
	public static Sys_User getUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		Sys_User user = (Sys_User) session.getAttribute("user");
		return user;
	}

}
