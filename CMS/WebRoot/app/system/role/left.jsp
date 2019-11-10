<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@page import="com.MessagePort"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
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
		<a href="request?port=<%=MessagePort.ROLE_MAIN%>" target="main">角色管理</a>
	</body>
</html>
