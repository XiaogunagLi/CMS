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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>index</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="show/css/main.css" type="text/css"></link>
    <link rel="stylesheet" href="show/css/teacher.css" type="text/css"></link>
    <link rel="stylesheet" href="show/css/my.css" type="text/css"></link>
    
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="show/js/jquery.js"></script>
    <script type="text/javascript" src="show/js/slide.js"></script>
    
    
    <script type="text/javascript">
			window.onload = function() {
				aaa();
				navSelect();
			$("#phone").css("background-image", "url(show/img/navSelectBg.png)");
				
				$(".nav_label").bind("mouseout", function(){
						if($(this).attr("id") == "phone"){
						}else{
							$(this).css("background-image", "url(show/img/navBg.png)");
						}
				});
		 }
	    </script>
 </head>
  
  <body>
   		<jsp:include page="include/head.jsp" />
   		
   		
   		<div>
   			 <div style="border-bottom: 1px solid #aaa;width: 1100;height: 40;margin-left: auto;margin-right: auto; margin-top: 10px;">
   			 	<span class = "content_head">
   	               <label style="color: #090;"><b>联系我们</b> </label>
   	               <label style="color: #ada; margin-left: 100px;">免费咨询热线：600-616-879</label>
   	       		</span>
   			 </div>
   		     
   		     <div style="border: 0px solid #ddd;width: 1100;height: auto;
   		         margin-left: auto;margin-right: auto; margin-top: 10px;padding: 5px;">
   		     <%
   		 	 List<Model_Default> jiuyeList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 9); 
   		 	   int len = jiuyeList.size()>1? 1: jiuyeList.size();
     			for(int i=0; i<len; i++){
     				Model_Default t = jiuyeList.get(i);
     				out.print(t.getContent());
     			}
   		     %>
   		     </div>
   		</div>
   		
   		
   		
   		<jsp:include page="include/foot.jsp" />
  </body>
</html>
