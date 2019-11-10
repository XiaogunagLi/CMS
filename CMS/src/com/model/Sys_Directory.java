package com.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;
@Entity
@Table(name = "sys_directory")
public class Sys_Directory {
	private int id;
	private int parentId;
	private int count;//������
	private String displayName;
	private String modelName;
	//������
	private int createUser;
	private Date createTime; 
	private String descrip;
	//��չ�ֶΣ�����ʹ��
	private String othor;
	private Map<String, String> pool = new HashMap<String, String>();
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public String getOthor() {
		return othor;
	}
	public void setOthor(String othor) {
		/**
		 * ��ȡpool�е����ݴ�ŵ�othor�У�������ֻ��pool���в���
		 */
		this.othor = othor;
	}
	@Transient
	public Map<String, String> getPool() {
		if(this.getOthor().length() > 0){
			JSONObject othor = JSONObject.fromObject(this.getOthor());  
			Map<String, String> pool = (Map)othor;
			return pool;
		}else{
			return null;
		}
	}
	/**
	 * ��pool����ת�����ַ�����ֵ��othor
	 */
	public void setPool() {
		this.othor = this.getPool().toString();
	}
	@Transient
	public Map<String, String> getPool_() {
		return pool;
	}
	
}
