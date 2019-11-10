package com.logic.control;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_Menu;
@Component("controlDao")
public class ControlDaoImpl extends BaseDao{

	public List<Sys_Menu> getMenuList(String hql){
		return getList(hql);
	}
}
	
