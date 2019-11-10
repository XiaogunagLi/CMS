<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.MessagePort"%><%@page import="com.model.Sys_Menu"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Sys_Menu> menuList = (List<Sys_Menu>)request.getAttribute("menus");
	if(menuList == null){
		menuList = new ArrayList();
	}
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
		添加角色	<a href="request?port=<%=MessagePort.ROLE_MAIN%>">返回</a>
		<hr>
		<form action="request">
			<input type="hidden" name="port" value="<%=MessagePort.ROLE_ADD%>"/>
			名称 <input type="text" name="name"><br><br>
			菜单权限 
			<%
				for(int i=0; i<menuList.size(); i++){
				Sys_Menu menu = menuList.get(i); 	
			%>
				<input type="checkbox" name="ids" value="<%=menu.getId() %>"><%=menu.getName() %> &nbsp;
			<%
				}
			%>
			<br><br>
			<input type="submit" value="提交">
		</form>
	</body>
</html>
