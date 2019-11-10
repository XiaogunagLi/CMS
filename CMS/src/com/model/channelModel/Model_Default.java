package com.model.channelModel;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.core.annotation.InputTag;
import com.core.annotation.TableTitle;
import com.core.annotation.TextAreaTag;
import com.model.Sys_BaseModel;

@Entity
@Table(name = "log_default")
public class Model_Default extends Sys_BaseModel implements Serializable{
	public static final long serialVersionUID = -5464414531499850045L;
	@TableTitle(name = "标题")
	@InputTag(label = "标题", name = "title", type = "text", value = "")
	public String title;
	
	@TextAreaTag(cols = "70", label = "描述", name = "desc_", rows = "3", value = "")
	public String desc_;
	
	@TextAreaTag(cols = "70", label = "内容", name = "content", rows = "10", value = "")
	public String content;
	
	@InputTag(label = "图片", name = "upload", type = "file", value = "")
	public String upload;

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

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
}
