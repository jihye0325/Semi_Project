<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행후기</title>
<style>
	.outer {
		margin: auto;
	    display: flex;
	    justify-content : center;
	}
	
	.article_outer {
		width: 1100px;
		min-height: 700px;
	}
	
	.board_title {
	    margin-bottom: 100px;
	    text-align: center;
	}
	
	article {
		width: 800px;
		margin : 0 auto;
	}
	
	.board_title h1 {
		font-size: 32px;
	}
	
	.search form {
		display: flex;
		justify-content: flex-end;
		align-items: center;
	}
	select {
		height: 24px;
		border: 1px solid rgba(84, 84, 84, 1);
		border-radius: 5px;
		margin-right: 5px;
	}
	.search input[type=search] {
		height: 24px;
		width: 185px;
		border-style: none;
		border: 1px solid rgba(84, 84, 84, 1);
		border-radius: 5px;
	}
	
	form input[type=submit] {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border-radius: 5px;
		margin-left: 5px;
	}
	.board_list {
		margin-top: 15px;
	}
	.board_header {
	    list-style: none;
	    display: flex;
	    line-height: 30px;
	    padding: 0;
	    margin: 0;
	    border-radius: 20px;
	    font-size: 19px;
		font-weight: bold;
		background: rgb(254,229,207);
	}
	.board_list>ul {
	    border-bottom: 1px solid #f3f5f7;
		border-radius: 10px;
	    justify-content: space-around;
	    text-align: center;
	    line-height: 40px;
	    padding-left: 15px;
	}
	
	.board_list .id {
		width: 60px;
	}
	
	.board_list .category {
		width: 80px;
	}
	
	.board_list .title {
		width: 350px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	
	.board_list .writer {
		width: 80px;
	}
	
	.board_list .count {
		width: 60px;
	}
	
	.board_list .date {
		width: 100px;
	}
	
	.onmouseover {
		background: rgb(255, 236, 222);
		cursor : pointer;
	}
	
	.board_ul {
		list-style: none;
		display: flex;
		margin: 0;
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
	}
	
	.board_paging a.current_page {
		border-bottom: 2px solid orange;
		font-weight: bold;
		color: orange;
	}
	
	.btn_area {
		display: flex;
		justify-content: flex-end;
		margin-top: 15px;
	}
	
	.btn_area input[type=button] {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border-radius: 5px;
		margin-left: 5px;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="outer">
		<article class="article_outer">
			<div class="board_title">
				<h1>#자유롭게 여행 이야기를 공유하세요</h1>			
			</div>
			<div class="search">
				<form method="get" action="${ contextPath }/board/review/list">
					<select name="searchCondition" required>
						<option value="" disabled hidden <c:if test="${ param.searchCondition == null }">selected</c:if>>선택하세요.</option>
						<option value="dining" <c:if test="${ param.searchCondition == 'dining' }" >selected</c:if>>식당</option>
						<option value="room" <c:if test="${ param.searchCondition == 'room' }" >selected</c:if>>숙소</option>
						<option value="etc" <c:if test="${ param.searchCondition == 'etc' }" >selected</c:if>>기타</option>
					</select>
					<input type="search" name="searchValue" placeholder="키워드 입력" value="${ param.searchValue }">
					<input type="submit" value="검색">
				</form>
			</div>
			<div class="section">
				<div class="board_list">
					<ul class="board_header">
						<li class="id">글번호</li>
						<li class="category">분류</li>
						<li class="title">제목</li>
						<li class="writer">작성자</li>
						<li class="count">조회수</li>
						<li class="date">작성일</li>
					</ul>
					<c:forEach var="b" items="${ boardList }">
						<ul class="board_ul" onclick="detailView(${ b.bno })">
							<li class="id">${ b.bno }</li>
							<li class="category">
							<c:choose>
							<c:when test="${ b.bid == 20 }">식당</c:when>
							<c:when test="${ b.bid == 30 }">숙소</c:when>
							<c:when test="${ b.bid == 40 }">기타</c:when>							
							</c:choose>
							</li>
							<li class="title">${ b.bTitle }</li>
							<li class="writer">${ b.bWriter }</li>
							<li class="count">${ b.bCount }</li>
							<li class="date">${ b.createDate }</li>
						</ul>
					</c:forEach>
				</div>			
			</div>
			
			<!-- 페이징 -->
			<ul class="board_paging">
			
			<!-- 검색 결과 화면인 경우에만 값이 들어가있도록, 후기 게시판은 검색조건&검색키워드 존재 -->
			<c:choose>
				<c:when test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam"
					value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:when>
				<c:when test="${ !empty param.searchCondition || !empty param.searchValue }">
					<c:set var="searchParam" value="&searchValue=${ param.searchValue }"/>
				</c:when>
			</c:choose>
			
			<!-- 제일 처음 페이지로(<<) -->
			<li><a href="${ contextPath }/board/review/list?page=1${ searchParam }">&lt;&lt;</a></li>
			
			<!-- 이전 페이지로(<) -->
			<li>
				<c:choose>
				<c:when test="${ pi.page > 1 }">
					<a href="${ contextPath }/board/review/list?page=${ pi.page - 1 }${ searchParam }">&lt;</a>
				</c:when>
				<c:otherwise>
					<a href="#">&lt;</a>
				</c:otherwise>
				</c:choose>
			</li>
			
			<!-- 페이지목록(최대10개) -->
			<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
			<li>
				<c:choose>
				<c:when test="${ p eq pi.page }">
					<a href="#" class="current_page">${ p }</a>
				</c:when>
				<c:otherwise>
					<a href="${ contextPath }/board/review/list?page=${ p }${ searchParam }" class="pages">${ p }</a>
				</c:otherwise>
				</c:choose>
			</li>
			</c:forEach>
			
			<!-- 다음 페이지로(>) -->
			<li>
				<c:choose>
				<c:when test="${ pi.page < pi.maxPage }">
					<a href="${ contextPath }/board/review/list?page=${ pi.page + 1}${ searchParam }">&gt;</a>
				</c:when>
				<c:otherwise>
					<a href="#">&gt;</a>
				</c:otherwise>
				</c:choose>
			</li>
			
			<!-- 제일 마지막 페이지로(>>) -->
			<li><a href="${ contextPath }/board/review/list?page="${ pi.maxPage }${ searchParam }">&gt;&gt;</a>
			</ul>
			
			<!-- loginUser의 경우에만 보이도록 -->
			<div class="btn_area">
				<input type="button" value="작성하기" onclick="location.href='${ contextPath }/board/review/insert'">
			</div>
		</article>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	 <script>
		const boardList = document.querySelector(".board_list");
		
		boardList.addEventListener('mouseover', function(){
 			if(event.target.classList.contains('board_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
			
		boardList.addEventListener('mouseout', function() {		
 			if(event.target.classList.contains('board_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
	</script>
	
	<!-- 로그인 여부에 따라 구분 필요 -->
	<script>
		function detailView(bno) {
			location.href = '${ contextPath }/board/review/detail?bno=' + bno;
		}
	</script>
</body>
</html>