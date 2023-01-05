<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let memberName = $('#memberName');
		let memberPw = $('#memberPw');
		
		memberId.focus();
		
		// 이름 유효성 검사
		memberName.blur(function(){
			if(memberName.val().length <= 0 || memberName.val().indexOf(' ') != -1){ // 아무것도 입력하지 않았거나 공백을 포함한다면			
				$('#nameMsg').text('이름을 정확히 입력해 주세요');
				memberName.focus();
			}else {
				$('#nameMsg').text('');
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
			}
		});
		
		// 폼 제출
		$('#modifyMemberBtn').click(function(){
			$('#modifyMemberForm').submit();
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
	<div id="wrapper">
		<div id="main">
			<h3>회원정보 수정</h3>
			<article class="post">
				<form method="post" action="${pageContext.request.contextPath}/member/modifyMember" id="modifyMemberForm">
					<table>
						<tr>
							<td>아이디</td>
							<td>${loginMember.memberId}</td>
						</tr>
						<tr>
							<td>이름</td>
							<td>
								<input type="text" name="changeMemberName" id="memberName">
								<div id="nameMsg"></div>
							</td>
						</tr>
						<tr>
							<td>비밀번호</td>
							<td>
								<input type="password" name="memberPw" id="memberPw">
								<div id="pwMsg"></div>
							</td>
						</tr>
					</table>
					<button type="button" id="modifyMemberBtn">수정</button>
				</form>
			</article>
		</div>
	</div>
</body>
</html>