<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.logic.*"%>
<%@page import="com.model.Sys_Directory"%>
<%@page import="com.logic.direction.MainPro"%>
<%@page import="com.MessagePort"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WebApplicationContext wac = (WebApplicationContext) config
			.getServletContext()
			.getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	MainPro mainPro=(MainPro)request.getAttribute("mainPro");
	List dirList = mainPro.getList();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
		<link rel="stylesheet" href="js/dtree/dtree.css" type="text/css"></link>
		<script type="text/javascript" src="js/dtree/dtree.js">
</script>
	</head>
	<body>
	
		当前目录：<%= mainPro.getCurrentDirName() %>
		<a href = "request?port=<%=MessagePort.DIRECTORY_ADD1%>&currentDirId=<%=mainPro.getCurrentDirId()%>">添加</a>
		<hr>
		<table  border="0" cellpadding="3" cellspacing="1" width="90%" align="" style="background-color: #b9d8f3;">
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
				<td>ID</td>
				<td>名称</td>
				<td>模板</td>
				<td>操作</td>
			</tr>
		<%
			if(dirList != null){
				for(int i=0; i<dirList.size(); i++){
					Sys_Directory directory = (Sys_Directory)dirList.get(i);
		 %>
			 <tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
			 	<td><%= directory.getId()%></td>
			 	<td><%= directory.getDisplayName()%></td>
			 	<td><%= directory.getModelName()%></td>
			 	<td>
			 		<a href="request?port=<%=MessagePort.DIRECTORY_UPDATE1%>&id=<%=directory.getId()%>">编辑</a>
			 		<a href="request?port=<%=MessagePort.DIRECTORY_DEL%>&id=<%=directory.getId()%>">删除</a>
			 	</td>
			 </tr>
		 <%
				}
			}
		%>
		</table>
	</body>
</html>
