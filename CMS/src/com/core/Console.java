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
		
		String portStr = request.getParameter("port");
		List<FileItem> items = null;
		if(portStr == null){
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName(); 
					String value = item.getString();
					String type = item.getContentType();
					if("port".equals(name)){
						portStr = value;
					}
				}
			}
		}
		int port = Integer.valueOf(portStr);
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null && MessagePort.LOGIN != port){
			response.sendRedirect("request?port=" + MessagePort.LOGIN);
			return;
		}
		
		try {
			Method method = CollectionPath.methodMap.get(port);
			if(items == null){
				method.invoke(CollectionPath.objMap.get(port), request, response);
			}else{
				method.invoke(CollectionPath.objMap.get(port), request, response, items);
			}
		} catch (Exception e) {
			response.sendRedirect("request?port=" + MessagePort.LOGIN);
			System.out.println("消息解析异常:" + port + "  " + e.getMessage());
			e.printStackTrace();
		}
	}
}
