package com.logic.system.role;



import java.util.List;

import javax.management.relation.Role;

import com.core.SpringContext;
import com.model.Sys_Menu;
import com.model.Sys_Role;

public class RoleManager {

	private static final RoleManager roleManager = new RoleManager();
	private RoleDaoImpl roleDao = SpringContext.getBeen("roleDao");
	
	public static RoleManager getInstance(){
		return roleManager;
	}
	
	public List<Sys_Role> getRoleList(){
		return roleDao.getRoleList();
	}
	
	public List<Sys_Menu> getMenuList(){
		return roleDao.getMenuList();
	}
	public List<Sys_Menu> getMenuList(String hql){
		return roleDao.getMenuList(hql);
	}
	
	public void save(String name, String menuIds){
		Sys_Role role = new Sys_Role();
		role.setName(name);
		role.setMenuIds(menuIds);
		
		roleDao.saveObject(role);
	}
	public void del(int id){
		Object obj = roleDao.getObjectById(Role.class, id);
		if(obj == null){
			return;
		}
		roleDao.delObject(obj);
	}
	
	public Sys_Role find(int id){
		Sys_Role role = (Sys_Role)roleDao.getObjectById(Sys_Role.class, id);
		return role;
	}
	
	public void update(int id, String name, String menuIds){
		Sys_Role role = new Sys_Role();
		role.setId(id);
		role.setName(name);
		role.setMenuIds(menuIds);
		roleDao.updateObject(role);
	}
	
	
}
