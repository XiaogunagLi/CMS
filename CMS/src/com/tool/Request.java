package com.tool;

import javax.servlet.http.HttpServletRequest;

public class Request {

	public static String getString(HttpServletRequest request, String key){
		return request.getParameter(key);
	}
	
	public static int getInt(HttpServletRequest request, String key){
		int value_ = 0;
		try{
			String value = request.getParameter(key);
			if(value != null){
				value_ = Integer.parseInt(value);
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return value_;
	}
	
	public static long getLong(HttpServletRequest request, String key){
		long value_ = 0;
		try{
			String value = request.getParameter(key);
			value_ = Long.parseLong(value);
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return value_;
	}
}
