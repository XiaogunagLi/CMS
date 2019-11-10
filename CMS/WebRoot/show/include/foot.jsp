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


  		<div class = "foot">
  		
		    <%
        		List<Model_Default> rList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 15); 
        		Model_Default t1 = rList.get(0); 
        		Model_Default t2 = rList.get(1);
        		Model_Default t3 = rList.get(2);
        	%>
   			<div style="margin-left: auto; margin-right: auto; width: 1100px; height: 35px; margin-top: 5px; text-align: center;font-family:'microsoft yahei', 宋体;line-height:24px;font-size: 14">
   				<%=t1.getDesc_() %>
   			</div>
	
			<div style="margin-left: auto; margin-right: auto; width: 1100px; height: 50px; margin-top: 5px; text-align: center;font-size:32px;font-family:KaiTi_GB2312;">
   				<%=t2.getDesc_() %>
   			</div>
   			
   			<div style="margin-left: auto; margin-right: auto; width: 1100px; height: 50px; margin-top: 5px; text-align: center;font-family:'microsoft yahei', 宋体;line-height:24px;font-size: 14">
   				<%=t3.getDesc_() %>
   			</div>
   		</div>

