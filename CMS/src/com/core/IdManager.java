package com.core;


public class IdManager {
	private final static IdManager idManager = new IdManager();
	
	private int baseId = 0;
	
	private int playerManagerId;
	private int desenoDetailsManagerId;
	
	private IdManager(){
	}
	
	public static IdManager getInstance(){
		return idManager;
	}
	
	public void ini(){
//		String gmId = ProManager.getInstance().getValue("gmId");
//		baseId = Integer.valueOf(gmId);
	}

	public synchronized int getPlayerManagerId() {
		playerManagerId++;
		return playerManagerId;
	}
	
	public synchronized int getDesenoDetailsManagerId() {
		desenoDetailsManagerId++;
		return desenoDetailsManagerId;
	}
}
