<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	
    <h3>로그인</h3>
    <!-- request.getContextPath() ==EL==> ${pageContext.request.contextPath} -->
    <form method="post" action="${pageContext.request.contextPath}/member/login">
        <table>
            <tr>
                <td>
                    <input type="text" name="memberId" placeholder="아이디를 입력하세요">
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="memberPw" placeholder="비밀번호를 입력하세요">
                </td>
            </tr>
        </table>
        <button type="submit">로그인</button>
    </form>
</body>
</html>