<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%!
	String contextPath;
%>
<%
	contextPath = request.getContextPath();
%>
<!-- 이걸 컴포넌트 개념으로 생각하면 될 듯 -->
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판</title>
	<!-- Compiled and minified CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
	<!-- Compiled and minified JavaScript -->
	<!--  Materialize-Icon -->          
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	
</head>
<body>
	<nav class="nav-extended">
		<div class="nav-wrapper">
			<a href="#" class="brand-logo">게시판</a>
			<a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li><a href="<%= contextPath %>">처음으로</a></li>
				<li><a href="/board/list">목록으로</a></li>
			</ul>
			<ul class="side-nav" id="mobile-demo">
				<li><a href="<%= contextPath %>">처음으로</a></li>
				<li><a href="/board/list">목록으로</a></li>
			</ul>
		</div>
	</nav>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<script src="<%= contextPath %>/resources/js/service.js"></script>
</body>
</html>