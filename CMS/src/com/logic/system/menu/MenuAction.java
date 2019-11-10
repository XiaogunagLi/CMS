package com.logic.system.menu;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;
import com.model.Sys_Menu;
import com.tool.Request;


public class MenuAction {

	//跳转到菜单面板
	@Port(port = MessagePort.MENU)
	public void menu(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("left", "app/system/menu/left.jsp");
		request.setAttribute("main", "request?port=" + MessagePort.MENU_MAIN);

		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	
	//menu main
	@Port(port = MessagePort.MENU_MAIN)
	public void menuMain(HttpServletRequest request, HttpServletResponse response){
		List<Sys_Menu> menuList = MenuManager.getInstance().getMenuList();
		request.setAttribute("menus", menuList);
		Dispatcher.dispatcher(request, response, "app/system/menu/main.jsp");
	}
	
	//menu add
	@Port(port = MessagePort.MENU_ADD)
	public void menuAdd(HttpServletRequest request, HttpServletResponse response){
		String name = Request.getString(request, "name");
		int port = Request.getInt(request, "port_");
		
		MenuManager.getInstance().save(name, port);
		
		menuMain(request, response);
	}
	
	//menu del
	@Port(port = MessagePort.MENU_DEL)
	public void menuDel(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		
		MenuManager.getInstance().del(id);
		
		menuMain(request, response);
	}
	
	//menu update_view
	@Port(port = MessagePort.MENU_UPDATE_VIEW)
	public void menuUpdateView(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		
		Sys_Menu menu = MenuManager.getInstance().findMenu(id);
		request.setAttribute("menu", menu);

		Dispatcher.dispatcher(request, response, "app/system/menu/update.jsp");
	}

	//menu update
	@Port(port = MessagePort.MENU_UPDATE)
	public void menuUpdate(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		String name = Request.getString(request, "name");
		int port = Request.getInt(request, "port_");
		int sort = Request.getInt(request, "sort");
		
		MenuManager.getInstance().update(id, name, port, sort);
		
		menuMain(request, response);
	}
}
