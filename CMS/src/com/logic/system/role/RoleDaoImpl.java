package com.logic.system.role;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_Menu;
import com.model.Sys_Role;
@Component("roleDao")
public class RoleDaoImpl extends BaseDao{

	public List<Sys_Role> getRoleList(){
		return getList("from Sys_Role");
	}
	
	public List<Sys_Menu> getMenuList(){
		return getList("from Sys_Menu order by sort asc");
	}
	
	public List<Sys_Menu> getMenuList(String hql){
		return getList(hql);
	}
}
	
