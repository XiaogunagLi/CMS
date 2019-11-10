package com.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.logic.system.menu.MenuDaoImpl;
import com.logic.system.role.RoleDaoImpl;
import com.model.Sys_Menu;
import com.model.Sys_Role;

public class PowerCheckManager {
	private Map<Integer, Integer> portMap = new HashMap<Integer, Integer>();
	
	public void portMapIni(int roleId){
		RoleDaoImpl roleDao = SpringContext.getBeen("roleDao");
		Sys_Role role = (Sys_Role)roleDao.getObjectById(Sys_Role.class, roleId);
		String menuIds = role.getMenuIds();
		
		MenuDaoImpl menuDao = SpringContext.getBeen("menuDao");
		List<Sys_Menu> menuList =menuDao.getMenuList("from Sys_Menu where id in (" + menuIds + ")");
		for (Sys_Menu menu : menuList) {
			portMap.put(menu.getPort(), menu.getPort());
		}
	}
	
	public boolean check(HttpSession session, int port){
		if(port <= 21){
			return true;
		}
		
		int mId = port;
		
		//目录 30 - 49
		if(mId >= 30 && mId <= 49){
			if(portMap.get(30) == null){
				return false;
			}
			
		//内容 50 - 99
		}else if(mId >= 50 && mId <= 99){
			if(portMap.get(50) == null){
				return false;
			}
			
		//菜单 100 - 149
		}else if(mId >= 100 && mId <= 149){
			if(portMap.get(100) == null){
				return false;
			}
			
		//首页 150 - 199
		}else if(mId >= 150 && mId <= 199){
			if(portMap.get(150) == null){
				return false;
			}
			
		//角色 200 - 249
		}else if(mId >= 200 && mId <= 249){
			if(portMap.get(200) == null){
				return false;
			}
			
		//用户管理 250 - 349
		}else if(mId >= 250 && mId <= 349){
			if(portMap.get(250) == null){
				return false;
			}
			
			//活动 350 - 449
		}else if(mId >= 350 && mId <= 449){
			if(portMap.get(350) == null){
				return false;
			}
			
		//统计 450 - 499
		}else if(mId >= 450 && mId <= 499){
			if(portMap.get(450) == null){
				return false;
			}
			
		//管理 500 - 600
		}else if(mId >= 500 && mId <= 600){
			if(portMap.get(500) == null){
				return false;
			}
		}
		
		return true;
	}
}
