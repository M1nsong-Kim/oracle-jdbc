<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
   	<h3>회원가입</h3>
    <!-- request.getContextPath() ==EL==> ${pageContext.request.contextPath} -->
    <form method="post" action="${pageContext.request.contextPath}/member/addMember">
        <table>
            <tr>
            	<td>아이디</td>
                <td>
                    <input type="text" name="memberId">
                </td>
            </tr>
            <tr>
            	<td>비밀번호</td>
                <td>
                    <input type="password" name="memberPw">
                </td>
            </tr>
            <tr>
            	<td>이름</td>
                <td>
                    <input type="text" name="memberName">
                </td>
            </tr>
        </table>
        <button type="submit">회원가입</button>
    </form>
</body>
</html>