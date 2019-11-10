package com.logic.show;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.model.Sys_Directory;
import com.model.channelModel.Model_Baoming;
import com.model.channelModel.Model_Default;

@Component("show")
@Scope("prototype")
public class ShowAction{
	public ShowServer show;
	public ShowServer getShow() {
		return show;
	}
	@Resource(name="showServer")
	public void setShow(ShowServer show) {
		this.show = show;
	}
	public Object getModel() {
		return null;
	}
	
	public void saveBM(Model_Baoming bm){
		show.saveBm(bm);
	}
	
	public List<Model_Default> getContentsByDirId(String table, int dirId){
		List<Model_Default> list = show.getContentsByDirId(table, dirId);
		if(list == null){
			return Collections.emptyList();
		}
		return list;
	}
	
	public List<Object> getContentByScan(String table, int n){
		List<Object> list = show.getContentsByScan(table, n);
		if(list == null){
			return Collections.emptyList();
		}
		return list;
	}
	
	
	public List<Object> getContentsByDirId(String table, int dirId, int n){
		List<Object> list = show.getContentsByDirId(table, dirId, n);
		if(list == null){
			return Collections.emptyList();
		}
		return list;
	}
	public String getStringLimit(String str, int len, String append){
		if(str.length() > len){
			str = str.substring(0, len) + append;
		}
		return str;
	}

	public Object getModelDefaultById(String table, int id){
		return show.getContentById(table, id);
	}
	
	public Sys_Directory getDirById(int dirId){
		return show.getDirById(dirId);
	}
	
	public Map<String, List<Object>> search(String value){
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		String modelList[] = new String[]{"Model_algorithm","Model_environment",
				"Model_infomation","Model_interview","Model_Java","Model_share" };
		for (String model : modelList) {
			map.put(model, show.getObjecsBySearch(model, value));
		}
		return map;
	}
}
