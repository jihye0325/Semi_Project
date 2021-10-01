<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
.outer {
	margin: auto;
	display: flex;
	justify-content: center;
}

.article_outer {
	width: 800px;
	margin: 0 200px 0 100px;
}

.board_title {
	margin-bottom: 20px;
	text-align: center;
}

article {
	width: 800px;
	margin: 0 auto;
}

.board_title h1 {
	font-size: 40px;
}

ul, li {
	margin: 0;
	padding: 0;
}

.notice_header {
	list-style: none;
	display: flex;
	line-height: 30px;
}

.notice_list ul:nth-of-type(2n-1) {
	background-color: rgba(254, 229, 207, 1);
	border-radius: 15px;
}

.member_list {
	margin: 20px 30px;
	min-height: 540px;
}

.member_list>ul {
	border-bottom: 1px solid #f3f5f7;
	height: 50px;
	line-height: 50px;
	display: flex;
	justify-content: space-around;
	list-style: none;
}

.member_list>ul.last {
	border: 0;
}

.member_list>ul>li {
	text-align: center;
}

.member_list .no {
	width: 100px;
}

.member_list .title {
	width: 520px;
	text-align: left;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.member_list .date {
	width: 100px;
}

.member_list .memberhead_ul {
	margin: 0;
	background: rgba(254, 229, 207, 1);
	border-radius: 15px;
	font-size: 20px;
	font-weight: bolder;
}

.onmouseover {
	background: #f3f5f7;
	cursor: pointer;
}

.search_area {
	text-align: center;
	padding: 30px;
}

.search_area select {
	width: 150px;
	height: 30px;
	border: 0px;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	margin-right: 20px;
	background: white;
}

.input_area input {
	width: 250px;
	height: 30px;
	border: 0px;
}

.input_area input:focus, .search_area select:focus {
	outline: none;
}

.search_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
}

.notice_list .title {
	width: 400px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.wrap {
	width: 900px;
}

.board_paging {
	height: 50px;
	line-height: 50px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 480px;
	margin: auto;
}

.board_paging a {
	text-decoration: none;
	color: #282A35;
	width: 40px;
	display: block;
	text-align: center;
}

.board_paging a.current_page {
	border-bottom: 2px solid #282A35;
	font-weight: bold;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>

	<div class="outer">
		<%@ include file="/WEB-INF/views/admin/aside.jsp"%>
		<div class="wrap">
			<div class="notice_area">
				<div class="board_title">
					<h1>신고 관리</h1>
				</div>
				<div class="member_list">
					<ul class="memberhead_ul">
						<li class="report_no">신고번호</li>
						<li class="user_name">신고자 아이디</li>
						<li class="board_title">게시물 제목</li>
						<li class="report_con">신고 사유 내용</li>
					</ul>
					<c:forEach var="r" items="${reportList }">
						<ul class="member_ul" onclick="detailView(${ r.report_no});">
							<li class="report_no">${ r.report_no }</li>
							<li class="id">${ r.id }</li>
							<li class="board_title">${ r.b_title }</li>
							<li class="report_con">${r.report_con }</li>
						</ul>
					</c:forEach>
				</div>
				<ul class="board_paging">
				<c:if test="${!empty param.searchCondition && !empty param.searchValue }">
					<c:set var = "searchParam"
					value="&searchCondition=${param.searchCondition }&searchValue=${param.searchValue}"/>
				</c:if>
					<li><a href="${contextPath}/admin/memberAllList?page=1${searchParam}">&lt;&lt;</a></li>
					<li><c:choose>
							<c:when test="${pi.page>1 }">
								<a href="${contextPath}/admin/memberAllList?page=${pi.page-1}${searchParam}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&lt;</a>
							</c:otherwise>
						</c:choose></li>
					<!-- 페이지 목록 -->
					<c:forEach var="p" begin="${pi.startPage }" end = "${pi.endPage }">
						<li><c:choose>
								<c:when test="${p eq pi.page }">
									<a href="#" class="current_page">${p}</a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath }/admin/memberAllList?page=${p}${searchParam}">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 -->
					<li><c:choose>
							<c:when test="${pi.page<pi.maxPage }">
								<a href="${contextPath}/admin/memberAllList?page=${pi.page+1}${searchParam}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&gt;</a>
							</c:otherwise>
						</c:choose></li>
					<li><a href="${contextPath}/admin/memberAllList?page=${pi.maxPage}${searchParam}">&gt;&gt;</a></li>
				</ul>
				
			</div>
			<div class="search_area">
				<form method="get" action="${contextPath }/admin/memberAllList">
					<select id="searchCondition" name="searchCondition">
						<option value="user_name" <c:if test ="${param.searchCondition == 'user_name' }">selected</c:if>>이름</option>
						<option value="id" <c:if test ="${param.searchCondition == 'id' }">selected</c:if>>아이디</option>
					</select>
					<span class="input_area">
					 <input type="search" name="searchValue" value="${param.searchValue }">
					</span>
					<button type="submit">검색하기</button>
				</form>
			</div>
		</div>
	</div>
	  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	  <div id="chart_div"></div>
	<script>
		const memberList = document.querySelector(".member_list");
		
		memberList.addEventListener('mouseover',function(){
			console.log('mouseover');
			console.log(event.target);
			
			if(event.target.classList.contains('member_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('member_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		memberList.addEventListener('mouseout',function(){
			console.log('mouseout');
			console.log(event.target);

			if(event.target.classList.contains('member_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('member_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
		//회원 상세정보 페이지
		function detailView(report_no) {
		
			location.href='${contextPath}/admin/reportDetail?report_no='+report_no;
			
		}
	
		</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>