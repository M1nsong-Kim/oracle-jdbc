<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  태그 라이브러리 쓸 거면 붙여야 하는 부분 -->
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
<!-- 템플릿 적용 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/html5up-future-imperfect/assets/css/main.css">
</head>
<body>	
	<!-- Header -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="./header.jsp"></jsp:include>
	</div>
	<!-- 메인 -->
	<div id="wrapper">
		<div id="main">
				<h3>블로그</h3>
			<article class="post">
				<p>블로그에 오신 것을 환영합니다!</p>
				<p>회원가입 및 로그인 후 게시판에 글 작성이 가능합니다.</p>
				<p>나만의 포스팅으로 게시판을 꾸며보세요!</p>
			</article>
		</div>
	</div>
</body>
</html>