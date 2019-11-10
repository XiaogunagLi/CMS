package com.tool;


import java.io.File;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.core.annotation.InputTag;
import com.core.annotation.SelectTag;
import com.core.annotation.TableTitle;
import com.core.annotation.TextAreaTag;

public class FormTag {

	public static List<String> getInputTag(Object obj, Class<?> clazz){
		List<String> inputList = new ArrayList<String>(); 
		//��������
		if(obj != null)//�޸�ʱ����
			addInputTag(obj, clazz.getSuperclass(), inputList);
		
		//��ǰ������  �޸� ��ӵ���
		addInputTag(obj, clazz, inputList);
		
		return inputList;
	}
	
	public static List<String> getTextAreaTag(Object obj, Class<?> clazz){
		List<String> areaList = new ArrayList<String>(); 
		//��������
		if(obj != null)//�޸�ʱ����
			addTextAra(obj, clazz.getSuperclass(), areaList);
		
		//��ǰ������  �޸� ��ӵ���
		addTextAra(obj, clazz, areaList);
		
		return areaList;
	}
	
	public static List<String> getSelectTag(Object obj, Class<?> clazz){
		List<String> selectList = new ArrayList<String>(); 
		addSelect(obj, clazz, selectList);
		
		return selectList;
	}
	//<select name=""><option value="" selected></option>11</select>
	public static void addSelect(Object obj, Class<?> clazz, List<String> areaList){
		Field field[] = clazz.getDeclaredFields();
		for(int j=0; j<field.length; j++){
			String value = "";
			try {
				if(obj != null && field[j].get(obj) != null)
				value = field[j].get(obj).toString();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			StringBuffer sb = new StringBuffer();

			SelectTag tag = field[j].getAnnotation(SelectTag.class);
			if(tag != null){
				field[j].setAccessible(true);
				
				
				sb.append("<tr style=\"text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;\">");
				
				sb.append("<td>");
				sb.append(tag.label());
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("<select name = \"");
				sb.append(tag.name());
				sb.append("\" >");
				
				String keys[] = tag.keys().split(",");
				String values[] = tag.values().split(",");
				for(int i=0; i<keys.length; i++){
					if(value.equals(keys[i])){
						sb.append("<option value=\"" + keys[i] + "\" selected=\"selected\">" + values[i] + "</option>");
					}else{
						sb.append("<option value=\"" + keys[i] + "\">" + values[i] + "</option>");
					}
				}
				sb.append("</select>");
				
				sb.append("</td>");
				
				sb.append("</tr>");
				sb.append("\n");
			}
			areaList.add(sb.toString());
		}
	}
	public static void addInputTag(Object obj, Class<?> clazz, List<String> inputList){
		Field field[] = clazz.getDeclaredFields();
		for(int j=0; j<field.length; j++){
			String value = "";
			try {
				//�޸�ʱԤ��
				if(obj != null && field[j].get(obj) != null){
					value = field[j].get(obj).toString();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			StringBuffer sb = new StringBuffer();
			if(field[j].getName().equals("id")){
				sb.append("<input type = \"hidden\" name = \"id\" value = \"" + value + "\" /> \n");
			}
			InputTag tag = field[j].getAnnotation(InputTag.class);
			if(tag != null){
				field[j].setAccessible(true);
				
				//���ʱ ����Ĭ��ֵ
				if(obj == null){
					value = tag.value();
				}
				
				//ͼƬ��ʾ<img src="" width="" height=""/>
				if(tag.name().equals("upload") && obj != null){
					sb.append("<tr style=\"text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;\">");
					
					sb.append("<td>");
					sb.append(tag.label());
					sb.append("</td>");
					
					sb.append("<td>");
					sb.append("<img src = \"");
					sb.append("file" + File.separator + value);
					sb.append("\" width = \"");
					sb.append(200);
					sb.append("\" height = \"");
					sb.append(200);
					sb.append("\" />");
					sb.append("</td>");
					
					sb.append("</tr>");
					sb.append("\n");
				}
				
				//��ͼƬ�������ֶ���ʾ
				sb.append("<tr style=\"text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;\">");
				
				sb.append("<td>");
				sb.append(tag.label());
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("<input type = \"");
				sb.append(tag.type());
				sb.append("\" name = \"");
				sb.append(tag.name());
				sb.append("\" value = \"");
				sb.append(value);
				sb.append("\" />");
				sb.append("</td>");
				
				sb.append("</tr>");
				sb.append("\n");
			}
			inputList.add(sb.toString());
		}
	}
	//<textarea rows="" cols=""></textarea>
	public static void addTextAra(Object obj, Class<?> clazz, List<String> areaList){
		Field field[] = clazz.getDeclaredFields();
		for(int j=0; j<field.length; j++){
			String value = "";
			try {
				if(obj != null && field[j].get(obj) != null)
				value = field[j].get(obj).toString();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			StringBuffer sb = new StringBuffer();
//			if(field[j].getName().equals("id")){
//				sb.append("<input type = \"hidden\" name = \"id\" value = \"" + value + "\" /> \n");
//			}
			TextAreaTag tag = field[j].getAnnotation(TextAreaTag.class);
			if(tag != null){
				field[j].setAccessible(true);
				
				
				sb.append("<tr style=\"text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;\">");
				
				sb.append("<td>");
				sb.append(tag.label());
				sb.append("</td>");
				
				sb.append("<td>");
				sb.append("<textarea name = \"");
				sb.append(tag.name());
				sb.append("\" rows = \"");
				sb.append(tag.rows());
				sb.append("\" cols = \"");
				sb.append(tag.cols());
				sb.append("\" >");
				sb.append(value);
				sb.append("</textarea>");
				sb.append("</td>");
				
				sb.append("</tr>");
				sb.append("\n");
			}
			areaList.add(sb.toString());
		}
	}

	//��ȡ��ѯ����
	public static String getSearchTag(Class<?> clazz, String fieldName[], String fieldValue[]){
		StringBuffer str = new StringBuffer();
		str.append("<select name = \"field\">");
		str.append("<option value=\"\">��ѡ��</option>");
		
		//��������
		Field fieldParent[] = clazz.getSuperclass().getDeclaredFields();
		for(int j=0; j<fieldParent.length; j++){
			TableTitle tag = fieldParent[j].getAnnotation(TableTitle.class);
			if(tag != null){
//				if(!"".equals(fieldValue) && fieldParent[j].getName().equals(fieldName)){
//					str.append("<option value =\"" + fieldParent[j].getName() + "\" selected=\"selected\">");
//				}else{
					str.append("<option value =\"" + fieldParent[j].getName() + "\">");
//				}
				str.append(tag.name());
				str.append("</option>");
			}
		}
		
		//��ǰ������
		Field field[] = clazz.getDeclaredFields();
		for(int j=0; j<field.length; j++){
			TableTitle tag = field[j].getAnnotation(TableTitle.class);
			if(tag != null){
//				if(!"".equals(fieldValue) && field[j].getName().equals(fieldName)){
//					str.append("<option value =\"" + field[j].getName() + "\" selected=\"selected\">");
//				}else{
					str.append("<option value =\"" + field[j].getName() + "\">");
//				}
				str.append(tag.name());
				str.append("</option>");
			}
		}
		
		str.append("</select>");
//		if(fieldValue != null){
//			str.append("<input type=\"text\" name= \"fieldValue\" value = \"");
//			str.append(fieldValue);
//			str.append("\"/>");
//		}else{
			str.append("<input type=\"text\" name= \"fieldValue\"/>");
//		}
		return str.toString();
	}

	//��ҳ  <a href = "aaaa.action?next=1">��һҳ</a>
	public static String getPartPage(String action, int dirId, int next, int allPage,int allCount, String field[], String fieldValue[]){
	// 	
		StringBuffer page = new StringBuffer();
		String before = null;
		String after = null;
		int b = 0;
		int n = 0;
		
		page.append("��");
		page.append(allCount);
		page.append("��&nbsp;&nbsp;&nbsp;&nbsp;��");
		page.append(next);
		page.append("/");
		page.append(allPage);
		page.append("ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
		
		if(allPage <= 1){
			before = "��ҳ&nbsp;&nbsp;&nbsp;&nbsp;��һҳ&nbsp;&nbsp;&nbsp;&nbsp;";
			after = "��һҳ&nbsp;&nbsp;&nbsp;&nbsp;βҳ";
		}else if(next <= 1){
			n = 2; 
			before = "��ҳ&nbsp;&nbsp;&nbsp;&nbsp;��һҳ&nbsp;&nbsp;&nbsp;&nbsp;";
			after = "<a href = " + action + "&{0}>��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href = " + action + "&{1}>βҳ</a>";
		}else if(next >= allPage){
			b = allPage - 1;
			before = "<a href = " + action + "&{1}>��ҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href = " + action + "&{0}>��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			after = "��һҳ&nbsp;&nbsp;&nbsp;&nbsp;βҳ";
		}else{
			b = next - 1;
			n = next + 1;
			before = "<a href = " + action + "&{1}>��ҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href = " + action + "&{0}>��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;";
			after = "<a href = " + action + "&{0}>��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href = " + action + "&{1}>βҳ</a>";
		}
		
		
		//if(field == null && fieldValue == null){
		if(true){
			page.append(MessageFormat.format(before, "dirId=" + dirId + "&next=" + b, "dirId=" + dirId + "&next=1"));
			page.append(MessageFormat.format(after, "dirId=" + dirId + "&next=" + n, "dirId=" + dirId + "&next=" + allPage));
		}else{
			page.append(MessageFormat.format(before, "dirId=" + dirId + "&next=" + b + "&field=" + field + "&fieldValue=" + fieldValue, "dirId=" + dirId + "&next=" + 1 + "&field=" + field + "&fieldValue=" + fieldValue));
			page.append(MessageFormat.format(after, "dirId=" + dirId + "&next=" + n + "&field=" + field + "&fieldValue=" + fieldValue, "dirId=" + dirId + "&next=" + allPage + "&field=" + field + "&fieldValue=" + fieldValue));
		}
		//System.out.println(page.toString());
		return page.toString();
	}


}
