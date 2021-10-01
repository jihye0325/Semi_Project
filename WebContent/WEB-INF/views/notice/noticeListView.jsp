<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
	
	.notice_title {
	    margin-bottom: 100px;
	    text-align: center;
	}
	
	article {
		width: 800px;
		margin : 0 auto;
	}
	
	.notice_title h1 {
		font-size: 32px;
	}
		
	.search form {
		display: flex;
		justify-content: flex-end;
		align-items: center;
	}
	
	.search input[type=search] {
		height: 24px;
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
	.notice_list {
		margin-top: 15px;
	}
	.notice_header {
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
	.notice_list>ul {
	    border-bottom: 1px solid #f3f5f7;
		border-radius: 10px;
	    justify-content: space-around;
	    text-align: center;
	    line-height: 40px;
	    padding-left: 15px;
	}
	
	.notice_list .id {
		width: 60px;
	}
	
	.notice_list .title {
		width: 400px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	
	.notice_list .writer {
		width: 100px;
	}
	
	.notice_list .count {
		width: 100px;
	}
	
	.notice_list .date {
		width: 100px;
	}
	
	.onmouseover {
		background: rgb(255, 236, 222);
		cursor : pointer;
	}
	
	.notice_ul {
		list-style: none;
		display: flex;
		margin: 0;
	}
	.notice_paging {
		height: 50px;
		line-height: 50px;
		display: flex;
		justify-content: center;
		list-style: none;
		width: 600px;
		margin: auto;
	}
	.notice_paging a {
		text-decoration: none;
		color: #282A35;
		width: 40px;
		display: block;
		text-align: center;
	}
	
	.notice_paging li {
		margin: 6px;
		font-size: 18px;
		text-align: center;
	}
	
	.pages {
		border-radius: 4px;
	}
	.notice_paging a.current_page {
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
			<div class="notice_title">
				<h1>#공지사항</h1>			
			</div>
			<div class="search">
			<form method="get" action="${ contextPath }/notice/list">
				<input type="search" placeholder="키워드 입력" name="searchValue" value="${ param.searchValue }" required>
				<input type="submit" value="검색">
			</form>
			</div>
			<div class="section">
				<div class="notice_list">
					<ul class="notice_header">
						<li class="id">글번호</li>
						<li class="title">제목</li>
						<li class="writer">작성자</li>
						<li class="count">조회수</li>
						<li class="date">작성일</li>
					</ul>
					<c:forEach var="n" items="${ noticeList }">
					<ul class="notice_ul" onclick="detailView(${ n.nno })">
						<li class="id">${ n.nno }</li>
						<li class="title">${ n.nTitle }</li>
						<li class="writer">${ n.nickname }</li>
						<li class="count">${ n.ncount }</li>
						<li class="date">${ n.createDate }</li>
					</ul>
					</c:forEach>
				</div>			
			</div>
			
			<!-- 페이징 -->
			<ul class="notice_paging">
			<c:if test="${ !empty param.searchValue }">
				<c:set var="searchParam" value="&searchValue=${ param.searchValue }"/>
			</c:if>
			
			<!-- 첫 페이지로 -->
			<li><a href="${ contextPath }/notice/list?page=1${ searchParam }">&lt;&lt;</a></li>
			
			<!-- 이전 페이지로  -->
			<li>
				<c:choose>
				<c:when test="${ npi.page > 1 }">
					<a href="${ contextPath }/notice/list?page=${ npi.page - 1 }${ searchParam }">&lt;</a>
				</c:when>
				<c:otherwise>
					<a href="#">&lt;</a>
				</c:otherwise>
				</c:choose>
			</li>
			
			<!-- 하단 페이지 목록 -->
			<c:forEach var="p" begin="${ npi.startPage }" end="${ npi.endPage }">
			<li>
				<c:choose>
				<c:when test="${ p eq npi.page }">
					<a href="#" class="current_page">${ p }</a>
				</c:when>
				<c:otherwise>
					<a href="${ contextPath }/notice/list?page=${ p }${ searchParam }" class="pages">${ p }</a>
				</c:otherwise>
				</c:choose>
			</li>
			</c:forEach>
			
			<!-- 다음 페이지로 -->
			<li>
				<c:choose>
				<c:when test="${ npi.page < pi.maxPage }">
					<a href="${ contextPath }/notice/list?page=${ npi.page + 1 }${ searchParam }">&gt;</a>
				</c:when>
				<c:otherwise>
					<a href="#">&gt;</a>
				</c:otherwise>
				</c:choose>
			</li>
			
			<!-- 마지막 페이지로 -->
			<li><a href="${ contextPath }/notice/list?page="${ npi.maxPage }${ searchParam }">&gt;&gt;</a></li>
			</ul>
			
			<!-- admin의 경우에만 보이도록 -->
			<c:if test="${ loginUser.authority == 3 }">
			<div class="btn_area">
				<input type="button" value="작성하기" onclick="location.href='${ contextPath }/notice/insert'">
			</div>
			</c:if>
		</article>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	<script>
		const noticeList = document.querySelector(".notice_list");
		
		noticeList.addEventListener('mouseover', function(){
			if(event.target.classList.contains('notice_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('notice_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		noticeList.addEventListener('mouseout', function(){
			if(event.target.classList.contains('notice_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('notice_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
		
		function detailView(nno) {
			location.href = '${ contextPath }/notice/detail?nno=' + nno;
		}
	</script>
</body>
</html>