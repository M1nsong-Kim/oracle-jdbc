<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let memberId = $('#memberId');
		let memberPw = $('#memberPw');
		let memberName = $('#memberName');
		
		memberId.focus();
		
		// 아이디 유효성 검사
		memberId.blur(function(){
			if(memberId.val().length <= 0){				
				$('#idMsg').text('아이디를 입력해 주세요');
				memberId.focus();
			}else {
				$('#idMsg').text('');
				memberPw.focus();
			}
		});
		
		// 비밀번호 유효성 검사
		memberPw.blur(function(){
			if(memberPw.val().length <= 0){				
				$('#pwMsg').text('비밀번호를 입력해 주세요');
				memberPw.focus();
			}else {
				$('#pwMsg').text('');
				memberName.focus();
			}
		});
		
		// 이름 유효성 검사
		memberName.blur(function(){
			if(memberName.val().length <= 0){				
				$('#nameMsg').text('이름을 입력해 주세요');
				memberName.focus();
			}else {
				$('#nameMsg').text('');
			}
		});
		
		// 폼 제출
		$('#signupBtn').click(function(){
			$('#signupForm').submit();
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
   	<h3>회원가입</h3>
    <!-- request.getContextPath() ==EL==> ${pageContext.request.contextPath} -->
    <form method="post" action="${pageContext.request.contextPath}/member/addMember" id="signupForm">
        <table>
            <tr>
            	<td>아이디</td>
                <td>
                    <input type="text" name="memberId" id="memberId">
                    <div id="idMsg"></div>
                </td>
            </tr>
            <tr>
            	<td>비밀번호</td>
                <td>
                    <input type="password" name="memberPw" id="memberPw">
                    <div id="pwMsg"></div>
                </td>
            </tr>
            <tr>
            	<td>이름</td>
                <td>
                    <input type="text" name="memberName" id="memberName">
                    <div id="nameMsg"></div>
                </td>
            </tr>
        </table>
        <button type="button" id="signupBtn">회원가입</button>
    </form>
</body>
</html>