<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="my.remind.board.vo.ContentVO" %>
<%!
	String contextPath;
	int pages = 1;
	int nowPage = 1;
	boolean existNext = false;
	boolean existPrev = false;
	
	ArrayList<ContentVO> contents = null;
	String pageNumberClassName = "page-number";
%>
<%
	contextPath = request.getContextPath();

	// 게시글 목록 가져오기
	if( request.getAttribute("contents") != null ){
		contents = (ArrayList<ContentVO>) request.getAttribute("contents");
	}
	// 게시글 목록 페이지수 가져오기
	if( request.getAttribute("pages") != null ){
		pages = (Integer) request.getAttribute("pages");
	}
	// 현재 페이지 번호 가져오기
	if( request.getParameter("page") != null ){
		nowPage = Integer.parseInt(request.getParameter("page"));
	}
	
	// 다음 페이지가 있는지 확인
	if( request.getAttribute("existNext") != null ){
		existNext = (Boolean) request.getAttribute("existNext");
	}
	// 이전 페이지가 있는지 확인
	if( request.getAttribute("existPrev") != null ){
		existPrev = (Boolean) request.getAttribute("existPrev");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시판</title>
</head>
<body>
	<jsp:include page="ContentHome.jsp"/>
	<table class="bordered">
		<thead>
			<tr>
				<td class="content-no">NO</td>
				<td class="content-title">Title</td>
				<td class="content-writer">Writer</td>
				<td class="content-date">Date</td>
			</tr>
		</thead>
		<tbody>
		<% if( contents != null ) { 
			for(int i = 0; i < contents.size(); i++) { 
				ContentVO content = contents.get(i);
			%>
			<tr class="content-item" content-id=<%= content.getcNo() %>>
				<td class="content-no" content-id="<%=content.getcNo()%>"><%=content.getcNo()%></td>
				<td class="content-title" content-id="<%=content.getcNo()%>"><%=content.getcTitle()%></td>
				<td class="content-writer" content-id="<%=content.getcNo()%>"><%=content.getcWriter()%></td>
				<td class="content-date" content-id="<%=content.getcNo()%>"><%=content.getcDate()%></td>
			</tr>
			<% } %>
		<% } %>
		</tbody>
	</table>
	<ul id="content-list-remote" class="pagination">
		<li class="waves-effect">
			<a id="btn-prev">
				<% if( existPrev ) { %>
				<i class="material-icons">chevron_left</i>
				<% } %>
			</a>
		</li>
		<% for(int pageNumber = 1; pageNumber <= pages; pageNumber++) { 
			System.out.println("now(" + nowPage + ")/pageNumber("+pageNumber+")"); %>
			<li class="weves-effect">
				<a class="page-number <%= pageNumber %><%= pageNumber == nowPage ? " active" : "" %>" 
					page-number="<%=pageNumber%>">
					<%=pageNumber%>
				</a>
			</li>
		<% } %>
		<li class="waves-effect">
			<a id="btn-next">
				<% if( existNext ) { %>
				<i class="material-icons">chevron_right</i>
				<% } %>
			</a>
		</li>
		<li>
			<a href="/board/write?page=<%= nowPage %>">글쓰기</a>
		</li>
	</ul>
	<script src="<%=contextPath%>/resources/js/ContentListConfig.js"></script>
</body>
</html>