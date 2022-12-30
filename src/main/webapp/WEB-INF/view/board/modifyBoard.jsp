<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	<form method="post" action="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="boardTitle" value="${board.boardTitle}">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea rows="50" cols="50" name="boardContent">${board.boardContent}</textarea>
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>