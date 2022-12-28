<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	<h3>회원정보 수정</h3>
	<form method="post" action="${pageContext.request.contextPath}/member/modifyMember">
		<table>
			<tr>
				<td>아이디</td>
				<td>${loginMember.memberId}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" name="changeMemberName">
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="memberPw">
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>