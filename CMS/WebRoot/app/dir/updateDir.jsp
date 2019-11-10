<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.*"%>
<%@page import="com.MessagePort"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sys_Directory directory=(Sys_Directory)request.getAttribute("directory");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'contentAdd.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
	</head>

	<body>
		<form action="request?port=<%=MessagePort.DIRECTORY_UPDATE2%>" method="post">
			<input type="hidden" name="id"   value="<%=directory.getId()%>">
			<br>
			<table  border="0" cellpadding="3" cellspacing="1" width="90%"  style="background-color: #b9d8f3;">
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td>名称</td>
					<td>
						<input type="text" name="displayName" value="<%=directory.getDisplayName()%>">
					</td>
				 </tr>
				 <tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td>模板</td>
					<td>
						<input type="text" name="model" value="<%=directory.getModelName()%>">
					</td>
				</tr>
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td>
						<input type="submit" value="确定">
					</td>
					<td>
						<a href="request?port=<%=MessagePort.DIRECTORY_MAIN%>&currentDirId=<%=directory.getParentId()%>">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
