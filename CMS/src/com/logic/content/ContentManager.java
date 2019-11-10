package com.logic.content;



import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.core.SpringContext;
import com.model.Sys_Directory;

public class ContentManager {

	private static final ContentManager conManager = new ContentManager();
	private ConDaoImpl conDao = SpringContext.getBeen("conDao");
	
	public static ContentManager getInstance(){
		return conManager;
	}
	/**
	 * ����id��ȡ���ݶ���ʵ��
	 * @param id
	 * @return
	 */
	public Object getContentById(Class<?> clazz, int id){
		return (Object) conDao.getObjectById(clazz, id);
	}
	/**
	 * ����id��ȡĿ¼����ʵ��
	 * @param id
	 * @return
	 */
	public Sys_Directory getDirById(int id){
		return (Sys_Directory) conDao.getObjectById(Sys_Directory.class, id);
	}
	/**
	 * ����Ŀ¼id��ȡ�����б�
	 * @param dirId
	 * @return
	 */
	public List<Object> getContentListByDirId(String model, Map<String,String> param, int nextPage, int count){
		StringBuffer hql = new StringBuffer();
		hql.append("from " + model.substring(model.lastIndexOf(".") + 1) + " where 1 = 1 ");
		for (Entry<String, String> element : param.entrySet()) {
			String key = element.getKey();
			String value = element.getValue();
			hql.append(" and ");
			hql.append(key);
			hql.append(" = '");
			hql.append(value);
			hql.append("'");
		}
		return conDao.getList(hql.toString(), (nextPage - 1)*count, count);
	}
	/**
	 * ��������ʵ��
	 * @param content
	 */
	public int saveContent(Object content){
		return conDao.saveObject(content);
	}
	/**
	 * �޸Ķ���ʵ��
	 * @param content
	 */
	public void updateContent(Object content){
		conDao.updateObject(content);
	}
	/**
	 * ɾ������ʵ��
	 * @param content
	 * @return 0ɾ���ɣ�1ɾ��ʧ��
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public int delContent(String model, Object obj){
		conDao.delObject(obj);
		return 0;
	}

	public List<Object> getListByHql(String hql){
		return conDao.getList(hql);
	}
	public int getCount(String model, Map<String,String> param){
		
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from ");
		hql.append(model.substring(model.lastIndexOf(".") + 1));
		hql.append(" where 1 = 1 ");
		for (Entry<String, String> element : param.entrySet()) {
			String key = element.getKey();
			String value = element.getValue();
			hql.append(" and ");
			hql.append(key);
			hql.append(" like '%");
			hql.append(value);
			hql.append("%'");
		}
		return conDao.getCount(hql.toString());
	}
}
