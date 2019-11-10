<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.model.Sys_User"%>
<%@page import="com.logic.direction.MainPro"%><%@page import="com.model.Sys_Role"%><%@page import="com.MessagePort"%>


<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WebApplicationContext wac = (WebApplicationContext) config.getServletContext().getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	
	List<Sys_User> userList = (List<Sys_User>)request.getAttribute("users");
	if(userList == null){
		userList = new ArrayList();
	}
	
	Map<Integer, Sys_Role> roleMap = (Map<Integer, Sys_Role>)request.getAttribute("roles");
	if(roleMap == null){
		roleMap = new HashMap<Integer, Sys_Role>();
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

		<link rel="stylesheet" href="js/dtree/dtree.css" type="text/css"></link>
		<script type="text/javascript" src="js/dtree/dtree.js"></script>
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>

</head>

<body>
	用户管理
	<a href="request?port=<%=MessagePort.USER_ADD_VIEW%>">添加</a>
	<hr>
	<br>
			<table border="0" cellpadding="3" cellspacing="1" width="90%" align="" style="background-color: #b9d8f3;">
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
					<td> ID </td>  <td>用户名</td> <td> 角色</td><td> 操作 </td>
				</tr>
				<%
				  for(int i=0; i<userList.size(); i++){
					  Sys_User user = userList.get(i);
					  Sys_Role role = roleMap.get(user.getRoleId());
				%>
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td><%=user.getId() %></td>
					<td><%=user.getUserName() %></td>
					<td><%=role.getName()%></td>
					<td>
						<a href="request?port=<%=MessagePort.USER_UPDATE_VIEW%>&id=<%=user.getId()%>">修改</a>
						<a href="request?port=<%=MessagePort.USER_DEL%>&id=<%=user.getId()%>">删除</a>
					</td>
				</tr>
				<%
				  }
				%>
				
			</table>


		
			


	</body>
</html>
