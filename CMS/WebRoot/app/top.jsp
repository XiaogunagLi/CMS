<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.model.*"%>
<%@page import="com.MessagePort"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Sys_User user = (Sys_User)session.getAttribute("user");
List<Sys_Menu> menuList = (List<Sys_Menu>)request.getAttribute("menus");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>

	<style type="text/css">
	   a{ 
		    text-decoration:none; 
	        color:#000000;
	   }
	</style>

  </head>
 
<body>
	<label style="position:relative; float: right; margin-top: 5px;">欢迎您：<%=user.getUserName() %></label>
	<div id = "nav" style="position:relative; float: left; margin-top:40px;margin-left:155px">
	<%
		if(menuList !=null){
			for(int i=0; i<menuList.size(); i++){
				Sys_Menu menu = menuList.get(i);
			%>
				<a href="javascript:void(0)"  style="margin-right: 50px" onclick="parent.document.location='<%=basePath%>request?port=<%=menu.getPort()%>'"><%=menu.getName() %></a>
			<%
			}
		}
	%>
	</div>
	
<body>
</html>