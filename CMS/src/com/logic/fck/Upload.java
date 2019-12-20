package com.logic.fck;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload  extends HttpServlet{
	private static final long serialVersionUID = -4628976586947504713L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession();
			if(session.getAttribute("user") == null){
				System.out.println("not login");
				return;
			}
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) { 
					String value = item.getString();
					if("dirList".equals(value)){
						getDirInfo(response);
					}else if("createDir".equals(value)){
						createDir(items, response);
					}else if("imgList".equals(value)){
						imgList(items, response);
					}else if("upload".equals(value)){
						upload(items, response);
					}
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void upload(List<FileItem> items, HttpServletResponse response){
		try{
			Map<String, Object> map = getParams(items);
			FileItem file = (FileItem)map.get("file");
			String dir = (String)map.get("dir");
			dir = decodePath(dir);
			String name = file.getName();
			String fileName = System.currentTimeMillis() 
								+name.substring(0, name.lastIndexOf(".")) 
								+ name.substring(name.lastIndexOf("."));
			//long size = item.getSize();
			String filePath = getRootPath() + dir + File.separator + fileName;
			File img = new File(filePath);
			if(!img.getParentFile().exists()){
				return;
			}
			File saveFile = new File(filePath);
			file.write(saveFile);
					

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void imgList(List<FileItem> items, HttpServletResponse response){
		Map<String, Object> map = getParams(items);
		String path = (String)map.get("path");
		path = decodePath(path);
		
		File dir = new File(getRootPath() + path);
		File[] files = dir.listFiles();
		JSONArray array = new JSONArray();
		for(int i=0; i<files.length; i++){
			if(files[i].isFile()){
				String name = files[i].getName();
				JSONObject json = new JSONObject();
				json.put("src", path+File.separator+name);
				array.add(json);
			}
		}
		response(response, array.toString());
	}
	
	public void getDirInfo(HttpServletResponse response){
		JSONArray array = new JSONArray();
		String rootPath = getRootPath();
		String ckUpload = rootPath + getServletContext().getInitParameter("fileDir");
		File dir = new File(ckUpload);
		if(!dir.exists()){
			dir.mkdirs();
			response(response, array.toString());
			return;
		}
		int index = 0;
		Queue<DirInfo> queue = new LinkedList<DirInfo>();
		queue.offer(new DirInfo(index++, -1, "root", dir));
		while(!queue.isEmpty()){
			DirInfo info = queue.poll();
			JSONObject json = new JSONObject();
			json.put("id", info.getId());
			json.put("pId", info.getParentId());
			json.put("name", info.getName());
			String path = info.getFile().getPath();
			path = path.substring(path.indexOf("ckUpload"));
			path = encodePath(path);
			
			json.put("path", path);
			array.add(json);
			
			File[] children = info.getFile().listFiles();
			for(int j=0; j<children.length; j++){
				File file = children[j];
				if(file.isDirectory()){
					queue.offer(new DirInfo(index++, info.getId(), file.getName(), file));
				}
			}
		}
		response(response, array.toString());
	}
	
	public void createDir(List<FileItem> items, HttpServletResponse response){
		Map<String, Object> map = getParams(items);
		String path = (String)map.get("path");
		String name = (String)map.get("name");
		path = decodePath(path);
		path = getRootPath() + path + File.separator + name;
		
		File dir = new File(path);
		if(!dir.exists()){
			dir.mkdirs();
		}
	}
	
	public void response(HttpServletResponse response, String json){
		try{
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(json);
			out.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String decodePath(String path){
		String osInfo = System.getProperties().getProperty("os.name").toLowerCase();
		if(osInfo.contains("windows")){
			path = path.replaceAll("#", "\\\\");
		}else{
			path = path.replaceAll("#", "/");
		}
		return path;
	}
	
	public String encodePath(String path){
		String osInfo = System.getProperties().getProperty("os.name").toLowerCase();
		if(osInfo.contains("windows")){
			path = path.replaceAll("\\\\", "#");
		}else{
			path = path.replace("/", "#");
		}
		return path;
	}
	
	public String getRootPath(){
		String rootPath = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "ROOT" + File.separator;
		return rootPath;
	}
	
	public Map<String, Object> getParams(List<FileItem> items){
		Map<String, Object> map = new HashMap<String, Object>();
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) { 
				map.put(item.getFieldName(), item.getString());
			}else{
				map.put(item.getFieldName(), item);
			}
		}
		return map;
	}
}

class DirInfo{
	private int id;
	private int parentId;
	private String name;
	private File file;
	public DirInfo(int id, int parentId, String name, File file){
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
}
