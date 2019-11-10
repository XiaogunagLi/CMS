<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.logic.direction.MainPro"%>
<%@page import="com.model.Sys_User"%><%@page import="com.MessagePort"%><%@page import="com.model.Sys_Role"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	Sys_User user = (Sys_User)request.getAttribute("user");
	
	List<Sys_Role> roleList = (List<Sys_Role>)request.getAttribute("roles");
	if(roleList == null){
		roleList = new ArrayList();
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
		<script type="text/javascript" src="js/dtree/dtree.js">
		</script>
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>

	</head>

	<body>
		用户修
		<hr>
		<form action="request" method="post">
		<input type="hidden" name="port"  value="<%=MessagePort.USER_UPDATE%>"> 
		<input type="hidden" name="id"  value="<%=user.getId()%>">
		<table border="0" cellpadding="3" cellspacing="1" width="90%" style="background-color: #b9d8f3;">
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
				<td>用户</td><td><input type="text" name="userName" value="<%=user.getUserName() %>"></td>
			</tr>
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
				<td>旧密码</td><td><input type="password" name="passWord"></td>
			</tr>
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
				<td>新密码</td><td><input type="password" name="newPassWord"></td>
			</tr>
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
				<td>角色</td>
				<td>
					<select name = "role">
						<%
							for(int i=0; i<roleList.size(); i++){
								Sys_Role role = roleList.get(i);
						%>
							<option  value="<%=role.getId() %>" <%=user.getRoleId()==role.getId()?"selected=\"selected\"":"" %>><%=role.getName() %></option>
						<%
							}
						%>
					</select>
				</td>
			</tr>
			<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
				<td><input type="submit" value="确定"></td>
				<td><a href="javascript:history.go(-1)">返回</a>
			</tr>
		</table>
		</form>
	</body>
</html>
