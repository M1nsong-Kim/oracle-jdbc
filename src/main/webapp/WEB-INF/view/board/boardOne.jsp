<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  태그 라이브러리 -->
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardTitle}</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	<table>
		<tr>
			<td>제목</td>
			<td>${board.boardTitle}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.memberId}</td>
		</tr>
		<tr>
			<td colspan="2">${board.createdate}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.boardContent}</td>
		</tr>
	</table>
	<!-- 작성자라면 수정/삭제 -->
	 <c:if test="${loginMember.memberId == board.memberId}">
	 	<a href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
	 	<a href="${pageContext.request.contextPath}/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
	 </c:if>
</body>
</html>