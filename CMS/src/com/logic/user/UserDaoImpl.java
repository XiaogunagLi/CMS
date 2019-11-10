package com.logic.user;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_User;
@Component("userDao")
public class UserDaoImpl extends BaseDao{
	public Sys_User getUser(String userName, String passWord){
		List<Sys_User> list = getList("from Sys_User where userName = '" + userName + "' and passWord = '" + passWord + "'");
		if(list.size() <= 0){
			return null;
		}
		return list.get(0);
	}
	
	public List<Sys_User> getUserList(){
		List<Sys_User> list = getList("from Sys_User");
		return list;
	}
	
	public void update(Sys_User user){
		updateObject(user);
	}
	
	public Sys_User getUser(int id){
		Sys_User user = (Sys_User)this.getObjectById(Sys_User.class, id);
		return user;
	}
	
	public void del(Sys_User user){
		this.delObject(user);
	}
}
