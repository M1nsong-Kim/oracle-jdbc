<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
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
	<a href="${pageContext.request.contextPath}/member/modifyMember">회원정보수정</a>
	<a href="${pageContext.request.contextPath}/member/removeMember">회원탈퇴</a>
</body>
</html>