<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	#login{height:200px;width: 600px; }
	#logout{height:200px;width: 600px; }
	button{height:200px;width:600px;}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>Form Elements - Ace Admin</title>
<script src="jquery-1.9.1.min.js"></script>
</head>
	
	<body>
	<!--<%=request.getContextPath() %>-->
	<button onclick="login()" id="login">login</button> </br><div ></div></br></br>
	<button onclick="logout()" id="logout">logout</button> </br><div ></div>
	<button onclick="list()" id="list">list</button> </br><div ></div>
	<button onclick="fom()" id="fom">form</button> </br><div ></div>
	<button onclick="tmsPolicyssave()" id="tmsPolicyssave">tmsPolicyssave</button> </br><div ></div>
	<button onclick="tmsPolicysdel()" id="tmsPolicysdel">tmsPolicysdel</button> </br><div ></div>
	<button onclick="tmsPolicysList()" id="tmsPolicysList">tmsPolicysList</button> </br><div ></div>
	</body>
<script >
	function login(){
		$.ajax({
			  type:"POST",
              url:"<%=request.getContextPath() %>/a/login",
			  dataType: 'json',
              data:{'username':'thinkgem','password':'admin'},
              success:function(data){
			  
                $("#login").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#login").html(JSON.stringify(data));
              }
 
    });

	}
	
	function logout(){
		$.ajax({
			  type:"POST",
              url:"<%=request.getContextPath() %>/a/logout",
			  dataType: 'json',
              data:"",
              success:function(data){
			  
			  $("#logout").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#logout").html(JSON.stringify(data));
              }
 
    });
	}
	function list(){
		$.ajax({
			  type:"POST",
              url:"<%=request.getContextPath() %>/a/tmsmenu/tmsMenuMain",
			  dataType: 'json',
              data:"",
              success:function(data){
			  
			  $("#list").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#list").html(JSON.stringify(data));
              }
 
    });
	}
	function fom(){
		$.ajax({
			  type:"POST",
              url:"<%=request.getContextPath() %>/a/tmsmenu/tmsMenuMain/form",
			  dataType: 'json',
              data:"",
              success:function(data){
			  
			  $("#fom").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#fom").html(JSON.stringify(data));
              }
 
    });
	}
	function tmsPolicyssave(){
		$.ajax({
			  type:"POST",
              url:"<%=request.getContextPath() %>/a/tmsPolicys",
			  dataType: 'json',
		<!--data:{'name':'策略1'},-->
		data:{'name':'策略11','id':'a0d2d96bef0d4948be284215a113847a'},
              success:function(data){
			  
			  $("#tmsPolicyssave").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#tmsPolicyssave").html(JSON.stringify(data));
              }
 
    });
	}
	function tmsPolicysdel(){
		$.ajax({
			  type:"DELETE",
              url:"<%=request.getContextPath() %>/a/tmsPolicys/a0d2d96bef0d4948be284215a113847a",
			  dataType: 'json',
				data:"",
		
              success:function(data){
			  
			  $("#tmsPolicysdel").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#tmsPolicysdel").html(JSON.stringify(data));
              }
 
    });
	}
	
	function tmsPolicysList(){
		$.ajax({
			  type:"GET",
              url:"<%=request.getContextPath() %>/a/tmsPolicys",
			  dataType: 'json',
				data:{"pageNo":"1","pageSize":"10"},
		
              success:function(data){
			  
			  $("#tmsPolicysList").html(JSON.stringify(data));
              },
              error:function(data){
			  
			  $("#tmsPolicysList").html(JSON.stringify(data));
              }
 
    });
	}
	
</script>	
</html>