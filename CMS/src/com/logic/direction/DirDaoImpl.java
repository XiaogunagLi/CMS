package com.logic.direction;


import java.util.List;

import org.springframework.stereotype.Component;

import com.dao.BaseDao;
import com.model.Sys_Directory;
@Component("dirDao")
public class DirDaoImpl extends BaseDao{
	/**
	 * ����dirId��ȡ�����б�
	 * @param dirId
	 * @return List
	 */
	public List<Object> getContentListByPrentId(String modelName, int dirId){
		return getList("from " + modelName + " where dirId = " + dirId);
	}
	/**
	 * 
	 * ����ID��ȡĿ¼����ʵ��
	 * @param id
	 * @return Sys_Directory
	 */
	public Sys_Directory getDirById(int id){
		 return (Sys_Directory)getObjectById(Sys_Directory.class, id);
	}
	/**
	 * ����parentId��ȡ��Ŀ¼�б�
	 * @param prentId
	 * @return List
	 */
	public List<Sys_Directory> getDirListByPrentId(int prentId){
		return getList("from Sys_Directory where parentId = " + prentId);
	}
}
	
