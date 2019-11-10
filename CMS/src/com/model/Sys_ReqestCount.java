package com.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.core.annotation.TableTitle;
@Entity
@Table(name = "sys_reqestCount")
public class Sys_ReqestCount {

	@TableTitle(name = "ID")
	private int id;
	@TableTitle(name = "IP")
	private String ip;
	@TableTitle(name = "Ê±¼ä")
	private String time;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
