<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<!-- 제이쿼리 CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		let boardTitle = $('#boardTitle');
		let boardContent = $('#boardContent');
		
		boardTitle.focus();
		
		// 제목 유효성 검사
		boardTitle.blur(function(){
			if(boardTitle.val().length <= 0){				
				$('#titleMsg').text('제목을 입력해 주세요');
				boardTitle.focus();
			}else {
				$('#titleMsg').text('');
				boardContent.focus();
			}
		});
		
		// 내용 유효성 검사
		boardContent.blur(function(){
			if(boardContent.val().length <= 0){				
				$('#contentMsg').text('내용을 입력해 주세요');
				boardContent.focus();
			}else {
				$('#contentMsg').text('');
			}
		});
		
		// 폼 제출
		$('#addBoardBtn').click(function(){
			$('#addBoardForm').submit();
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
	<h3>글쓰기</h3>
	<form method="post" action="${pageContext.request.contextPath}/board/addBoard" id="addBoardForm">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="boardTitle" id="boardTitle">
					<div id="titleMsg"></div>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="5" cols="5" name="boardContent" id="boardContent"></textarea>
					<div id="contentMsg"></div>
				</td>
			</tr>
		</table>
		<button type="button" id="addBoardBtn">작성</button>
	</form>
</body>
</html>