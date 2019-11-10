package com.model;


import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.core.annotation.TableTitle;

@MappedSuperclass
public class Sys_BaseModel implements Serializable{
	public static final long serialVersionUID = -1155504111791704987L;

	@TableTitle(name = "ID")
	public int id;
	
	public int dirId;


	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int title() {
		return id;
	}
	
	public int getDirId() {
		return dirId;
	}
	public void setDirId(int dirId) {
		this.dirId = dirId;
	}
}
