package com.core;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class ProManager {
	
	private final static ProManager proManager = new ProManager();
	private Properties pro = new Properties();
	
	public ProManager(){
		ini();
	}
	
	public static ProManager getInstance(){
		return proManager;
	}
	
	public void ini(){
		//文本日志
		String tomcatPath = System.getProperty("catalina.home");
		String proConfig = tomcatPath + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "WEB-INF" + File.separator + "config" + File.separator + "pro.properties";
		
		try {
			pro.load(new FileInputStream(proConfig));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key){
		return pro.getProperty(key);
	}
}
