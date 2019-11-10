package com.logic.home;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;


public class HomeAction {

	//ªÒ»°≤Àµ• top.jsp
	@Port(port = MessagePort.HOME)
	public void home(HttpServletRequest request, HttpServletResponse response){
		
		request.setAttribute("left", "app/home/left.jsp");
		request.setAttribute("main", "app/home/main.jsp");
		
		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	

}
