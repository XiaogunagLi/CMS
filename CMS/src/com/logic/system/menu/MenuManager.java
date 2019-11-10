package com.logic.system.menu;



import java.util.List;

import com.core.SpringContext;
import com.model.Sys_Menu;

public class MenuManager {

	private static final MenuManager menuManager = new MenuManager();
	private MenuDaoImpl menuDao = SpringContext.getBeen("menuDao");
	
	public static MenuManager getInstance(){
		return menuManager;
	}
	
	public List<Sys_Menu> getMenuList(){
		return menuDao.getMenuList();
	}
	
	public void save(String name, int port){
		Sys_Menu menu = new Sys_Menu();
		menu.setName(name);
		menu.setPort(port);
		
		menuDao.saveObject(menu);
	}
	public void del(int id){
		Object obj = menuDao.getObjectById(Sys_Menu.class, id);
		if(obj == null){
			return;
		}
		menuDao.delObject(obj);
	}
	
	public Sys_Menu findMenu(int id){
		Sys_Menu menu = (Sys_Menu)menuDao.getObjectById(Sys_Menu.class, id);
		return menu;
	}
	
	public void update(int id, String name, int port, int sort){
		Sys_Menu menu = new Sys_Menu();
		menu.setId(id);
		menu.setName(name);
		menu.setPort(port);
		menu.setSort(sort);
		menuDao.updateObject(menu);
	}
	
	
}
