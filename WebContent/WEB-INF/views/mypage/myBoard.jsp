<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/banner.jsp"%>
<style>
.myBoard {
	list-style: none;
	padding: 0;
	margin: 0;
	min-width: 1000px;
}

.myBoard li {
	font-size: 18px;
}

.myBoard>ul {
	justify-content: space-around;
	display: flex;
	font-weight: bold;
	line-height: 30px;
	padding-left: 15px;
	list-style: none;
}

.myBoard ul:nth-of-type(2n-1) {
	background-color: rgba(254, 229, 207, 1);
	border-radius: 15px;
}

}
.search {
	display: flex;
	justify-content: flex-end;
	align-items: center;
}

.search input[type=text] {
	height: 22px;
	width: 185px;
	border-style: none;
	border: 1px solid rgba(84, 84, 84, 1);
	border-radius: 5px;
}

.search input[type=submit] {
	height: 26px;
	width: 80px;
	border-style: none;
	background-color: rgba(255, 191, 132, 1);
	border-radius: 5px;
	margin-left: 5px;
}

.myBoard .id {
	width: 60px;
}

.myBoard .category {
	width: 80px;
}

.myBoard .title {
	width: 350px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.myBoard .writer {
	width: 80px;
}

.myBoard .count {
	width: 60px;
}

.myBoard .date {
	width: 100px;
}

.board_ul {
	list-style: none;
	display: flex;
	margin: 0;
}

.board_ul li {
	font-weight: normal;
}

.board_paging {
	height: 50px;
	line-height: 50px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 600px;
	margin: auto;
}

.board_paging a {
	text-decoration: none;
	color: #282A35;
	width: 40px;
	display: block;
	text-align: center;
}

.board_paging li {
	margin: 6px;
	font-size: 18px;
	text-align: center;
}

.pages {
	border-radius: 4px;
	background-color: #ffffff;
}

.board_paging a.current_page {
	border-bottom: 2px solid orange;
	font-weight: bold;
	color: orange;
}

.btn_area {
	display: flex;
	justify-content: flex-end;
}

.btn_area input[type=button] {
	height: 26px;
	width: 80px;
	border-style: none;
	background-color: rgba(255, 191, 132, 1);
	border-radius: 5px;
	margin-left: 5px;
	border-radius: 5px;
}

.onmouseover {
	cursor: pointer;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 게시글 관리</h2>

			<div class="board_page">
				<div class="myBoard">
					<div class="search">
						<form action="${ contextPath }/mypage/myboard" method="get">
							<input type="text" placeholder="키워드 입력" name="searchValue"
								value="${ param.searchValue }"> <input type="submit"
								value="검색">
						</form>
					</div>
					<ul class="board_header">
						<li class="id">글번호</li>
						<li class="category">분류</li>
						<li class="title">제목</li>
						<li class="writer">작성자</li>
						<li class="count">조회수</li>
						<li class="date">작성일</li>
					</ul>
					<c:forEach var="b" items="${ boardList }">
						<ul class="board_ul" onclick="myBoardDetail(${ b.bno });">
							<li class="id">${ b.bno }</li>
							<li class="category"><c:choose>
									<c:when test="${ b.bid == 0 }"></c:when>
									<c:when test="${ b.bid == 10 }">동행구함</c:when>
									<c:when test="${ b.bid == 20 }">식당</c:when>
									<c:when test="${ b.bid == 30 }">숙소</c:when>
									<c:when test="${ b.bid == 40 }">기타</c:when>
									<c:when test="${ b.bid == 100 }">공지사항</c:when>
								</c:choose></li>
							<li class="title boardView">${ b.bTitle }</li>
							<li class="writer">${ b.bWriter }</li>
							<li class="count">${ b.bCount }</li>
							<li class="date">${ b.modifyDate }</li>
						</ul>
					</c:forEach>
				</div>
			</div>
			<ul class="board_paging">
				<c:if
					test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam"
						value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }" />
				</c:if>

				<!-- 맨 처음으로 -->
				<li><a
					href="${ contextPath }/mypage/myboard?page=1${ searchParam }">&lt;&lt;</a></li>

				<!-- 이전 페이지로 -->
				<li><c:choose>
						<c:when test="${ pi.page > 1 }">
							<a
								href="${ contextPath }/mypage/myboard?page=${ pi.page - 1}${ searchParam }">&lt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&lt;</a>
						</c:otherwise>
					</c:choose></li>

				<!-- 페이지 목록 -->
				<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
					<li><c:choose>
							<c:when test="${ p eq pi.page }">
								<a href="#" class="current_page">${ p }</a>
							</c:when>
							<c:otherwise>
								<a class="pages"
									href="${ contextPath }/mypage/myboard?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose></li>
				</c:forEach>

				<!-- 다음 데이지로 -->
				<li><c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
							<a
								href="${ contextPath }/mypage/myboard?page=${ pi.page + 1}${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose></li>

				<!-- 맨 끝으로 -->
				<li><a
					href="${ contextPath }/mypage/myboard?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
			</ul>
		</div>
	</div>
	<script>
		const messageList = document.querySelector(".myBoard");
		messageList.addEventListener('mouseover', function() {
			if (event.target.classList.contains('boardView'))
				event.target.classList.add('onmouseover');
			else if (event.target.parentNode.classList.contains('boardView'))
				event.target.parentNode.classList.add('onmouseover');
		});
		messageList.addEventListener('mouseout', function() {
			if (event.target.classList.contains('boardView'))
				event.target.classList.remove('onmouseover');
			else if (event.target.parentNode.classList.contains('boardView'))
				event.target.parentNode.classList.remove('onmouseover');
		});
		
		function myBoardDetail(bno) {
			location.href= '${ contextPath }/board/accompany/detail?bno=' + bno;
		}
	</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>
