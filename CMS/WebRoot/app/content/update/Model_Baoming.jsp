<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.logic.direction.MainPro"%>
<%@page import="com.model.channelModel.Model_Default"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="com.core.annotation.*"%>
<%@page import="com.MessagePort"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	String modelName = (String)request.getAttribute("modelName");
	int id = (Integer)request.getAttribute("id");
	int dirId = (Integer)request.getAttribute("dirId");
	String dirName = (String)request.getAttribute("dirName");
	List<String> inputList = (List<String>)request.getAttribute("inputList");
	List<String> areaList = (List<String>)request.getAttribute("areaList");
	List<String> selectList = (List<String>)request.getAttribute("selectList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>修改页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="plugin/fckeditor/fckeditor.js"></script>
		<link rel="stylesheet" href="app/css/main.css" type="text/css"></link>
		<script type="text/javascript">
			window.onload = function() {
				//var sBasePath = "<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/plugin/fckeditor/" %>"
				//var oFCKeditor = new FCKeditor( 'content' ) ;
				//oFCKeditor.BasePath	= sBasePath ;
				//oFCKeditor.ReplaceTextarea() ;
		 }
	    </script>
	</head>
	<body>
		<form action="request"  enctype="multipart/form-data" method="post">
			当前目录：<%=dirName%>
			<hr/>
			<input type="hidden" name="port"  value="<%=MessagePort.CONTENT_UPDATE%>">
			<input type="hidden" name="modelName"  value="<%=modelName%>">
			<input type="hidden" name="id"  value="<%=id%>">
			<br>
			<table    border="0" cellpadding="3" cellspacing="1" width="80%" align="" style="background-color: #b9d8f3;">
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
						<a href="request?port=<%=MessagePort.CONTENT_MAIN%>&currentDirId=<%=dirId%>">返回</a>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
