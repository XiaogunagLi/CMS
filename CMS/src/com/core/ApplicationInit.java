package com.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInit implements ServletContextListener{
	
	public void contextInitialized(ServletContextEvent event) {
		//收集消息号
		String rootPath = event.getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com" + File.separator + "logic";
		String rootPath2 = event.getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com" + File.separator + "user_logic";
		
		System.out.println("system path------------------------------" + rootPath);
		System.out.println("user path------------------------------" + rootPath2);
		
		CollectionPath cp = new CollectionPath();
		cp.collectionPort(rootPath);
		cp.collectionPort(rootPath2);
		
		//收集model
		String projectPath = event.getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "com" + File.separator + "model" + File.separator + "channelModel";
		event.getServletContext().setAttribute("modelList", getModelList(projectPath));
	}
	public void contextDestroyed(ServletContextEvent event) {
	}
	
	public List<String> getModelList(String projectPath){
		//获取模板列表
		File file = new File(projectPath);
		List<String> modelList = new ArrayList<String>();
		if(file.exists()){
			File[] models = file.listFiles();
			for (File model : models) {
				String modelName = model.getName();
				if(modelName.startsWith("Model")){
					modelName = modelName.substring(0, modelName.indexOf("."));
					modelList.add(modelName);
				}
			}
		}
		return modelList;
	}
	
}