package com.logic.direction;


import java.util.List;

import com.core.SpringContext;
import com.model.Sys_Directory;

public class DirManager {
	private static final DirManager dirManager = new DirManager();
	private DirDaoImpl dirDao = SpringContext.getBeen("dirDao");
	private DirManager(){
	}
	
	public static DirManager getInstance(){
		return dirManager;
	}
	/**
	 * ����Ŀ¼ʵ��
	 * @param Sys_Directory
	 */
	public void saveDir(Sys_Directory dir){
		dirDao.saveObject(dir);
	}
	/**
	 * ����Ŀ¼ʵ��
	 * @param Sys_Directory
	 */
	public void updateDir(Sys_Directory dir){
		dirDao.updateObject(dir);
	}
	/**
	 * ����parentId��ȡ��Ŀ¼�б�
	 * @param prentId
	 * @return List
	 */
	public List<Sys_Directory> getDirListByPrentId(int prentId){
		return dirDao.getList("from Sys_Directory where parentId = " + prentId);
	}
	/**
	 * ɾ����ǰĿ¼
	 * ��ǰĿ¼������Ŀ¼�������ݣ�����ɾ��
	 * ɾ���ɹ�����0��ʧ�ܷ�����Ŀ¼�ĸ��������ݵ�����
	 * @param directory
	 * @return ��ǰĿ¼��Ŀ¼�ĸ���
	 */
	public int delDir(Sys_Directory directory) {
		List<Sys_Directory> dirList = dirDao.getDirListByPrentId(directory.getId());

		// �ж��Ƿ������Ŀ¼
		if (dirList.size() > 0) {
			return dirList.size();
		}
		
		//�Ƿ��������
		List<Object> contentList = dirDao.getContentListByPrentId(directory.getModelName(), directory.getId());
		if (contentList.size() > 0) {
			return contentList.size();
		} else {
			dirDao.delObject(directory);
			return 0;
		}
	}
	/**
	 * 
	 * ����ID��ȡĿ¼����ʵ��
	 * @param id
	 * @return Sys_Directory
	 */
	public Sys_Directory getDirById(int id){
		 return (Sys_Directory)dirDao.getObjectById(Sys_Directory.class, id);
	}
	
	/**
	 * ����Ŀ¼��
	 * @return String
	 */
	public String getDirTree(String type){
		List<Sys_Directory> parentList = dirDao.getList("from Sys_Directory  where parentId = 0");
		if (parentList == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		toTree(sb, dirDao, parentList, type, 0);
		String dirTree = "d = new dTree(\"d\");";
		dirTree += "d.config.useCookies = false;";
		dirTree += "d.config.useStatusText = true;";
		dirTree += "d.config.closeSameLevel = true;";
		dirTree += "d.add(0, -1, \"Root\",\"" + type + "\", \"Root\", \"main\");";
		dirTree += sb.toString();
		String toHtml = "<script type=\"text/javascript\">function treeshow() {"
				+ dirTree + " document.write(d)}treeshow();</script>";
		return toHtml;
	}
	public void toTree(StringBuilder sb, DirDaoImpl dirDao, List<Sys_Directory> childsList, String action, int parentId){
		for(int i=0; i<childsList.size(); i++){
			Sys_Directory curentDir = (Sys_Directory)childsList.get(i);
			List<Sys_Directory> nextList = dirDao.getList("from Sys_Directory where parentId =" + curentDir.getId());
			if(nextList.size()==0){
				sb.append("d.add(" + curentDir.getId() + ", " + parentId + ", \"" + curentDir.getDisplayName() + "\", \"" + action + "&currentDirId=" + curentDir.getId() + "\", \"" + curentDir.getDisplayName() + "\", \"main\");");
			}else{
				sb.append("d.add(" + curentDir.getId() + ", " + parentId + ", \"" + curentDir.getDisplayName() + "\", \"" + action + "&currentDirId=" + curentDir.getId() + "\", \"" + curentDir.getDisplayName() + "\", \"main\");");
				toTree(sb, dirDao, nextList, action, curentDir.getId());
			}
		}
	}
}
