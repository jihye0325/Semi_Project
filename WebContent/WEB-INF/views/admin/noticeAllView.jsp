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
	border-radius: 25px;
	background: rgba(254, 229, 207, 1);
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

.member_list .memberhead_ul {
	margin: 0;
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

.address {
	width: 80px; /* 너비는 변경될수 있습니다. */
	text-overflow: ellipsis; /* 위에 설정한 100px 보다 길면 말줄임표처럼 표시합니다. */
	white-space: nowrap; /* 줄바꿈을 하지 않습니다. */
	overflow: hidden; /* 내용이 길면 감춤니다 */
}

.name {
	width: 100px; /* 너비는 변경될수 있습니다. */
	text-overflow: ellipsis; /* 위에 설정한 100px 보다 길면 말줄임표처럼 표시합니다. */
	white-space: nowrap; /* 줄바꿈을 하지 않습니다. */
	overflow: hidden; /* 내용이 길면 감춤니다 */
}

.no {
	width: 30px;
}
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
					<h1>공지 관리</h1>
				</div>
				<div class="member_list">
					<ul class="member_ul">
						<li class="no">번호</li>
						<li class="name">제목</li>
						<li class="count">조회수</li>
						<li class="delete">삭제</li>
					</ul>
					<c:forEach var="n" items="${noticeList }">
						<ul class="member_ul">
							<li class="no">${n.n_no}</li>
							<li class="name">${n.n_title}</li>
							<li class="count">${n.n_hit }</li>


							<li><button type="button" onclick="deleteNotice()">삭제</button></li>
						</ul>
						<form name="noticeForm" method="post">
							<input type="hidden" name="n_no" value="${n.n_no}">
						</form>
					</c:forEach>

				</div>
				<ul class="board_paging">
					<li><a href="${contextPath}/admin/noticeAllList?page=1">&lt;&lt;</a></li>
					<li><c:choose>
							<c:when test="${pi.page>1 }">
								<a href="${contextPath}/admin/noticeAllList?page=${pi.page-1}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&lt;</a>
							</c:otherwise>
						</c:choose></li>
					<!-- 페이지 목록 -->
					<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
						<li><c:choose>
								<c:when test="${p eq pi.page }">
									<a href="#" class="current_page">${p}</a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath }/admin/noticeAllList?page=${p}">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 -->
					<li><c:choose>
							<c:when test="${pi.page<pi.maxPage }">
								<a href="${contextPath}/admin/noticeAllList?page=${pi.page+1}">&gt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&gt;</a>
							</c:otherwise>
						</c:choose></li>
					<li><a href="${contextPath}/admin/noticeAllList?page=${pi.maxPage}">&gt;&gt;</a></li>
				</ul>
			</div>
			<div class="search_area">
				<form method="get" action="${contextPath }/admin/noticeAllList">
					<select id="searchCondition" name="searchCondition">
						<option value="s_name" <c:if test ="${param.searchCondition == 'n_title' }">selected</c:if>>제목</option>
					</select>
					<span class="input_area"> <input type="search" name="searchValue" value="${param.searchValue }">
					</span>
					<button type="submit">검색하기</button>
				</form>
			</div>
		</div>
	</div>
	<script>
		function deleteNotice() {
			if (confirm('삭제하시겠습니까?')) {
				document.forms.noticeForm.action = "${contextPath}/admin/deleteNotice";
				document.forms.noticeForm.submit();
			}
		}
	</script>

</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>