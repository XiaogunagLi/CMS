package com.logic.content;


import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.MessagePort;
import com.core.Dispatcher;
import com.core.annotation.Port;
import com.logic.direction.DirManager;
import com.model.Sys_Directory;
import com.tool.ClassAttr;
import com.tool.FormTag;
import com.tool.Request;

public class ContentAction {
	private int pageSize = 15;

	//�������
	@Port(port = MessagePort.CONTENT)
	public void conList(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("left", "request?port=" + MessagePort.CONTENT_LEFT);
		request.setAttribute("main", "request?port=" + MessagePort.CONTENT_MAIN);
		
		Dispatcher.dispatcher(request, response, "app/index.jsp");
	}

	//left
	@Port(port = MessagePort.CONTENT_LEFT)
	public void leftTree(HttpServletRequest request, HttpServletResponse response){
		String tree = DirManager.getInstance().getDirTree("request?port=" + MessagePort.CONTENT_MAIN);
		request.setAttribute("tree", tree);

		Dispatcher.dispatcher(request, response, "app/content/left.jsp");
	}
	
	//main
	@Port(port = MessagePort.CONTENT_MAIN)
	public void dirList(HttpServletRequest request, HttpServletResponse response){
		int CurrentDirId = Request.getInt(request, "currentDirId");
		setcurrentList(request, CurrentDirId);
		Dispatcher.dispatcher(request, response, "app/content/main.jsp");
	}
	
	// ��ת�����ҳ��
	@Port(port = MessagePort.CONTENT_ADD1)
	public void addContent1(HttpServletRequest request, HttpServletResponse response) {
		int dirId = Request.getInt(request, "dirId");

		Sys_Directory directory = ContentManager.getInstance().getDirById(dirId);
		if (directory == null) {
			return;
		}

		Class<?> clazz = null;
		try {
			clazz = Class.forName(directory.getModelName());
		} catch (ClassNotFoundException e) {
			System.out.println("addContent  model exception!");
		}
		
		request.setAttribute("dir", directory);
		request.setAttribute("inputList", FormTag.getInputTag(null, clazz));
		request.setAttribute("areaList", FormTag.getTextAreaTag(null, clazz));
		request.setAttribute("selectList", FormTag.getSelectTag(null, clazz));

		String jspName = directory.getModelName().substring(directory.getModelName().lastIndexOf(".") + 1);
		Dispatcher.dispatcher(request, response, "app/content/add/" + jspName + ".jsp");
	}

	//���
	@Port(port = MessagePort.CONTENT_ADD2)
	public void addContent2(HttpServletRequest request, HttpServletResponse response ,List<FileItem> items) throws Exception {
		Iterator iter = null;
		String modelName = null;
		int dirId = 0;
		
		iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//����Ǳ���
			if (item.isFormField()) { 
				// ��ȡname���Ե�ֵ
				String name = item.getFieldName(); 
				// ��ȡvalue���Ե�ֵ
				String value = item.getString(); 
				if("modelName".equals(name)){
					modelName = value;
				}
				
				if("dirId".equals(name)){
					dirId = Integer.parseInt(value);
				}
			}
		}
		
