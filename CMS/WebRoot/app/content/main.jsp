<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="com.core.annotation.TableTitle"%>
<%@page import="com.MessagePort"%>

<%
	String path = request.getContextPath();   
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() 
			+ path + "/";
	WebApplicationContext wac = (WebApplicationContext) config.getServletContext().getAttribute( 
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE); 
	
	Integer dirId = (Integer)request.getAttribute("dirId");
	String dirName = (String)request.getAttribute("dirName");
	String modelName = (String)request.getAttribute("modelName"); 
	List<Object> conList = (List)request.getAttribute("conList");
	String search = (String)request.getAttribute("search");
	String partPage = (String)request.getAttribute("partPage");
%>
<html>
	<head>
		<base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>main</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
		<link rel="stylesheet" href="plugin/dtree/dtree.css" type="text/css"></link>
		<script type="text/javascript" src="plugin/dtree/dtree.js"></script>
		<script language="JavaScript" src="plugin/jquery/jquery.js" type="text/javascript"></script>
</head>

<body>
	<%
		if(dirName == null || dirId == null || modelName == null || conList == null){ 
			out.print("no content in this directory");   
			out.print("<hr>");
		}else{
	%>
	<form action="request" method="get">
	<label>Directory：<%=dirName%></label>   <a href="request?port=<%=MessagePort.CONTENT_ADD1%>&dirId=<%=dirId %>">Add</a>
	<hr>
		<label>Search</label>&nbsp;
		<%=search %><%=search %>
		<input type="hidden" name = "port" value = "<%=MessagePort.CONTENT_SEARCH %>">&nbsp;&nbsp;&nbsp;
		<input type="hidden" name = "dirId" value = "<%=dirId %>">&nbsp;&nbsp;&nbsp;
		<input type="submit" value="search">&nbsp;&nbsp;&nbsp;
	</form>
	<table  border="0" cellpadding="3" cellspacing="1" width="90%" align="" style="background-color: #b9d8f3;">
		<%
		//表头
		    out.print("<tr style=\"text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF; font-weight: bold\">");
		
			Class<?> clazz = Class.forName(modelName);
			//父类
			Class<?> parent = clazz.getSuperclass();
			Field field[] = parent.getDeclaredFields();
			for(int i=0; i<field.length; i++){
				TableTitle tableTitle = field[i].getAnnotation(TableTitle.class);
				if(tableTitle != null){
					out.print("<td>" + tableTitle.name() + "</td>");
				}
			}
			//子类
			field = clazz.getDeclaredFields();
			for(int i=0; i<field.length; i++){
				TableTitle tableTitle = field[i].getAnnotation(TableTitle.class);
				if(tableTitle != null){
					out.print("<td>" + tableTitle.name() + "</td>");
				}
			}
			out.print("<td>operation</td>");
			out.print("</tr>");
			
		//列表
			for(int i=0; i<conList.size(); i++){
				out.print("<tr bgcolor='#F4FAFF'>");
				
				Object obj = conList.get(i);
				clazz = Class.forName(modelName);
				//父类
				parent = clazz.getSuperclass();
				field = parent.getDeclaredFields();
				for(int j=0; j<field.length; j++){
					TableTitle tableTitle = field[j].getAnnotation(TableTitle.class);
					if(tableTitle != null){
						field[j].setAccessible(true);
						out.print("<td>" + field[j].get(obj) + "</td>");
					}
				}
				//子类
				field = clazz.getDeclaredFields();
				for(int j=0; j<field.length; j++){
					TableTitle tableTitle = field[j].getAnnotation(TableTitle.class);
					if(tableTitle != null){
						field[j].setAccessible(true);
						out.print("<td>" + field[j].get(obj) + "</td>");
					}
				}
				
				Field idField = parent.getDeclaredField("id");
				idField.setAccessible(true);
				int id = idField.getInt(obj);
				
				out.print("<td>");
				out.print("<a href= \"request?port=" + MessagePort.CONTENT_INFO + "&id=" + id + "&modelName=" + modelName + "\">modify</a> ");
				//out.print("<a href= \"request?port=" + MessagePort.CONTENT_DEL + "&id=" + id + "&modelName=" + modelName + "\">删除</a>");
				out.print("</td>");
				
				out.print("</tr>");
			}
		%>
	</table>
		<br>
		<%=partPage %>
	<%}%>
	</body>
</html>
