<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>errorpage</title>
<style>
	#imageArea{
		width : 300px;
		margin : 100px auto;
		display : flex;
		justify-content : center;
		align-itmes : center;
	}
	#imageArea img{
		width : 100%;
	}
</style>
</head>
<body>
	<!-- 모든 페이지에 include할 munubar.jsp 생성 -->
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
	<div id="imageArea">
		<img id="errorImage" src="<%= request.getContextPath() %>/resources/images/error.png">
	</div>
	<h1 align="center"><%= message %></h1>
	
	<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer> 
</body>
</html>