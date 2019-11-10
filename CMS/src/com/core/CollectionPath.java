package com.core;


import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.core.annotation.Port;


public class CollectionPath {
	public static  Map<Integer, Method> methodMap = new HashMap<Integer, Method>();
	public static  Map<Integer, Object> objMap = new HashMap<Integer, Object>();
	
	 //收集消息�?
    public  void collectionPort(String path){
		List<String> portList = new ArrayList<String>();
		File file = new File(path);
		scan(file, portList);

		for (String filePath : portList) {
			Class<?> clazz = null;
			try {
				clazz = Class.forName(filePath);
			} catch (ClassNotFoundException e) {
				System.err.println("加载类异常");
			}
			Object obj = null;
			try {
				obj = clazz.newInstance();
			} catch (Exception e) {
				System.err.println("类实例化异常");
			}

			Method methods[] = clazz.getDeclaredMethods();
			for (Method method : methods) {
				Port port = method.getAnnotation(Port.class);
				if (port != null) {
					methodMap.put(port.port(), method);
					objMap.put(port.port(), obj);
				}
			}
		}
    }
    public  File[] scan(File file, List<String> portList){
    	File fs[] = file.listFiles();
		for (File f : fs) {
			if(f.isDirectory()){
				scan(f, portList);
			}else{
				String path = f.getAbsolutePath();
				if(path.endsWith("Action.class")){
					path = path.substring(path.indexOf(File.separator + "com") + 1, path.indexOf(".class"));
					path = path.replace(File.separator, ".");
					portList.add(path);
					System.out.println(path);
				}
			}
		}
		return null;
    }
}
