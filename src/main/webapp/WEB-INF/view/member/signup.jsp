<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 템플릿 적용 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/html5up-future-imperfect/assets/css/main.css">
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let memberId = $('#memberId');
		let memberPw = $('#memberPw');
		let memberPwCk = $('#memberPwCk');
		let memberName = $('#memberName');
		
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
				memberPwCk.focus();
			}
		});
		
		// 비밀번호 일치 검사
		memberPwCk.blur(function(){
			if(memberPw.val() != memberPwCk.val()){
				$('#pwCkMsg').text('비밀번호가 일치하지 않습니다');
				memberPwCk.focus();
			}else {
				$('#pwCkMsg').text('');
				memberName.focus();
			}
		});
		
		// 이름 유효성 검사
		memberName.blur(function(){
			if(memberName.val().length <= 0 || memberName.val().indexOf(' ') != -1){				
				$('#nameMsg').text('이름을 정확히 입력해 주세요');
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
		<jsp:include page="../header.jsp"></jsp:include>
	</div>
	<div id="wrapper">
		<div id="main">
		   	<h3>회원가입</h3>
		   	<article class="post">
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
			            	<td>비밀번호 확인</td>
			                <td>
			                    <input type="password" name="memberPwCk" id="memberPwCk">
			                    <div id="pwCkMsg"></div>
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
			        <div class="text-center">
				        <button type="button" id="signupBtn">회원가입</button>
			        </div>
			    </form>
		   	</article>
		</div>
	</div>
</body>
</html>