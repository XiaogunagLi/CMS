package com;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.sf.json.JSONObject;
@Entity
@Table(name = "Admin")
public class Admin {

	private int id;
	@Column(table = "")
	private String address; //   {"aa":11,"bb":22,"cc":33}
	private Map<String, Integer> addressMap = new HashMap<String, Integer>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name  = "")
	public String getAddress() {
		JSONObject json = new JSONObject();
		for (Map.Entry<String, Integer> entry : addressMap.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			json.put(key, value);
		}
		
		return json.toString();
	}
	public void setAddress(String address) {
		JSONObject json = JSONObject.fromObject(address);
		for (Object obj : json.keySet()) {
			String key = obj.toString();
			int value = json.getInt(key);
			addressMap.put(key, value);
		}
	}
	@Transient
	public Map<String, Integer> getAddressMap() {
		return addressMap;
	}
	
}
