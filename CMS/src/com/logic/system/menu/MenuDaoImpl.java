package com.logic.system.menu;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_Menu;
@Component("menuDao")
public class MenuDaoImpl extends BaseDao{

	public List<Sys_Menu> getMenuList(){
		return getList("from Sys_Menu order by sort asc");
	}
	
	public List<Sys_Menu> getMenuList(String hql){
		return getList(hql);
	}
}
	
