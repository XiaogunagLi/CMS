package com.threadServer;
import com.dao.BaseDao;
import com.tool.ColCount;

public class CountThr implements Runnable{
	
	private BaseDao dao;
	private String ip;
	public CountThr(BaseDao dao, String ip){
		this.dao = dao;
		this.ip = ip;
	}

	public void run() {
		//ColCount.col(ip,dao);
	}
}