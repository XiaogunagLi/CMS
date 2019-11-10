<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.Sys_Menu"%><%@page import="com.MessagePort"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sys_Menu menu = (Sys_Menu)request.getAttribute("menu");
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

		<link rel="stylesheet" href="app/js/dtree/dtree.css" type="text/css"></link>
		<script type="text/javascript" src="app/js/dtree/dtree.js">
</script>
	</head>

	<body>
		菜单修改	<a href="request?port=<%=MessagePort.MENU_MAIN%>">返回</a>
		<hr>
		<form action="request">
			<input type="hidden" name="port" value="<%=MessagePort.MENU_UPDATE%>">
			<input type="hidden" name="id" value="<%=menu.getId()%>">
			名称 <input type="text" name="name" value="<%=menu.getName() %>"><br>
			端口 <input type="text" name="port_" value="<%=menu.getPort()%>"><br>
			序号 <input type="text" name="sort" value="<%=menu.getSort()%>"><br>
			<input type="submit" value="提交">
		</form>
	</body>
</html>
