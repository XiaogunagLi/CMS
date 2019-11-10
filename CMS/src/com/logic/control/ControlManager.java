package com.logic.control;



import java.util.List;

import com.core.SpringContext;
import com.model.Sys_Menu;

public class ControlManager {

	private static final ControlManager conManager = new ControlManager();
	private ControlDaoImpl controlDao = SpringContext.getBeen("controlDao");
	
	public static ControlManager getInstance(){
		return conManager;
	}
	
	public List<Sys_Menu> getMenuList(String hql){
		return controlDao.getMenuList(hql);
	}
	
}
