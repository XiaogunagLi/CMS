package com.tool;


import java.util.Date;

import org.apache.commons.collections.map.LinkedMap;

import com.dao.BaseDao;
import com.model.Sys_ReqestCount;

public class ColCount {
	public static LinkedMap map = new LinkedMap();
	public static long tap = 1*1000;//10*60*1000
	
	public static synchronized void col(String ip, BaseDao dao){
		Long time = (Long) map.get(ip);
		if(time == null){
			map.put(ip, new Date().getTime());
			Sys_ReqestCount count = new Sys_ReqestCount();
			count.setIp(ip);
			count.setTime(new Date().toLocaleString().replaceAll(" ", "/"));
			dao.saveObject(count);
			return;
		}
		
		if(new Date().getTime() - time < tap){//ʮ�����ڶ�η��ʲ�ͳ��
			return;
		}
		
		if(map.size() >= 100){
			map.remove(0);
		}
		//ͳ�Ʒ�����
		Sys_ReqestCount count = new Sys_ReqestCount();
		count.setIp(ip);
		count.setTime(new Date().toLocaleString().replaceAll(" ", "/"));
		dao.saveObject(count);
		
		map.put(ip, new Date().getTime());
		return ;

		
		
		
	}

}
