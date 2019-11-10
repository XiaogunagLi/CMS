package com.core;


import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.MessagePort;

public class Console extends HttpServlet{
	private static final long serialVersionUID = -8721978296072660955L;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("ISO8859_1");
		
		List<FileItem> items = null;
		try {
			items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) { // 如果是表单域 ，就是非文件上传元素
					String name = item.getFieldName(); // 获取name属�?的�?
					String value = item.getString(); // 获取value属�?的�?
					String type = item.getContentType();
					if("port".equals(name)){
					    String	port = value;
					    System.out.println("message Port:" + port);
						HttpSession session = request.getSession();
						int PORT = Integer.parseInt(port);
						if(session.getAttribute("user") == null && !String.valueOf(MessagePort.LOGIN).equals(port)&& PORT < 500){
							response.sendRedirect("request?port=" + MessagePort.LOGIN);
							return;
						}
						
						//权限检测
						if(session.getAttribute("pc") != null){
							PowerCheckManager pc = (PowerCheckManager) session.getAttribute("pc");
							if(!pc.check(session, PORT)){
								return;
							}
						}
						
						try {
							Method method = CollectionPath.methodMap.get(PORT);
							method.invoke(CollectionPath.objMap.get(PORT), request, response, items);
						} catch (Exception e) {
							System.out.println("消息解析异常:" + port + "  " + e.getMessage());
							e.printStackTrace();
						}
						break;
					}
				}
			}
		} catch (FileUploadException e) {
			String port = request.getParameter("port");
			System.out.println("message Port:" + port);
			HttpSession session = request.getSession();
			int PORT = Integer.parseInt(port);
			if(session.getAttribute("user") == null && !String.valueOf(MessagePort.LOGIN).equals(port)&& PORT < 500){
				response.sendRedirect("request?port=" + MessagePort.LOGIN);
				return;
			}
			
			//权限检测
			if(session.getAttribute("pc") != null){
				PowerCheckManager pc = (PowerCheckManager) session.getAttribute("pc");
				if(!pc.check(session, PORT)){
					return;
				}
			}
			
			
			try {
				Method method = CollectionPath.methodMap.get(PORT);
				method.invoke(CollectionPath.objMap.get(PORT), request, response);
			} catch (Exception e1) {
				System.out.println("消息解析异常:" + port + "  " + e.getMessage());
				e1.printStackTrace();
			}
		} 
		
	}
	
}
