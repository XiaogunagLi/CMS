package com.logic.user;


import java.util.List;

import com.core.SpringContext;
import com.model.Sys_User;

public class UserManager {
	private static final UserManager userManager = new UserManager();
	private UserDaoImpl userDao = SpringContext.getBeen("userDao");
	private UserManager(){
	}
	
	public static UserManager getInstance(){
		return userManager;
	}
	public List<Sys_User> getUserList(){
		return userDao.getUserList();
	}
	
	public void save(Sys_User user){
		userDao.saveObject(user);
	}
	
	public void update(Sys_User user){
		userDao.update(user);
	}
	
	public Sys_User findUserById(int userID){
		return userDao.getUser(userID);
	}
	
	public void del(Sys_User user){
		userDao.del(user);
	}
}
