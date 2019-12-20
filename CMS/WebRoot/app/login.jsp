<%@page contentType="text/html" pageEncoding="utf-8"%> 
<%@ page language="java" import="java.util.List"%>
<%@page import="com.MessagePort"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String errorStr = (String)request.getAttribute("error");
%>

<html>
  <head>
    <base href="<%=basePath%>"/>
    
    <title>登陆</title>
    
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script language="JavaScript" src="plugin/jquery/jquery.js" type="text/javascript"></script>
	<script>
	 $(document).ready(function(){
	    $("#login").css({ 
	        position: "absolute", 
	        left: ($(window).width() - $("#login").outerWidth())/2, 
	        top: ($(window).height() - $("#login").outerHeight())/2 
	    });
	});
	</script>
<style>
	#change{
		cursor: pointer;
	}
	#code2{
		color: red;
	}
	#login{ 
		   width:500px;  
		   height:300px;  
		   position:absolute;  
		   border:solid 1px #ddd;
		   left:50%;  
		   top:50%;  
		   background-image: url("app/image/login_bg.png");
		   background-repeat: repeat-x;
		} 
</style>
</head>
	<body style="background-color: #eee;">
		<div id="login">
		<div style="width:500px; text-align:center; margin-top:30px; font-size: 25px; font-family: '黑体'">
			GM&nbsp;&nbsp;管&nbsp;&nbsp;理
		</div>
		  <form action="request?port=<%=MessagePort.LOGIN%>"  method="post">
		    <table align="center" style="margin-top: 20px;">
		     <tr>
                  <td colspan="2"><label id="code2"><%=errorStr==null?"":errorStr %></label></td>
              </tr>
              <tr>
                  <td> 用户</td>
                  <td>
                 	 <input type="text" name="userName"/>
				  </td>
              </tr>
              <tr>
                  <td>密码</td>
                  <td>
                  	<input type="password" name="passWord"/>
				  </td>
              </tr>
              <tr>
                  <td>验证码</td>
                  <td>
                  	 <input type="text" name="code"/>
				  </td>
              </tr>
              <tr>
                  <td></td>
                  <td>
                  	 <img id="code" src="app/imgCode.jsp"/>
                  	 <label id="change">换一张</label>
				  </td>
              </tr>
               <tr>
                  <td>
                 	 <input type="submit" value="登录" style="width: 80px; height: 30px; font-size: 16px;"/>
				  </td>
              </tr>
           </table>
		  </form>
		 </div>
	</body>

</html>