<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 태그 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<!-- 템플릿 적용 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/html5up-future-imperfect/assets/css/main.css">
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>

	<div id="wrapper">
		<div id="main">
			<a href="${pageContext.request.contextPath}/board/addBoard">글쓰기</a>
		    <article class="post">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성날짜</th>
					</tr>
					<c:forEach var="b" items="${boardList}">
						<tr>
							<td>${b.boardNo}</td>
							<td><a href="${pageContext.request.contextPath}/board/boardOne?boardNo=${b.boardNo}">${b.boardTitle}</a></td>
							<td>${b.createdate}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 페이징 -->
				<div>
					<c:if test="${currentPage > 10}">
						<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${currentPage-10}"><<</a>
					</c:if>
					<c:if test="${currentPage > 1}">
						<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${currentPage-1}"><span><</span></a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endRow}">
						<c:if test="${i == currentPage}">
							<strong>
								<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${i}">${i}</a>
							</strong>
						</c:if>
						<c:if test="${i != currentPage && i <= lastPage}">
								<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${i}">${i}</a>
						</c:if>
					</c:forEach>
					<c:if test="${currentPage < lastPage}">
						<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${currentPage+1}">></a>
					</c:if>
					<c:if test="${currentPage+10 < lastPage}">
						<a href="${pageContext.request.contextPath}/board/boardList?currentPage=${currentPage+10}">>></a>
					</c:if>
				</div>
			</article>
		</div>
	</div>
</body>
</html>