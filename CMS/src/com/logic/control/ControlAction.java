package com.logic.control;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;
import com.logic.system.role.RoleManager;
import com.model.Sys_Menu;
import com.model.Sys_Role;
import com.model.Sys_User;
import com.tool.Context;


public class ControlAction {

	//ªÒ»°≤Àµ• top.jsp
	@Port(port = MessagePort.MENU_LIST)
	public void menu(HttpServletRequest request, HttpServletResponse response){
		Sys_User user = Context.getUser(request);
		int roleId = user.getRoleId();
		Sys_Role role = RoleManager.getInstance().find(roleId);
		
		List<Sys_Menu> menuList = ControlManager.getInstance().getMenuList("from Sys_Menu where id in(" + role.getMenuIds() + ") order by sort asc");
		
		request.setAttribute("menus", menuList);

		Dispatcher.dispatcher(request, response, "app/top.jsp");
	}
	

}
