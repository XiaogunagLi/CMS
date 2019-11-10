package com.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
@Component
public class SpringContext implements ApplicationContextAware{
	public static ApplicationContext context;
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SpringContext.context = context;
		
		IdManager.getInstance().ini();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBeen(String beenId){
		return (T)context.getBean(beenId);
	}
}
