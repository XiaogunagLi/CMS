package com.logic.direction;


import java.util.List;

import javax.xml.registry.infomodel.User;

import com.model.Sys_Directory;
import com.model.Sys_User;
import com.tool.Page;

public class MainPro<T> {
	private int currentDirId;
	private String currentDirName;
	private String currentDirModel;
	private Page page;
	private List<T> list;
	private Object content;
	private Sys_Directory directory;
	private Sys_User user;
	public String getCurrentDirName() {
		return currentDirName;
	}
	public void setCurrentDirName(String currentDirName) {
		this.currentDirName = currentDirName;
	}
	public int getCurrentDirId() {
		return currentDirId;
	}
	public void setCurrentDirId(int currentDirId) {
		this.currentDirId = currentDirId;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public String getCurrentDirModel() {
		return currentDirModel;
	}
	public void setCurrentDirModel(String currentDirModel) {
		this.currentDirModel = currentDirModel;
	}
	
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public Sys_Directory getSys_Directory() {
		return directory;
	}
	public void setSys_Directory(Sys_Directory directory) {
		this.directory = directory;
	}
	public Sys_User getUser() {
		return user;
	}
	public void Sys_User(Sys_User user) {
		this.user = user;
	}
}
