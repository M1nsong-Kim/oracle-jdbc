<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let memberPw = $('#memberPw');
		
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
		$('#removeMemberBtn').click(function(){
			$('#removeMemberForm').submit();
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
			<article class="post">
				<form method="post" action="${pageContext.request.contextPath}/member/removeMember" id="removeMemberForm">
					<table>
						<tr>
							<td>비밀번호</td>
							<td>
								<input type="password" name="memberPw" id="memberPw">
								<div id="pwMsg"></div>
							</td>
						</tr>
					</table>
					<button type="button" id="removeMemberBtn">회원탈퇴</button>
				</form>
			</article>
		</div>
	</div>
</body>
</html>