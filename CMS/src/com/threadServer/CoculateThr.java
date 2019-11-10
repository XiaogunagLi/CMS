package com.threadServer;
import com.dao.BaseDao;
import com.tool.ClassAttr;

public class CoculateThr implements Runnable{
	
	private BaseDao dao;
	private String id;
	private String table;
	public CoculateThr(BaseDao dao, String id, String table){
		this.dao = dao;
		this.id = id;
		this.table = table;
	}

	public void run() {
		Class<?> clazz = null;
		try {
			clazz = Class.forName("lxg.model." + this.table);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		
		int ID = Integer.parseInt(id);
		
//		Object obj = dao.getObjectById(clazz, ID);
//		Integer oldCount = (Integer)ClassAttr.getAttr(obj, Model_Java.class.getSuperclass(), "countN");
//		if(oldCount == null){
//			oldCount = 0;
//		}
//		
//		ClassAttr.setAttr(obj, clazz.getSuperclass(), "countN", oldCount + 1);
//		dao.updateObject(obj);
	}
}