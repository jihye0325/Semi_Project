<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 배너</title>
<style>
	.banner_container {
		position: relative;
		text-align: center;
	}
	#first img {
		width: 100%;
	}
	#second {
		width: 85%;
		position: absolute;
		left: 50%;
		top: 50%;
		transform: translate(-50%, -50%);
	}
</style>
<!-- 2021-09-16 msg alert 수행 추가 -->
<%
	if (session.getAttribute("msg") != null) {
%>
<script>
	alert('<%= session.getAttribute("msg")%>');
</script>
<%
	session.removeAttribute("msg");
	}
%>
</head>
<body>
<c:set value="${ pageContext.servletContext.contextPath }" var="contextPath" scope="application"/>
	<div class="banner_container">
		<div id="first">
			<img src="${ contextPath }/resources/images/common/banner.png">		
		</div>
		<div id="second">
			<img src="${ contextPath }/resources/images/common/jejulogo.png" id="logo">		
		</div>
	</div>
</body>
</html>