<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.logic.direction.MainPro"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="com.core.annotation.*"%>
<%@page import="com.MessagePort"%>
<%@page import="com.model.Sys_Directory"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	Sys_Directory dir = (Sys_Directory)request.getAttribute("dir");
	List<String> inputList = (List<String>)request.getAttribute("inputList");
	List<String> areaList = (List<String>)request.getAttribute("areaList");
	List<String> selectList = (List<String>)request.getAttribute("selectList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>添加页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="plugin/fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
			window.onload = function() {
				var sBasePath = "<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/plugin/fckeditor/" %>"
				var oFCKeditor = new FCKeditor( 'content' ) ;
				oFCKeditor.BasePath	= sBasePath ;
				oFCKeditor.ReplaceTextarea() ;
		 }
	    </script>
	</head>
	<body>
		<form action="request"  enctype="multipart/form-data" method="post">
			<label >当前目录：<%=dir.getDisplayName()%></label>
			<hr/>
			<input type="hidden" name="port"  value="<%=MessagePort.CONTENT_ADD2%>">
			<input type="hidden" name="dirId"  value="<%=dir.getId()%>">
			<input type="hidden" name="modelName"  value="<%=dir.getModelName()%>">
			<br>
			<table   border="0" cellpadding="3" cellspacing="1" width="80%" align="" style="background-color: #b9d8f3;">
				<%
					for(int i=0; i<inputList.size(); i++){
						out.print(String.valueOf(inputList.get(i)));
					}
				
					for(int i=0; i<selectList.size(); i++){
						out.print(String.valueOf(selectList.get(i)));
					}
					
					for(int i=0; i<areaList.size(); i++){
						out.print(String.valueOf(areaList.get(i)));
					}
				%>
				
				<tr style="text-align: left; COLOR: #204648; BACKGROUND-COLOR: #F4FAFF;">
					<td><input type="submit" value="保存"></td>
					<td>
						<a href="request?port=<%=MessagePort.CONTENT_MAIN%>&currentDirId=<%=dir.getId()%>">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
