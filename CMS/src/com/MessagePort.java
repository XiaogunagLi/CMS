package com;

public class MessagePort {
	
	public static final int CHECK = 1;
	
	public static final int MENU_LIST = 2;//顶部菜单
	
	public static final int LOGIN = 20;//登录
	public static final int EXIT = 21;//退出
	
	//目录 30 - 37
	public static final int DIRECTORY = 30;//目录
	public static final int DIRECTORY_LEFT = 31;//目录
	public static final int DIRECTORY_MAIN = 22;//目录MAIN
	public static final int DIRECTORY_ADD1 = 33;//添加目录view
	public static final int DIRECTORY_ADD2 = 34;//添加目录
	public static final int DIRECTORY_DEL = 35;//删除目录
	public static final int DIRECTORY_UPDATE1 = 36;//修改目录view
	public static final int DIRECTORY_UPDATE2 = 37;//修改目录view
	
	
	//内容 50 - 99
	public static final int CONTENT = 50;//内容
	public static final int CONTENT_LEFT = 51;//内容left
	public static final int CONTENT_MAIN = 52;//内容main
	public static final int CONTENT_ADD1 = 53;//添加内容view
	public static final int CONTENT_ADD2 = 54;//添加内容
	public static final int CONTENT_DEL = 55;//删除内容
	public static final int CONTENT_INFO = 56;//查看内容
	public static final int CONTENT_UPDATE_VIEW = 57;//修改view
	public static final int CONTENT_UPDATE = 58;//修改
	public static final int CONTENT_SEARCH = 59;//查询
	
	//菜单 100 - 149
	public static final int MENU = 100;//菜单
	public static final int MENU_MAIN = 102;//菜单main
	public static final int MENU_ADD = 103;//菜单ADD
	public static final int MENU_UPDATE_VIEW = 104;//菜单UPDATE_VIEW
	public static final int MENU_UPDATE = 105;//菜单UPDATE
	public static final int MENU_DEL = 106;//菜单del
	
	//首页 150 - 199
	public static final int HOME = 150;//菜单
	 
	//角色 200 - 249
	public static final int ROLE = 200;//菜单
	public static final int ROLE_LIST = 201;//菜单列表
	public static final int ROLE_MAIN = 202;//菜单main
	public static final int ROLE_ADD_VIEW = 203;//菜单ADD_VIEW
	public static final int ROLE_ADD = 204;//菜单ADD
	public static final int ROLE_UPDATE_VIEW = 205;//菜单UPDATE_VIEW
	public static final int ROLE_UPDATE = 206;//菜单UPDATE
	public static final int ROLE_DEL = 207;//菜单del
	
	//用户管理 250 - 349
	public static final int USER = 250;//用户
	public static final int USER_LEFT = 251;//用户列表
	public static final int USER_MAIN = 252;//用户main
	public static final int USER_ADD_VIEW = 253;//用户ADD_VIEW
	public static final int USER_ADD = 254;//用户ADD
	public static final int USER_UPDATE_VIEW = 255;//用户UPDATE_VIEW
	public static final int USER_UPDATE = 256;//用户UPDATE
	public static final int USER_DEL = 257;//用户del
	
	//活动 350 - 449
	public static final int ACTIVE = 350;//活动
	public static final int ACTIVE_LEFT = 351;//活动列表
	public static final int ACTIVE_MAIN = 352;//活动main
	public static final int ACTIVE_LIST = 353;//活动list
	public static final int ACTIVE_ADD = 354;//活动add
	public static final int ACTIVE_UPDATE = 355;//活动update
	public static final int ACTIVE_DELETE = 356;//活动Delete
	public static final int ACTIVE_TOUPDATE = 357;//活动TOUPDATE
	public static final int ACTIVE_Open = 358;//活动开启
	public static final int ACTIVE_Close = 359;//活动关闭
	//限时成就
	public static final int Deseno_MAIN = 360;//限时成就main
	public static final int Deseno_LIST = 361;//限时成就list
	public static final int Deseno_ADD = 362;//限时成就add
	public static final int Deseno_UPDATE = 363;//限时成就update
	public static final int Deseno_DELETE = 364;//限时成就delete
	public static final int Deseno_TOUPDATE = 365;//限时成就ToUpdate
	public static final int Deseno_Details = 367;//限时成就详细信息
	public static final int Deseno_DetailsADD = 368;//限时成就添加详细信息
	public static final int Deseno_DetailsTOUPDATE = 369;//限时成就修改详细信息
	public static final int Deseno_DetailsUPDATE = 370;//限时成就完成修改详细信息
	public static final int Deseno_DetailsDELETE = 371;//限时成就删除详细信息
	
	//统计 450 - 499
	public static final int STATISTIC = 450;//统计
	public static final int STATISTIC_LEFT = 451;//统计列表
	public static final int STATISTIC_MAIN = 452;//统计main
	public static final int MANAGER_MAIN_LIUCUN = 453;//留存列表
	
	//管理 500 - 600
	public static final int MANAGER_MAIN = 500;//管理main
	public static final int MANAGER_MAIN_LIST = 501;//邮件列表
	public static final int MANAGER_MAIN_SEND = 502;//邮件发送
	public static final int MANAGER_MAIN_DELL = 503;//邮件删除
	
	public static final int MANAGER_PLAYERMANAGER_LIST = 504;//玩家管理
	public static final int MANAGER_PLAYERMANAGER_ADD = 505;//玩家管理add
	public static final int MANAGER_PLAYERMANAGER_DEL = 506;//玩家管理delete
	public static final int MANAGER_GM = 507;//gm命令
	
	
}
