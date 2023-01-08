<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  태그 라이브러리 -->
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardTitle}</title>
</head>
<body>
	<!-- 메뉴 페이지 -->
	<div>
		<!-- .. : 현재 이 jsp파일이 위치한 곳에서 폴더 하나 밖으로 -->
		<jsp:include page="../home.jsp"></jsp:include>
	</div>
	<div id="wrapper">
		<div id="main">
		    <article class="post">
				<header>
					<div class="title">
						<h2>${board.boardTitle}</h2>
					</div>
					<div class="meta">
						<time class="published">${board.createdate}</time>
						<div class="author"><span class="name">${board.memberId}</span><img src="${pageContext.request.contextPath}/css/html5up-future-imperfect/images/blankProfile.png" alt="" /></div>
						<!-- 작성자라면 수정/삭제 -->
						 <c:if test="${loginMember.memberId == board.memberId}">
						 	<a href="${pageContext.request.contextPath}/board/modifyBoard?boardNo=${board.boardNo}">수정</a>
						 	<a href="${pageContext.request.contextPath}/board/removeBoard?boardNo=${board.boardNo}">삭제</a>
						 </c:if>
					</div>
				</header>
				<div>
					${board.boardContent}
				</div>
				<footer>
					<ul class="stats">
						<li><a href="#" class="icon solid fa-comment">128</a></li>
					</ul>
				</footer>
			</article>
		</div>
	</div>
</body>
</html>