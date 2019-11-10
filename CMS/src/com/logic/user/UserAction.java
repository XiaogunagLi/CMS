package com.logic.user;


import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.PowerCheckManager;
import com.core.SpringContext;
import com.core.annotation.Port;
import com.logic.system.menu.MenuDaoImpl;
import com.logic.system.role.RoleDaoImpl;
import com.logic.system.role.RoleManager;
import com.model.Sys_Menu;
import com.model.Sys_Role;
import com.model.Sys_User;
import com.tool.Request;

public class UserAction{
	//用户登录
	@Port(port = MessagePort.LOGIN)
	public  void login(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		//判断验证码是否过期
		String code = (String)session.getAttribute("code");
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
		} 

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String userCode = request.getParameter("code");
		
		if(code == null){
			request.setAttribute("error", "验证码为空");
			Dispatcher.dispatcher(request, response, "app/login.jsp");
			return;
		}
		//验证码输入错误
		if(!code.equals(userCode)){
			request.setAttribute("error", "验证码错误");
			Dispatcher.dispatcher(request, response, "app/login.jsp");
			return;
		}
		
//		MessageDigest md5 = MessageDigest.getInstance("MD5");  
//		BASE64Encoder base64en = new BASE64Encoder();  
//		// 加密后的字符串  
//		String pw = base64en.encode(md5.digest(pro.getPassWord().getBytes("utf-8"))); 
//		
		UserDaoImpl userDao = SpringContext.getBeen("userDao");
		Sys_User user = userDao.getUser(userName, passWord); 
		
		if(user == null){
			request.setAttribute("error", "用户名或密码错误");
			Dispatcher.dispatcher(request, response, "app/login.jsp");
			return;
		}

		session.setAttribute("user",user);
		
		request.setAttribute("left", "app/home/left.jsp");
		request.setAttribute("main", "app/home/main.jsp");
		
		PowerCheckManager pc = new PowerCheckManager();
		pc.portMapIni(user.getRoleId());
		session.setAttribute("pc",pc);
		
		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	
	//退出
	@Port(port = MessagePort.EXIT)
	public void exit(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		session.invalidate();
		Dispatcher.dispatcher(request, response, "app/login.jsp");
	}
	
	//跳转到用户界面
	@Port(port = MessagePort.USER)
	public void userManage(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("left", "request?port=" + MessagePort.USER_LEFT);
		request.setAttribute("main", "request?port=" + MessagePort.USER_MAIN);
		
		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	
	//left
	@Port(port = MessagePort.USER_LEFT)
	public void left(HttpServletRequest request, HttpServletResponse response){

		Dispatcher.dispatcher(request, response, "app/user/left.jsp");
	}
	
	//main
	@Port(port = MessagePort.USER_MAIN)
	public void main(HttpServletRequest request, HttpServletResponse response){
		List<Sys_User> userList = UserManager.getInstance().getUserList();
		
		List<Sys_Role> roleList = RoleManager.getInstance().getRoleList();
		Map<Integer, Sys_Role> roleMap = new HashMap<Integer, Sys_Role>();
		for(int i=0; i<roleList.size(); i++){
			Sys_Role role = roleList.get(i);
			roleMap.put(role.getId(), role);
		}
		request.setAttribute("roles", roleMap);
		request.setAttribute("users", userList);
		
		Dispatcher.dispatcher(request, response, "app/user/main.jsp");
	}
	
	//add view
	@Port(port = MessagePort.USER_ADD_VIEW)
	public void addView(HttpServletRequest request, HttpServletResponse response){
		List<Sys_Role> roleList = RoleManager.getInstance().getRoleList();
		request.setAttribute("roles", roleList);
		
		Dispatcher.dispatcher(request, response, "app/user/add.jsp");
	}
	
	//add
	@Port(port = MessagePort.USER_ADD)
	public void add(HttpServletRequest request, HttpServletResponse response){
		String userName = Request.getString(request, "userName");
		String passWord = Request.getString(request, "passWord");
		int roleId = Request.getInt(request, "role");
		
		Sys_User user = new Sys_User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		user.setRoleId(roleId);
		
		UserManager.getInstance().save(user);
		
		main(request, response);
	}
	
	//update view
	@Port(port = MessagePort.USER_UPDATE_VIEW)
	public void updateView(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		
		List<Sys_Role> roleList = RoleManager.getInstance().getRoleList();
		request.setAttribute("roles", roleList);
		
		Sys_User user = UserManager.getInstance().findUserById(id);
		if(user == null){
			return;
		}
		
		request.setAttribute("user", user);
		
		Dispatcher.dispatcher(request, response, "app/user/update.jsp");
	}
	
	//update
	@Port(port = MessagePort.USER_UPDATE)
	public void update(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		String userName = Request.getString(request, "userName");
		String passWord = Request.getString(request, "passWord");
		String newPassWord = Request.getString(request, "newPassWord");
		int roleId = Request.getInt(request, "role");
		
		Sys_User user = UserManager.getInstance().findUserById(id);
		if(user == null || !user.getPassWord().equals(passWord)){
			return;
		}
		user.setUserName(userName);
		user.setPassWord(newPassWord);
		user.setRoleId(roleId);
		
		UserManager.getInstance().update(user);
		
		main(request, response);
	}
	
	//del
	@Port(port = MessagePort.USER_DEL)
	public void del(HttpServletRequest request, HttpServletResponse response){
		int id = Request.getInt(request, "id");
		Sys_User user = UserManager.getInstance().findUserById(id);
		if(user == null){
			return;
		}
		UserManager.getInstance().del(user);
		
		main(request, response);
	}
}