		Class<?> clazz = Class.forName(modelName);
		Object obj = clazz.newInstance();
		
		
		iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//����Ǳ���
			if (item.isFormField()) { 
				// ��ȡname���Ե�ֵ
				String name = item.getFieldName(); 
				// ��ȡvalue���Ե�ֵ
				String value = item.getString(); 
				
				Field field = null;
				try{
					field = clazz.getDeclaredField(name);
				}catch(Exception e){
					continue;
				}
				
				String type = field.getType().toString();
					if(type.endsWith("String")){
						try {
							value = new String(value.getBytes("ISO8859_1"),"utf-8");
							ClassAttr.setAttr(obj, clazz, field.getName(), value);
						} catch (Exception e) {
							System.err.println("");
						}
					}else if(type.endsWith("int")){
						try {
							int Value = Integer.parseInt(value);
							ClassAttr.setAttr(obj, clazz, field.getName(), Value);
						} catch (Exception e) {
							System.err.println("");
						} 
					}else if(type.endsWith("long")){
						try {
							long Value = Long.parseLong(value);
							ClassAttr.setAttr(obj, clazz, field.getName(), Value);
						} catch (Exception e) {
							System.err.println("");
						} 
					}
			} else {
				String name = item.getFieldName(); // �ļ�����name���Ե�ֵ
				String realName = item.getName(); // �ļ���ȫ·��������·�������ļ���
				if(realName == null || "".equals(realName)){
					continue;
				}
				String fileName = System.currentTimeMillis() + realName.substring(realName.lastIndexOf("."));
				//long size = item.getSize(); // �ļ��Ĵ�С�����ֽ�Ϊ��λ
				String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "file";
				String filePath = path + File.separator + fileName;
				File img = new File(filePath);
				if(!img.getParentFile().exists()){
					img.mkdirs();
				}
				File saveFile = new File(filePath); // ����һ��fileָ��һ��������ļ�
				
				ClassAttr.setAttr(obj, clazz, name, fileName);
				try {
					item.write(saveFile);// ���ϴ�������д��һ���ļ���
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		ClassAttr.setAttr(obj, clazz.getSuperclass(), "dirId", dirId);
		
		ContentManager.getInstance().saveContent(obj);
		
		response.sendRedirect(request.getContextPath()+"/request?port=" + MessagePort.CONTENT_MAIN + "&currentDirId=" + dirId);
	}
	
	//��ѯ
	@Port(port = MessagePort.CONTENT_SEARCH)
	public void search(HttpServletRequest request, HttpServletResponse response) {
		int dirId = Request.getInt(request, "dirId");
		setcurrentList(request, dirId);
		Dispatcher.dispatcher(request, response, "app/content/main.jsp");
	}
	
	//ɾ��
	@Port(port = MessagePort.CONTENT_DEL)
	public void del(HttpServletRequest request, HttpServletResponse response) {
		int id = Request.getInt(request, "id");
		String modelName = request.getParameter("modelName");
		
		Class<?> clazz = null;
		try {
			clazz = Class.forName(modelName);
		} catch (ClassNotFoundException e) {
			System.out.println("model exception  port" + MessagePort.CONTENT_INFO);
			e.printStackTrace();
		}
		Object obj = ContentManager.getInstance().getContentById(clazz, id);
		Object objImgPath = ClassAttr.getAttr(obj, clazz, "upload");
		if(objImgPath != null){
			String imgName = ClassAttr.getAttr(obj, clazz, "upload").toString();
			String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "file";
			String filePath = path + File.separator + imgName;
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}
		ContentManager.getInstance().delContent(modelName, obj);
		
		Object dirId = ClassAttr.getAttr(obj, clazz.getSuperclass(), "dirId");
		
		Dispatcher.dispatcher(request, response, "request?port=" + MessagePort.CONTENT_MAIN + "&currentDirId=" + dirId);
	}
	
	// �޸�view
	@Port(port = MessagePort.CONTENT_INFO)
	public void updateContent1(HttpServletRequest request, HttpServletResponse response) {
		int id = Request.getInt(request, "id");
		String modelName = request.getParameter("modelName");

		Class<?> clazz = null;
		try {
			clazz = Class.forName(modelName);
		} catch (ClassNotFoundException e) {
			System.out.println("model exception  port"
					+ MessagePort.CONTENT_INFO);
		}
		Object obj = ContentManager.getInstance().getContentById(clazz, id);

		Sys_Directory directory = ContentManager.getInstance().getDirById((Integer)(ClassAttr.getAttr(obj, clazz.getSuperclass(), "dirId")));

		request.setAttribute("id", id);
		request.setAttribute("dirId", directory.getId());
		request.setAttribute("dirName", directory.getDisplayName());
		request.setAttribute("modelName", modelName);
		request.setAttribute("inputList", FormTag.getInputTag(obj, clazz));
		request.setAttribute("areaList", FormTag.getTextAreaTag(obj, clazz));
		request.setAttribute("selectList", FormTag.getSelectTag(obj, clazz));

		String jspName = directory.getModelName().substring(directory.getModelName().lastIndexOf(".") + 1);
		Dispatcher.dispatcher(request, response, "app/content/update/" + jspName + ".jsp");
	}
	
	//�޸�
	@Port(port = MessagePort.CONTENT_UPDATE)
	public void update2(HttpServletRequest request, HttpServletResponse response ,List<FileItem> items) throws Exception {
		Iterator iter = null;
		String modelName = null;
		int id = 0;
		
		iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//����Ǳ���
			if (item.isFormField()) { 
				// ��ȡname���Ե�ֵ
				String name = item.getFieldName(); 
				// ��ȡvalue���Ե�ֵ
				String value = item.getString(); 
				if("modelName".equals(name)){
					modelName = value;
				}
				
				if("id".equals(name)){
					id = Integer.parseInt(value);
				}
			}
		}
		
		Class<?> clazz = Class.forName(modelName);
		Object obj = ContentManager.getInstance().getContentById(clazz, id);
		
		iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			//����Ǳ���
			if (item.isFormField()) { 
				// ��ȡname���Ե�ֵ
				String name = item.getFieldName(); 
				// ��ȡvalue���Ե�ֵ
				String value = item.getString(); 
				
				Field field = null;
				try{
					field = clazz.getDeclaredField(name);
				}catch(Exception e){
					continue;
				}
				
				String type = field.getType().toString();
					if(type.endsWith("String")){
						try {
							value = new String(value.getBytes("ISO8859_1"),"utf-8");
							ClassAttr.setAttr(obj, clazz, field.getName(), value);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else if(type.endsWith("int")){
						try {
							int Value = Integer.parseInt(value);
							ClassAttr.setAttr(obj, clazz, field.getName(), Value);
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}else if(type.endsWith("long")){
						try {
							long Value = Long.parseLong(value);
							ClassAttr.setAttr(obj, clazz, field.getName(), Value);
						} catch (Exception e) {
							e.printStackTrace();
						} 
					}
			} else {
				String name = item.getFieldName(); // �ļ�����name���Ե�ֵ
				String realName = item.getName(); // �ļ���ȫ·��������·�������ļ���
				if(realName == null || "".equals(realName)){
					continue;
				}
				String fileName = System.currentTimeMillis() + realName.substring(realName.lastIndexOf("."));
				//long size = item.getSize(); // �ļ��Ĵ�С�����ֽ�Ϊ��λ
				//String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "file";
				String path = request.getRealPath("/") + "file";
				String filePath = path + File.separator + fileName;
				File img = new File(filePath);
				if(!img.getParentFile().exists()){
					img.getParentFile().mkdirs();
				}
				File saveFile = new File(filePath); // ����һ��fileָ��һ��������ļ�
				
				//ɾ����ͼƬ
				if(ClassAttr.getAttr(obj, clazz, name) != null){
					String oldPath = path + File.separator + ClassAttr.getAttr(obj, clazz, name).toString();
					File f = new File(oldPath);
					if(f.exists()){
						f.delete();
					}
				}
				
				ClassAttr.setAttr(obj, clazz, name, fileName);
				try {
					item.write(saveFile);// ���ϴ�������д��һ���ļ���
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		ContentManager.getInstance().updateContent(obj);
		int dirId = (Integer)(ClassAttr.getAttr(obj, clazz.getSuperclass(), "dirId"));
		response.sendRedirect(request.getContextPath()+"/request?port=" + MessagePort.CONTENT_MAIN + "&currentDirId=" + dirId);
	}
	

	/**
	 * ���õ�ǰ�����б�
	 */
	public void setcurrentList(HttpServletRequest request, int CrrentDirId) {
		// ��ȡ��ǰĿ¼ʵ��
		Sys_Directory currentDir = ContentManager.getInstance().getDirById(
				CrrentDirId);
		if (currentDir != null) {
			Map<String, String> map = new HashMap<String, String>();

			// ������ѯ
			String field[] = request.getParameterValues("field");
			String fieldValue[] = request.getParameterValues("fieldValue");
			if(field != null && fieldValue != null){
				for (int i = 0; i < fieldValue.length; i++) {
					if (field[i] != null && fieldValue[i] != null && !"".equals(fieldValue[i])&& !"".equals(field[i])) {
						map.put(field[i], fieldValue[i]);
					}
				}
			}
			
			// ��ҳ
			int nextPageN = 1;
			String nextPage = request.getParameter("next");
			if (nextPage != null) {
				nextPageN = Integer.parseInt(nextPage);
			}
			map.put("dirId", String.valueOf(CrrentDirId));
			int allCount = ContentManager.getInstance().getCount(
					currentDir.getModelName(), map);
			int allPage = (allCount + pageSize - 1) / pageSize;
			String partPage = FormTag.getPartPage("request?port="
					+ MessagePort.CONTENT_SEARCH, CrrentDirId, nextPageN,
					allPage, allCount, field, fieldValue);

			// ��ȡ�б�
			List<Object> conList = ContentManager.getInstance()
					.getContentListByDirId(currentDir.getModelName(), map,
							nextPageN, pageSize);

			request.setAttribute("dirId", CrrentDirId);
			request.setAttribute("conList", conList);
			request.setAttribute("dirName", currentDir.getDisplayName());
			request.setAttribute("modelName", currentDir.getModelName());
			request.setAttribute("partPage", partPage);
			try {
				request.setAttribute("search", FormTag.getSearchTag(
						Class.forName(currentDir.getModelName()), field,
						fieldValue));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
