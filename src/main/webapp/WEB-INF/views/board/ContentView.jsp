<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="my.remind.board.vo.ContentVO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%!
	String mode = "view";
	String prevPage = "1";
	DateFormat dateFormat = DateFormat.getDateTimeInstance();
	String date = dateFormat.format(new Date());
			
	String contextPath;
	ContentVO content = null;
%>
<%
	contextPath = request.getContextPath();

	// Mode 변경
	if( request.getAttribute("mode") != null ){
		mode = (String) request.getAttribute("mode");
	}

	// 게시글 가져오기
	if( request.getAttribute("content") != null ){
		content = (ContentVO) request.getAttribute("content");
	}
	
	if( request.getParameter("page") != null ){
		prevPage = request.getParameter("page");
	}
	
	System.out.println("[mode] " + mode);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Board Header -->
	<jsp:include page="ContentHome.jsp"/>
	<!-- contents -->
	<div class="<%= mode %>-mode row">
		<form id="content-form" method="POST">
			<input type="text" name="cNo" value="<%= content != null ? content.getcNo() : -1 %>" style="display:none">
			<div class="input-field col s12">
	         	<input 	id="cTitle" 
	         			class="content-title"
	         			type="text" 
	         			name="cTitle" 
	         			value="<%= content != null ? content.getcTitle() : ""  %>"
	         			placeholder="Title"
						<%= mode.equals("view") ? "readonly" : null %>>
	         	<label for="cTitle">제목</label>
			</div>
			<div class="input-field col s6">
				<input 	id="cWriter" 
						class="content-writer" 
						type="text" 
						name="cWriter"
						value="<%= content != null ? content.getcWriter() : "" %>"
						placeholder="Writer"
						<%= !mode.equals("write") ? "readonly" : null %>>	<!-- 작성자는 수정 못해야 하니까.... -->
				<label for="cWriter">작성자</label>
			</div>
			<div class="input-field col s6">
				<input 	id="cDate" 
						class="content-date" 
						type="text" 
						name="cDate" 
						value="<%= content != null ? content.getcDate() : date %>"
						placeholder="Date"
						readonly>
				<label for="cDate">작성일</label>
			</div>
			<div class="input-field col s12">
				<textarea 	id="cContent" 
							class="content-content materialize-textarea"
							name="cContent"
							<%= mode.equals("view") ? "readonly" : null %>
				><%= content != null ? content.getcContent() : "" %></textarea>
				<label for="cContent">내용</label>
			</div>
			<div class="col s12">
				<% if( mode.equals("view") ) { %>
				<a href="/board/list">목록으로</a>
				<a href="/board/update" >수정</a>
				<a href="#" onClick="doSubmit('delete')">삭제</a>
				<% } else { %>
				<a href="#" onClick="doSubmit('<%=mode%>')">저장</a>
				<a href="/board/list?page=<%= prevPage %>">목록으로</a>
				<% } %>
			</div>
		</form>
	</div>
	<script>
		function doSubmit( mode ){
			if( confirm("계속 진행하시겠습니까?") ){
				var cForm = document.getElementById("content-form");
				cForm.action = "/board/"+ mode + ".do";
				cForm.submit();	
			}
		}
	</script>
</body>
</html>