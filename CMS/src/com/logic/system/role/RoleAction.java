package com.logic.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;
import com.logic.system.menu.MenuManager;
import com.model.Sys_Menu;
import com.model.Sys_Role;
import com.tool.Request;


public class RoleAction {
	
	//跳转到菜单面板
	@Port(port = MessagePort.ROLE)
	public void menu(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("left", "app/system/role/left.jsp");
		request.setAttribute("main", "request?port=" + MessagePort.ROLE_MAIN);

		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	
	//role main
	@Port(port = MessagePort.ROLE_MAIN)
	public void roleMain(HttpServletRequest request, HttpServletResponse response){
		List<Sys_Role> roleList = RoleManager.getInstance().getRoleList();
		request.setAttribute("roles", roleList);
		
		List<Sys_Menu> menuList = MenuManager.getInstance().getMenuList();
		Map<Integer, Sys_Menu> menuMap = new HashMap<Integer, Sys_Menu>();
		for(int i=0; i<menuList.size(); i++){
			Sys_Menu menu = menuList.get(i);
			menuMap.put(menu.getId(), menu);
		}
		request.setAttribute("menus", menuMap);
		
		Dispatcher.dispatcher(request, response, "app/system/role/main.jsp");
	}
	
	
	
	
	//role addvIEW
	@Port(port = MessagePort.ROLE_ADD_VIEW)
	public void roleAddView(HttpServletRequest request, HttpServletResponse response){
		List<Sys_Menu> menuList = RoleManager.getInstance().getMenuList();
		request.setAttribute("menus", menuList);
		Dispatcher.dispatcher(request, response, "app/system/role/add.jsp");
	}
	
	//role add
	@Port(port = MessagePort.ROLE_ADD)
	public void roleAdd(HttpServletRequest request, HttpServletResponse response){
		String name = Request.getString(request, "name");
		String ids[] = request.getParameterValues("ids");
		StringBuilder str = new StringBuilder();
		for(int i=0; i<ids.length; i++){
			str.append(ids[i]);
			if(i != ids.length - 1){
				str.append(",");
			}
		}
		RoleManager.getInstance().save(name, str.toString());
		
		roleMain(request, response);
	}
	
	//role del
	@Port(port = MessagePort.ROLE_DEL)
	public void roleDel(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		
		RoleManager.getInstance().del(id);
		
		roleMain(request, response);
	}
	
	//role update_view
	@Port(port = MessagePort.ROLE_UPDATE_VIEW)
	public void roleUpdateView(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		
		Sys_Role role = RoleManager.getInstance().find(id);
		request.setAttribute("role", role);
		
		List<Sys_Menu> selectmenuList = RoleManager.getInstance().getMenuList("from Sys_Menu where id in(" + role.getMenuIds() + ")");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Sys_Menu menu : selectmenuList) {
			map.put(menu.getId(), menu.getId());
		}
		request.setAttribute("selectmenus", map); 
		
		List<Sys_Menu> menuList = RoleManager.getInstance().getMenuList();
		request.setAttribute("menus", menuList);

		Dispatcher.dispatcher(request, response, "app/system/role/update.jsp");
	}

	//role update
	@Port(port = MessagePort.ROLE_UPDATE)
	public void roleUpdate(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		String name = Request.getString(request, "name");
		String ids[] = request.getParameterValues("ids");
		StringBuilder str = new StringBuilder();
		for(int i=0; i<ids.length; i++){
			str.append(ids[i]);
			if(i != ids.length - 1){
				str.append(",");
			}
		}
		RoleManager.getInstance().update(id, name, str.toString());
		
		roleMain(request, response);
	}
}
