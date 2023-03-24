<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
<!-- 템플릿 적용 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/html5up-future-imperfect/assets/css/main.css">
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../header.jsp"></jsp:include>
	</div>
	<div id="wrapper">
		<div id="main">
			<h3>회원정보</h3>
			<article class="post">
				<table>
					<tr>
						<td>아이디</td>
						<td>${loginMember.memberId}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${loginMember.memberName}</td>
					</tr>
				</table>
				<a href="${pageContext.request.contextPath}/member/modifyMember">정보수정</a>
				<span>&nbsp;</span>
				<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
			</article>
		</div>
	</div>
</body>
</html>