<%@page import="member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Member loginUser = (Member) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
html {
	min-width: 1800px;
	/* width: 100%; */
}

.headerLogo {
	width: 150px;
}

.loginIcon {
	width: 50px;
}

.banner {
	width: 100%;
}

#header {
	height: 150px;
	display: flex;
	justify-content: space-between;
}

#header .nav {
	display: flex;
	margin-right: 20px;
	justify-content: space-between;
	align-items: center;
}

.header-menu {
	display: flex;
	width: 500px;
	padding: 5px 10px;
	text-align: center;
	justify-content: space-between;
	margin-right: 10px;
}
.header-menu>li {
	font-size: 18px;
	font-weight: bold;
	width: 120px;
	padding: 5px 0;
	border-radius: 5px;
}
#header ul,
#header>li {
	list-style: none;
	margin:0;
	padding: 0;
	line-height: 30px;
	border-radius: 5px;
}
#header>ul {
	margin-right: 15px;
}
li ul.submenu {
	display: none;
	position: absolute;
	top: 102px;
	z-index: 2;
	background-color: rgb(254,229,207);
	border-radius: 5px;
}
li ul.submenu li {
	font-size: 17px;
	line-height: 35px;
	padding: 5px 0;
	border-radius: 5px;
}
#header .nav a {
	color: BLACK;
	text-decoration: none;
}

.header-menu > li .submenu {
	width: 120px;
	text-align: center;
}
.loginIcon {
	cursor: pointer;
}
ul.header-menu>li:hover {
	background-color: #FFD3AB;
}
ul.header-menu>li:hover ul.submenu {
	display: block;
}
ul.header-menu>li ul.submenu>li:hover {
	background: #FFD3AB;
}
.profile-menu {
	text-align: center;
}
.profile-menu>li,
.profile-menu>li>ul {
	width: 120px;
	font-weight: bold;
}
ul.profile-menu>li:hover ul.submenu {
	display: block;
}
</style>

<link rel="icon" type="image/x-icon"
	href="<%=request.getContextPath()%>/resources/images/favicon.png">
</head>
<header id="header">
	<c:set value="${ pageContext.servletContext.contextPath }"
		var="contextPath" scope="application" />
	<a href="<%=request.getContextPath()%>"> <img class="headerLogo"
		src="<%=request.getContextPath()%>/resources/images/jejulogo.png"
		alt="logo">
	</a>

	<div class="nav">

	<ul class="header-menu">
		<li class="booking-menu"><a href="${pageContext.servletContext.contextPath}/booking/list">숙소예약</a></li>
		<li class="dining-menu"><a href="${pageContext.servletContext.contextPath}/store/list">식당</a></li>
		<li class="board-menu">게시판
			<ul class="board_s submenu">
				<li><a href="${ contextPath }/board/accompany/list">함께해요</a></li>
				<li><a href="${ contextPath }/board/review/list">여행후기</a></li>
				<li><a href="${ contextPath }/board/gallery/list">사진모아보기</a></li>
			</ul>
		</li>
		<li class="notice-menu"><a href="${ contextPath }/notice/list">공지사항</a></li>
		<%-- 2021-09-29 수정 
		<c:choose> 
		<c:when test="${ empty loginUser.userNo }">
		</c:when>
		<c:when test="${ loginUser.authority == 3 }">
		<li class="adminPage-menu"><a href="${ contextPath }/admin/memberManagement">관리자페이지</a></li>		
		</c:when>
		<c:otherwise>
		<li class="myPage-menu">마이페이지
			<ul class="myPage_s submenu">
				<li><a href="${ contextPath }/memberModify">개인정보수정</a></li>
				<li><a href="${ contextPath }/mypage/wishlist">위시리스트</a></li>
				<li><a href="${ contextPath }/mypage/myboard">게시글관리</a></li>
				<li><a href="${ contextPath }/mypage/mybook">예약관리</a></li>
				<li><a href="${ contextPath }/mypage/mymessage">쪽지관리</a></li>
			</ul>
		</li>		
		</c:otherwise>
		</c:choose> --%>
	</ul>
	<ul class="profile-menu">
		<li><img class="loginIcon" src="<%=request.getContextPath()%>/resources/images/loginIcon.png" alt="loginIcon" onclick="">
			<ul class="profile submenu">
			<c:choose>
			<c:when test="${ loginUser.authority == 3 }">
				<li><a href="${ contextPath }/admin/memberManagement">관리자페이지</a></li>
				<li><a href="${ contextPath }/logout">로그아웃</a></li>
			</c:when>
			<c:when test="${ !empty loginUser }">
				<li><a href="${ contextPath }/memberModify">마이페이지</a></li>
				<li><a href="${ contextPath }/logout">로그아웃</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="${ contextPath }/login">로그인</a></li>
				<li><a href="${ contextPath }/joinMember">회원가입</a></li>
			</c:otherwise>
			</c:choose>
			</ul>
		</li>
	</ul>
	

	</div>
</header>
</html>