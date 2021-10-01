<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.mypage_wrapper {
	position: relative;
	max-width: 80%;
	margin: 100px auto;
	padding: 0 4%;
	min-height: 100%;
	display: flex;
}

.mypageMain {
	width: 80%;
	margin-left: 100px;
}

.sub-menu {
	width: 20%;
	width: 190px;
	list-style: none;
}

.sub_menu li {
	text-align: center;
	width: auto;
}

.sub-menu a {
	text-decoration: none;
	color: balck;
	padding: 10px;
	color: black;
	padding: 10px;
	display: inline-block;
	padding: 10px;
	font-weight: bolder;
	font-size: 20px;
	transition: 0.5s;
	cursor: pointer;
	border-radius: 20px;
}

.sub-menu a:hover {
	background: #FFBF84;
	font-size: 25px;
}

.sub-title {
	font-size: 1.375em;
	padding: 0 8px 8px;
	border-bottom: 2px black solid;
	font-weight: normal;
	width: 190px;
}
</style>
</head>
<body>
	<aside class="mypage_aside">
		<ul class="sub-menu">
			<li><a href="${ contextPath }/memberModify">개인 정보 수정</a></li>
			<li><a href="${ contextPath }/mypage/wishlist">위시리스트</a></li>
			<li><a href="${ contextPath }/mypage/myboard">게시글 관리</a></li>
			<li><a href="${ contextPath }/mypage/mybook">예약 확인</a></li>
			<li><a href="${ contextPath }/mypage/mymessage">쪽지 관리</a></li>

		</ul>
		<h3 class="sub-title"></h3>
		<ul class="sub-menu">
			<li><a href="${ contextPath }/mypage/myroom">숙소 관리</a></li>
			<li><a href="${ contextPath }/mypage/leaveMemberView">회원 탈퇴</a></li>
		</ul>
	</aside>
</body>
</html>