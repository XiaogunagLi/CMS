package com.logic.show;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_Directory;
import com.model.channelModel.Model_Baoming;
import com.model.channelModel.Model_Default;

@Component("showServer")
@Scope("singleton")
public class ShowServer {

	private BaseDao dao;

	public BaseDao getStudentDao() {
		return dao;
	}

	@Resource(name = "daoServer")
	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

	public void saveBm(Model_Baoming bm){
		dao.saveObject(bm);
	}
	
	public List<Object> getDirsByParentId(int dirId) {
		List<Object> list = dao.getList("from Directory where parentId = " + dirId);
		return list;
	}
	
	public List<Model_Default> getContentsByDirId(String table, int dirId) {
		List<Model_Default> list = dao.getList("from " + table + " where dirId = " + dirId);
		return list;
	}

	public List<Object> getContentsByScan(String table, int n){
		List<Object> list = dao.getListBySql("from " + table + "  order by countN desc", n);
		return list;
	}
	
	public List<Object> getContentsByTime(String table, int n){
		List<Object> list = dao.getListBySql("from " + table + "  order by createTime desc", n);
		return list;
	}
	
	public List<Object> getContentsByDirId(String table, int dirId, int n){
		List<Object> list = dao.getListBySql("from " + table + " where dirId = " + dirId + "  order by createTime desc", n);
		return list;
	}
	
	public Object getContentById(String table, int id){
		Class<?> clazz = null;
		try {
			clazz = Class.forName("com.model.channelModel." + table);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		return dao.getObjectById(clazz, id);
	}
	
	public Sys_Directory getDirById(int dirId){
		return (Sys_Directory)dao.getObjectById(Sys_Directory.class, dirId);
	}
	
	/** 
	 * Ä£ºý²éÑ¯
	 */
	public List<Object> getObjecsBySearch(String table, String value){
		List<Object> list = dao.getList("from " + table + " where title like '%" + value + "%'");
		return list;
	}
}
