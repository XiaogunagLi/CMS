//package com.core;
//import java.rmi.Naming;
//import java.rmi.RemoteException;
//
//import com.game.gmconnection.net.GmService;
//
//
//public class GameConManager {
//	
//	private final static GameConManager logManager = new GameConManager(1);
//	
//	static GmService gmService = null;
//	
//	public GameConManager(){
//		ini();
//	}
//	
//	public GameConManager(int a){
//		
//	}
//	
//	public static GameConManager getInstance(){
//		return logManager;
//	}
//	
//	public GmService getGmService(){
//		return gmService;
//	}
//	
//	public void ini(){
//		try {
//			String url = ProManager.getInstance().getValue("gameRmi");
//			System.out.println("rmi url----" + url);
//			
//			gmService = (GmService) Naming.lookup(url);
//			System.out.println("rmi  lookup success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 *  class GM_SignInData
//
//		private Integer id;
//		private String title;//活动名称
//		private String desc;//描述
//		private String rule;//规则
//		private long startTime;
//		private long endTime;
//		private String reward;//奖励      {"1":{"type":1,"id":1,"count":1,"vipLevel":1,"multiple":1, "texiao",""}, "2":{"type":1,"id":1,"count":1,"vipLevel":1,"multiple":1}}
//		private String leiji_reward;//累计奖励     {"1":[{"type":1,"id":1,"count":1}, {"type":1,"id":1,"count":1}], "2":[{"type":1,"id":1,"count":1}, {"type":1,"id":1,"count":1}]}
//		private byte state;//1开启 0未开启
//	 */
////	public void sendActive(Object gm_signIndata){
////		try {
////			gmService.sendSignIn(gm_signIndata);
////		} catch (RemoteException e) {
////			e.printStackTrace();
////		}
////	}
//	
////	//活动状态
////	public void activeState(int id, int state){
////		try {
////			gmService.updateSignIn(id,state);
////		} catch (RemoteException e) {
////			e.printStackTrace();
////		}
////	}
//	
//	public String sendEmail(byte type, int roleId, String title, String contetn, String attach){
//		try {
//			return gmService.sendEmail(type, roleId, title, contetn, attach);
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//}
