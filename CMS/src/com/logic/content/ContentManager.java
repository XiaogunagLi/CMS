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
	 * 根据id获取内容对象实例
	 * @param id
	 * @return
	 */
	public Object getContentById(Class<?> clazz, int id){
		return (Object) conDao.getObjectById(clazz, id);
	}
	/**
	 * 根据id获取目录对象实例
	 * @param id
	 * @return
	 */
	public Sys_Directory getDirById(int id){
		return (Sys_Directory) conDao.getObjectById(Sys_Directory.class, id);
	}
	/**
	 * 根据目录id获取内容列表
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
	 * 保存内容实例
	 * @param content
	 */
	public int saveContent(Object content){
		return conDao.saveObject(content);
	}
	/**
	 * 修改对象实例
	 * @param content
	 */
	public void updateContent(Object content){
		conDao.updateObject(content);
	}
	/**
	 * 删除对象实例
	 * @param content
	 * @return 0删除成，1删除失败
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
