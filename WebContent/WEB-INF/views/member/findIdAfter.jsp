<%@page import="java.io.PrintWriter"%>
<%@page import="member.model.service.MemberService"%>
<%@page import="member.controller.FindIdServlet"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBCTemplate.* "%>
<%@page import="member.model.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 후</title>
<style>
.IdFindPage {
	background-color: rgba(254, 229, 207, 1);
	border: none;
	color: black;
	padding: 10px;
	margin-top: 50px;
	font-size: 16px;
	border-radius: 5px;
	transition: 0.4s;
	display: inline-block;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	String userName = request.getParameter("userName");
	String phone = request.getParameter("phone");

	// MemberService ms = new MemberService();
	// String userId = ms.findId(userName, phone);

	MemberDao md = new MemberDao();
	String userId = md.findId(userName, phone);
	%>
	<div class="header">
		<a href="${ contextPath }/home"> <img class="headerlogo"
			src="resources/images/jejulogo.png">
		</a>
	</div>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<div style="text-align: center; margin: 150px;">
		<form name="findId" method="post">

			<%
				if (userId != null) {
			%>

			<div>
				<div>
					<h4>입력하신 정보와 일치하는 아이디 입니다.</h4>
					<div style="font-size: 25px;"><%=userId%></div>
				</div>
			</div>
			<div>
				<input class="IdFindPage" type="button" id="btnlogin" value="로그인하기"
					onclick="location.href='${contextPath}/login'" /> <input
					class="IdFindPage" type="button" id="btnfindPwd" value="비밀번호 찾기"
					onclick="location.href='${contextPath}/findPwd'" />
			</div>

			<%
				} else {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert('입력하신 정보와 일치하는 아이디가 없습니다.')");
				pw.print("</script>");
				pw.close();
			}
			%>

		</form>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</body>
</html>