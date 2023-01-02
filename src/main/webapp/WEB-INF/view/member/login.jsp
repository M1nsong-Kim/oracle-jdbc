<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let memberId = $('#memberId');
		let memberPw = $('#memberPw');
		
		memberId.focus();
		
		// 아이디 유효성 검사
		memberId.blur(function(){
			if(memberId.val().length <= 0 || memberId.val().indexOf(' ') != -1){ // 아무것도 입력하지 않았거나 공백을 포함한다면		
				$('#idMsg').text('아이디를 정확히 입력해 주세요');
				memberId.focus();
			}else {
				$('#idMsg').text('');
				memberPw.focus();
			}
		});
		
		// 비밀번호 유효성 검사
		memberPw.blur(function(){
			if(memberPw.val().length <= 0 || memberPw.val().indexOf(' ') != -1){				
				$('#pwMsg').text('비밀번호를 정확히 입력해 주세요');
				memberPw.focus();
			}else {
				$('#pwMsg').text('');
			}
		});
		
		// 폼 제출
		$('#loginBtn').click(function(){
			$('#loginForm').submit();
		});
	});
</script>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	
    <h3>로그인</h3>
    <!-- request.getContextPath() ==EL==> ${pageContext.request.contextPath} -->
    <form method="post" action="${pageContext.request.contextPath}/member/login" id="loginForm">
        <table>
            <tr>
                <td>
                    <input type="text" name="memberId" placeholder="아이디를 입력하세요" id="memberId">
                	<div id="idMsg"></div>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="password" name="memberPw" placeholder="비밀번호를 입력하세요" id="memberPw">
                	<div id="pwMsg"></div>
                </td>
            </tr>
        </table>
        <button type="button" id="loginBtn">로그인</button>
    </form>
</body>
</html>