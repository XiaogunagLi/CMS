package com.model.channelModel;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.core.annotation.InputTag;
import com.core.annotation.TableTitle;
import com.core.annotation.TextAreaTag;
import com.model.Sys_BaseModel;

@Entity
@Table(name = "m_blog")
public class Model_Blog extends Sys_BaseModel implements Serializable{
	public static final long serialVersionUID = -5464414531499850045L;
	@TableTitle(name = "Title")
	@InputTag(label = "Title", name = "title", type = "text", value = "")
	public String title;
	
	@TableTitle(name = "Date")
	@InputTag(label = "Date", name = "date", type = "text", value = "")
	public String date;
	
	@TextAreaTag(cols = "70", label = "Description", name = "desc_", rows = "3", value = "")
	public String desc_;
	
	@TextAreaTag(cols = "70", label = "content", name = "content", rows = "10", value = "")
	public String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Type(type = "text")
	public String getDesc_() {
		return desc_;
	}

	public void setDesc_(String desc_) {
		this.desc_ = desc_;
	}

	@Type(type = "text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Type(type = "text")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
