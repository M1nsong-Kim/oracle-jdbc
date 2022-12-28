<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  태그 라이브러리 쓸 거면 붙여야 하는 부분 -->
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
</head>
<body>	
	<!-- 로그인 전: 회원가입, 로그인 -->
	<c:if test="${loginMember == null}">
		<a href="${pageContext.request.contextPath}/member/addMember">회원가입</a>
		<a href="${pageContext.request.contextPath}/member/login">로그인</a>
	</c:if>
	
	<!-- 로그인 후: 로그아웃, 회원정보, 게시판리스트 -->
	<c:if test="${loginMember != null}">
		<a href="${pageContext.request.contextPath}/member/memberOne">회원정보</a>
		<a href="${pageContext.request.contextPath}/board/boardList">게시판</a>
		<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
	</c:if>
</body>
</html>