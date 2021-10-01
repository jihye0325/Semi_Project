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
.ask_leave {
	margin-top: 100px;
	text-align: center;
}

.btnLeave {
	display: inline-block;
	border-radius: 4px;
	background-color: #FFBF84;
	border: none;
	color: black;
	text-align: center;
	font-size: 28px;
	padding: 10px;
	width: 180px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

.btnLeave span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.btnLeave span:after {
	content: '\00bb';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.btnLeave:hover span {
	padding-right: 25px;
}

.btnLeave:hover span:after {
	opacity: 1;
	right: 0;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 회원 탈퇴</h2>
			<div class="ask_leave">
				<h1>회원을 탈퇴하시겠습니까?</h1>
				<br>
				<button class="btnLeave" id="btn_leave"
					onclick="location.href='${ contextPath }/mypage/leaveMember'">
					<span>예 </span>
				</button>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>