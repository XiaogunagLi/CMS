<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.MessagePort"%>
<%@page import="com.model.Sys_Menu"%><%@page import="com.model.Sys_Role"%><%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>




<%

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	WebApplicationContext wac = (WebApplicationContext) config.getServletContext().getAttribute(
					WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	List<Sys_Role> roleList = (List<Sys_Role>)request.getAttribute("roles");
	if(roleList == null){
		roleList = new ArrayList();
	}
	
	Map<Integer, Sys_Menu> menuMap = (Map<Integer, Sys_Menu>)request.getAttribute("menus");
	if(menuMap == null){
		menuMap = new HashMap();
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
		<script language="JavaScript" src="plugin/jquery/jquery.js" type="text/javascript"></script>
</head>

<body>
	角色管理  <a href="request?port=<%=MessagePort.ROLE_ADD_VIEW%>">添加</a>
	<hr>
	<table  border="0" cellpadding="3" cellspacing="1" width="90%" align="" style="background-color: #b9d8f3;">
	<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF; font-weight: bold">
		<td>id</td> <td>角色名称</td> <td>菜单权限</td> <td>操作</td> 
	</tr>
	<%
		for(int i=0; i<roleList.size(); i++){
			Sys_Role role = roleList.get(i);
	%>
		<tr bgcolor='#F4FAFF'>
			<td><%= role.getId()%></td>
			<td><%= role.getName()%></td>
			<td>
			<%
				String menus = role.getMenuIds();
				String menus_[] = menus.split(",");
				for(int j=0; j<menus_.length; j++){
					out.write(menuMap.get(Integer.parseInt(menus_[j])).getName());
					if(j != menus_.length - 1){
						out.write(", ");
					}
				}
			%>
			</td>
			<td>
				<a href="request?port=<%=MessagePort.ROLE_UPDATE_VIEW%>&id=<%=role.getId()%>">修改</a>
				<a href="request?port=<%=MessagePort.ROLE_DEL%>&id=<%=role.getId()%>">删除</a>
			</td>
		</tr>
	<%
		}
	%>
	</table>
</body>
</html>
