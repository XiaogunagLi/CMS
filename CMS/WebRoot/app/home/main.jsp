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
		<script language="JavaScript" src="plugin/jquery/jquery.js" type="text/javascript"></script>
</head>

<body>
	首页内容
</body>
</html>
