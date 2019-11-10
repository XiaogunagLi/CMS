<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.MessagePort"%>
<%@page import="com.model.Sys_Menu"%>


<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WebApplicationContext wac = (WebApplicationContext) config.getServletContext().getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	List<Sys_Menu> menuList = (List<Sys_Menu>)request.getAttribute("menus");
	if(menuList == null){
		menuList = new ArrayList();
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
		<link rel="stylesheet" href="js/dtree/dtree.css" type="text/css"></link>
		<script type="text/javascript" src="js/dtree/dtree.js"></script>
		<script language="JavaScript" src="app/js/jquery.js" type="text/javascript"></script>
</head>

<body>
	菜单管理  <a href="app/system/menu/add.jsp">添加</a>
	<hr>
	<table  border="0" cellpadding="3" cellspacing="1" width="90%" align="" style="background-color: #b9d8f3;">
	<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
		<td>序号</td> <td>名称</td> <td>port</td> <td>操作</td> 
	</tr>
	<%
		for(int i=0; i<menuList.size(); i++){
			Sys_Menu menu = menuList.get(i);
	%>
		<tr bgcolor='#F4FAFF'>
			<td><%= menu.getSort()%></td>
			<td><%= menu.getName()%></td>
			<td><%= menu.getPort()%></td>
			<td>
				<a href="request?port=<%=MessagePort.MENU_UPDATE_VIEW%>&id=<%=menu.getId()%>">修改</a>
				<a href="request?port=<%=MessagePort.MENU_DEL%>&id=<%=menu.getId()%>">删除</a>
			</td>
		</tr>
	<%
		}
	%>
	</table>
</body>
</html>
