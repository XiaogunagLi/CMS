<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="com.logic.show.ShowAction"%>
<%@page import="com.model.channelModel.Model_Default"%>

<%
String path = request.getContextPath();   
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort() 
		+ path + "/";
WebApplicationContext wac = (WebApplicationContext) config.getServletContext().getAttribute( 
				WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE); 

ShowAction show=(ShowAction)wac.getBean("show");

%>


  		<!--head-->
   		<div class="head">
   			<div class="head_content">
   				   <%
	   	        		List<Model_Default> rList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 14); 
	   	        		Model_Default t = rList.get(0);
	   	        	%>
   				<img src="file/<%=t.getUpload() %>" width="1100" height="150">
   			</div>
   		</div>
   		
   		<!--nav-->
   		<div class="nav">
   			<div class="nav_content" id="nav_content">
   			 <a href="show/index.jsp"><span class = "nav_label"   id="index"><b>网站首页</b></span></a>
   			 <a href="show/product.jsp"><span class = "nav_label" id="product"><b>产品介绍</b></span></a>
   			 <a href="show/teacher.jsp"><span class = "nav_label" id="teacher"><b>专家师资</b></span></a>
   			 <a href="show/eve.jsp"><span class = "nav_label"     id="eve"><b>学习环境</b></span></a>
   			 <a href="show/class.jsp"><span class = "nav_label"   id="class"><b>课程培训</b></span></a>
   			 <a href="show/jiuye.jsp"><span class = "nav_label"   id="jiuye"><b>就业创业</b></span></a>
   			 <a href="show/zhaosheng.jsp"><span class = "nav_label" id="zhaosheng"><b>招生简章</b></span></a>
   			 <a href="show/phone.jsp"><span class = "nav_label"   id="phone"><b>联系我们</b></span></a>
   			</div>
   		</div>

