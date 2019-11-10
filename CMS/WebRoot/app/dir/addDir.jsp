<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.*"%>
<%@page import="com.MessagePort"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Sys_Directory directory=(Sys_Directory)request.getAttribute("currentDir");
	List<?> modelList = (List<?>)request.getAttribute("modelList");
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
			<form action="request?port=<%=MessagePort.DIRECTORY_ADD2%>" method="post">
				当前目录：<%=directory.getDisplayName()%>
				<hr>
				<input type="hidden" name="currentDirId"  value="<%=directory.getId()%>">
				<table   border="0" cellpadding="3" cellspacing="1" width="90%"  style="background-color: #b9d8f3;">
				
				 <tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td>名称</td>
					<td><input type="text" name="displayName"></td>
				</tr>
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td>模型</td>
					<td>
						<select name="model">
							<option>选择模板</option>
							<%
								for(int i=0; i<modelList.size(); i++){
									String modelName = (String)modelList.get(i);
									out.print("<option value = '" + modelName + "'>"+ modelName +"</option>");
								}
							%>
						</select>
					</td>
				</tr>
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td><input type="submit" value="保存"></td>
					<td>
						<a href="dirList.action?currentDirId=<%=directory.getId()%>">返回</a>
					</td>
				</tr>
			</table>
			</form>
	</body>
</html>
