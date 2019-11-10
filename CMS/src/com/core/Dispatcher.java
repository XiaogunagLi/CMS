package com.core;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher {
	public static void dispatcher(HttpServletRequest request, HttpServletResponse response, String page){
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}                                         
	}
}
