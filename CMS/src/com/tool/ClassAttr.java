package com.tool;

import java.lang.reflect.Field;

public class ClassAttr {

	public static void setAttr(Object obj, Class<?> clazz, String fieldStr, Object value){
		if(value == null){
			return ;
		}
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldStr);
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object getAttr(Object obj, Class<?> clazz, String fieldStr){
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldStr);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			return null;
		}
		
	}
}
