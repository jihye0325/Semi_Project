<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member"%>
<%@ page import="wrapper.EncryptWrapper"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="member.mail.SendMail"%>
<%-- 아ㅏㅏㅏㅏ 잘 모르겠음
<%
	request.setCharacterEncoding("UTF-8");
	String code = null;
	Member m = new Member();
	String userId = null;
	
	if(request.getAttribute("code") != null) {
		code = (String)session.getAttribute("code");
	}
	
	String id = m.getUserId(id);
	boolean isRight = (new EncryptWrapper().getSha512(userId).equals(code)) ? true : false;
	if(isRight == true){
		m.setUserEmailCheck(id);
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('인증에 성공했습니다.');");
		pw.println("location.href='index.jsp'");
		pw.println("</script>");
		return;
	} else {
		PrintWriter pw = response.getWriter();
		pw.println("<script>");
		pw.println("alert('유효하지 않은 코드입니다.');");
		pw.println("location.href='index.jsp'");
		pw.println("</script>");
		return;
	}
	
%>
--%>
<!DOCTYPE html>
<html>
<head>
<style>
.findArea {
	margin: 150px;
	text-align: center;
}


.findArea>form input[type=submit] {
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
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
	<div class="header">
		<a href="${ contextPath }/home"> <img class="headerlogo"
			src="resources/images/jejulogo.png">
		</a>
	</div>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<div class="findArea">
		<form class="findPwd" action="<%= request.getContextPath() %>/findPwd"
			method="post" onsubmit="return validate();">
			<h4>이름</h4>
			<input type="text" name="userName" id="userName">
			<h4>아이디</h4>
			<input type="text" name="userId" id="userId">

			<div>

			 	<input type="submit" value="인증하기">
		 	</div>
		 	</form>
		 </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
    <script>
    	function validate() {
			return true;
		}
    </script>

</body>
</html>