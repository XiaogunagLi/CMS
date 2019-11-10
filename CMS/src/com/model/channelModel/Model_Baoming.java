package com.model.channelModel;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.core.annotation.InputTag;
import com.core.annotation.TableTitle;
import com.model.Sys_BaseModel;

@Entity
@Table(name = "baoming")
public class Model_Baoming extends Sys_BaseModel implements Serializable{
	public static final long serialVersionUID = -5464414531499850045L;
	@TableTitle(name = "����")
	@InputTag(label = "����", name = "name", type = "text", value = "")
	public String name;
	
	
	@TableTitle(name = "�Ա�")
	@InputTag(label = "�Ա�", name = "sex", type = "text", value = "")
	public String sex;
	
	@TableTitle(name = "�绰")
	@InputTag(label = "�绰", name = "phone", type = "text", value = "")
	public String phone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
