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
					<h1>게시물 통합 관리</h1>
				</div>
				<div class="member_list">
					<ul class="memberhead_ul">
						<li class="name">이름</li>
						<li class="statistics">통계</li>
					</ul>
					<ul class="member_ul"onclick="boardAllView();">
							<li class="name">게시판</li>
							<li class="count">${boardListCount}</li>
					</ul>
					<ul class="member_ul"onclick="noticeAllView();">
							<li class="name">공지사항</li>
							<li class="count">${noticeListCount}</li>
					</ul>
					<ul class="member_ul"onclick="storeAllView();">
							<li class="name">식당</li>
							<li class="count">${storeListCount}</li>
					</ul>
					<ul class="member_ul" onclick = "roomAllView();">
							<li class="name">숙소</li>
							<li class="count">${roomListCount}</li>
					</ul>															
				</div>
			</div>
			
		</div>
	</div>
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
		function boardAllView() {
		
			location.href='${contextPath}/admin/boardList';
			
		}
		function noticeAllView() {
		
			location.href='${contextPath}/admin/noticeAllList';
			
		}
		function storeAllView() {
		
			location.href='${contextPath}/admin/storeAllList';
			
		}
		function roomAllView() {
		
			location.href='${contextPath}/admin/roomAllList';
			
		}
	
		</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>