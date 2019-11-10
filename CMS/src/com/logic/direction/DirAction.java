package com.logic.direction;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;
import com.model.Sys_Directory;

public class DirAction{
	
	//跳转目录面板
	@Port(port = MessagePort.DIRECTORY)
	public void init(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("left", "request?port=" + MessagePort.DIRECTORY_LEFT);
		request.setAttribute("main", "request?port=" + MessagePort.DIRECTORY_MAIN);
		
		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}
	
	//left
	@Port(port = MessagePort.DIRECTORY_LEFT)
	public void leftTree(HttpServletRequest request, HttpServletResponse response){
		String tree = DirManager.getInstance().getDirTree("request?port=" + MessagePort.DIRECTORY_MAIN);
		request.setAttribute("tree", tree);

		Dispatcher.dispatcher(request, response, "app/dir/left.jsp");
	}
	
	//main
	@Port(port = MessagePort.DIRECTORY_MAIN)
	public void dirList(HttpServletRequest request, HttpServletResponse response){
		int CurrentDirId = getInt(request, "currentDirId");
		setcurrentList(request, CurrentDirId);
		Dispatcher.dispatcher(request, response, "app/dir/main.jsp");
	}
	
	// 目录添加 跳转的添加页面
	@Port(port = MessagePort.DIRECTORY_ADD1)
	public void addDir1(HttpServletRequest request, HttpServletResponse response) {
		int CurrentDirId = getInt(request, "currentDirId");
		Sys_Directory directory = DirManager.getInstance().getDirById(CurrentDirId);
		if (directory == null) {
			directory = new Sys_Directory();
			directory.setId(0);
			directory.setDisplayName("根目录");
		}
		request.setAttribute("currentDir", directory);
		List<String> modelList = (List<String>) request.getSession().getServletContext().getAttribute("modelList");
		request.setAttribute("modelList", modelList);
		
		Dispatcher.dispatcher(request, response, "app/dir/addDir.jsp");
	}
	
	//目录添加 保存目录
	@Port(port = MessagePort.DIRECTORY_ADD2)
	public void addDir2(HttpServletRequest request, HttpServletResponse response) {
		int CurrentDirId = getInt(request, "currentDirId");
		String displayName = request.getParameter("displayName");
		String model = request.getParameter("model");
		
		Sys_Directory directory = new Sys_Directory();
		directory.setParentId(CurrentDirId);
		directory.setDisplayName(displayName);
		directory.setModelName("com.model.channelModel." + model);
		
		DirManager.getInstance().saveDir(directory);
		
		setcurrentList(request, CurrentDirId);
		
		Dispatcher.dispatcher(request, response, "app/dir/main.jsp");
	}
	
	//修改目录 跳转到修改界面
	@Port(port = MessagePort.DIRECTORY_UPDATE1)
	public void updateDir1(HttpServletRequest request, HttpServletResponse response){
		int id = getInt(request, "id");
		Sys_Directory directory = DirManager.getInstance().getDirById(id);
		request.setAttribute("directory", directory);
		
		Dispatcher.dispatcher(request, response, "app/dir/updateDir.jsp");
	}
	
	//修改目录 保存修改设置
	@Port(port = MessagePort.DIRECTORY_UPDATE2)
	public void updateDir2(HttpServletRequest request, HttpServletResponse response) {
		int id = getInt(request, "id");
		String displayName = request.getParameter("displayName");
		String model = request.getParameter("model");

		Sys_Directory directory = DirManager.getInstance().getDirById(id);
		directory.setDisplayName(displayName);
		directory.setModelName(model);
		
		DirManager.getInstance().updateDir(directory);

		setcurrentList(request, directory.getParentId());
		
		Dispatcher.dispatcher(request, response, "app/dir/main.jsp");
	}
	
	//查看目录
	public void viewDir(HttpServletRequest request, HttpServletResponse response){
		int id = getInt(request, "id");
		
		Sys_Directory directory = DirManager.getInstance().getDirById(id);
		
		request.setAttribute("directory", directory);
	}
	
	//删除目录
	@Port(port = MessagePort.DIRECTORY_DEL)
	public void delDir(HttpServletRequest request, HttpServletResponse response){
		int id = getInt(request, "id");
		Sys_Directory directory = DirManager.getInstance().getDirById(id);
		int n = DirManager.getInstance().delDir(directory);
		if(n != 0){
			request.setAttribute("error", "目录不为空，无法删除");
			Dispatcher.dispatcher(request, response, "app/dir/error.jsp");
			return;
		}
		
		setcurrentList(request, directory.getParentId());
		Dispatcher.dispatcher(request, response, "app/dir/main.jsp");
	}
	
	//设置当前目录列表
	public void setcurrentList(HttpServletRequest request, int CrrentDirId){
		String currentDirName;
		//获取当前目录实例
		Sys_Directory currentDir = DirManager.getInstance().getDirById(CrrentDirId);
		if(currentDir == null){
			currentDirName = "根目录";
		}else{
			currentDirName = currentDir.getDisplayName();
		}
		//获取目录列表
		List<Sys_Directory> dirList = DirManager.getInstance().getDirListByPrentId(CrrentDirId);
		
		MainPro<Sys_Directory> mainPro = new MainPro<Sys_Directory>();
		mainPro.setCurrentDirId(CrrentDirId);
		mainPro.setList(dirList);
		mainPro.setCurrentDirName(currentDirName);
		
		request.setAttribute("mainPro", mainPro);
	}
	
	//获取整形数据
	public int getInt(HttpServletRequest request, String param){
		String currentDirId = (String)request.getParameter(param);
		int CurrentDirId;
		//客户端发送数据有误处理--默认访问根目录
		try{
			CurrentDirId = Integer.parseInt(currentDirId);
		}catch(Exception e){
			//异常处理
			return 0;
		}
		return CurrentDirId;
	}
}
