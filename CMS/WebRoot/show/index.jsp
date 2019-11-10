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
    <link rel="stylesheet" href="show/css/my.css" type="text/css"></link>
    
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="show/js/jquery.js"></script>
    <script type="text/javascript" src="show/js/slide.js"></script>
    
    
    <script type="text/javascript">
			window.onload = function() {
				aaa();
				navSelect();
				
				$("#index").css("background-image", "url(show/img/navSelectBg.png)");
				
				$(".nav_label").bind("mouseout", function(){
						if($(this).attr("id") == "index"){
						}else{
							$(this).css("background-image", "url(show/img/navBg.png)");
						}
				});
				
		 }
    </script>
 </head>
  
  <body>
   		<jsp:include page="include/head.jsp" />
   		
   		<!--show-->
   		<div class ="show">
   			<div class="show_left">
   				<div id="focus">
					<ul>
						<%
		   	        		List<Model_Default> topList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 13); 
		   	        		int len1 = topList.size()>5? 5: topList.size();
		   	        		for(int i=0; i<len1; i++){
		   	        			Model_Default ttt = topList.get(i);
		   	        	%>
			   	        	<li>
								<div style="left:0; top:0; width:780px; height:380px;">
									<img src="file/<%=ttt.getUpload() %>" width="780" height="380"/>
								</div>
							</li>
		   	        	<%
		   	        		}
		   	        	%>
					</ul>
				</div>
   			</div>
   			
   			<div class="show_right">
   				<div class="show_right_item1">
	   				<%
	   	        		List<Model_Default> rList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 12);  
	   	        		Model_Default r1 = rList.get(0);
	   	        		Model_Default r2 = rList.get(1);
	   	        		Model_Default r3 = rList.get(2);
	   	        	%>
   					<a href="show/baoming.jsp"><img src="file/<%=r1.getUpload() %>" width="300" height="100"></a>
   				</div>
   				
   				<div class="show_right_item2">
   					<img src="file/<%=r2.getUpload() %>" width="300" height="200">
   				</div>
   				
   				<div class="show_right_bottom">
   					<img src="file/<%=r3.getUpload() %>" width="300" height="70">
   				</div>
   			</div>
   		</div>
   		
   		<!--名师团队-->
   		<div class="content"  style="height: 250px;">
   	        <span class = "content_head">
   	           <b>名师团队</b>
   	           <a href="show/teacher.jsp"><label class="more">更多</label></a>
   	        </span>
   	        
   	        <hr>
   	        <div class="content_content">
   	        	<%
   	        		List<Model_Default> teacherList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 3); 
   	        		int len = teacherList.size() > 10? 10: teacherList.size();
   	        		for(int i=0; i<teacherList.size(); i++){
   	        			Model_Default t = teacherList.get(i);
   	        	%>
   	        		<div class="content_content_item">
	   	        		<a href="show/teacherInfo.jsp?id=<%= t.getId()%>"><img src="file/<%=t.getUpload() %>" width="200" height="150"></a>
	   	        		<div class="content_content_item_name"><%=t.getTitle()%></div>
   	        		</div>	
   	        	<%
   	        		}
   	        	%>
   	        	
   	        </div>
   	        
   		</div>
   		
   		<!--横幅1-->
   		<div class="nav_img">
   				<%
   	        		List<Model_Default> hfList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 11); 
   	        		Model_Default h = hfList.get(0);
   	        	%>
   			<img src="file/<%=h.getUpload() %>" width="1100" height="80">
   		</div>
   		
   		<!--课程培训-->
   		<div class="content" style="height: 390px;">
   	        <span class = "content_head">
   	           <b>课程培训</b>
   	           <a href="show/class.jsp"><label class="more">更多</label></a>
   	        </span>
   	        
   	        <hr>
   	        <div class="content_content">
   	        
   	           <%
   	        		List<Model_Default> classList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 4); 
   	        		len = classList.size() > 4? 4: classList.size();
   	        		for(int i=0; i<len; i++){
   	        			Model_Default t = teacherList.get(i);
   	        	%>
   	        		<a href="show/classInfo.jsp?id=<%= t.getId()%>">
   	        		<div class="content_content_px">
   	        		<span class="content_content_px_head"><b><%=t.getTitle()%></b></span>
   	        		<div class="content_content_px_content">
   	        			&nbsp;&nbsp;&nbsp;
   	        			<%=t.getDesc_()%>
   	        		</div>
   	        		</a>
   	        	</div>
   	        	<%
   	        		}
   	        	%>
   	        </div>
   	        
   		</div>
   		
   		<!--产品介绍-->
   		<div class="content">
   	        <span class = "content_head">
   	           <b>产品介绍</b>
   	           <a href="show/product.jsp"><label class="more">更多</label></a>
   	        </span>
   	        
   	        <hr>
   	        <div class="content_content">
   	        	<%
   	        		List<Model_Default> pList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 6); 
   	        		len = pList.size() > 10? 10: pList.size();
   	        		for(int i=0; i<pList.size(); i++){
   	        			Model_Default p = pList.get(i);
   	        	%>
   	        		<div class="content_content_item">
	   	        		<a href="show/productInfo.jsp?id=<%= p.getId()%>">
	   	        			<img src="file/<%=p.getUpload() %>" width="200" height="150">
	   	        		</a>
	   	        		<%
	   		     				String title = p.getTitle();
	   		     				len = title.length() > 10? 10: title.length();
	   		     				title = title.substring(0, len);
		   		     	%>
	   	        		<div class="content_content_item_name"><%=title%></div>
   	        		</div>	
   	        	<%
   	        		}
   	        	%>
   	        </div>
   		</div>
   		
   		
   		<!--横幅2-->
   		<div class="nav_img">
   			  <%
   	        		List<Model_Default> hf2List = (List<Model_Default>)show.getContentsByDirId("Model_Default", 11); 
   	        		Model_Default h2 = hf2List.get(1);
   	        	%>
   			<img src="file/<%=h2.getUpload() %>" width="1100" height="80">
   		</div>
   		
   		
   		<!--学院环境-->
   		<div class="content">
   	        <span class = "content_head">
   	           <b>学院环境</b>
   	           <a href="show/eve.jsp"><label class="more">更多</label></a>
   	        </span>
   	        
   	        <hr>
   	        <div class="content_content">
   	        	<%
   	        		List<Model_Default> eveList = (List<Model_Default>)show.getContentsByDirId("Model_Default", 5); 
   	        		len = eveList.size() > 10? 10: eveList.size();
   	        		for(int i=0; i<eveList.size(); i++){
   	        			Model_Default t = eveList.get(i);
   	        	%>
   	        		<div class="content_content_item">
   	        			<a href="show/eveInfo.jsp?id=<%= t.getId()%>">
	   	        			<img src="file/<%=t.getUpload() %>" width="200" height="150">
	   	        		</a>
	   	        		<%
	   		     				String title = t.getTitle();
	   		     				len = title.length() > 10? 10: title.length();
	   		     				title = title.substring(0, len);
		   		     	%>
	   	        		<div class="content_content_item_name"><%=title%></div>
   	        		</div>	
   	        	<%
   	        		}
   	        	%>
   	        </div>
   		</div>
   		
   		<jsp:include page="include/foot.jsp" />
   		
  </body>
</html>
