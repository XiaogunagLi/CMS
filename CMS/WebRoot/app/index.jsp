<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.MessagePort"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head></head>

	<frameset rows="100,*"  border="1" framespacing="0" bordercolor="#ccc">
			<frame name="top" src="request?port=<%=MessagePort.MENU_LIST%>">
			<frameset cols="160,*"   border="1" framespacing="0" bordercolor="#ccc">
				<frame name="left" src="<%=request.getAttribute("left")%>">
				<frame name="main" src="<%=request.getAttribute("main")%>">
			</frameset>
		
	</frameset>

</html>