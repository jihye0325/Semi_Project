<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 후</title>
</head>
<body>
	<div class="header">
        <a href="">
            <img class="headerlogo" src="resources/images/jejulogo.png">
        </a>
    </div>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
		 <div>
            <form class="findPwdAfter" action="<%= request.getContextPath() %>/findPwdAfter" method="post">
            <input type="hidden" name="userId" id="userId" value="<%= request.getParameter("userId") %>">
		 	<h4>인증번호</h4>
		 	<input type="text" name="authKey" id="authKey">
		 	<h4>새 비밀번호</h4>
		 	<input type="password" name="newPwd" id="newPwd">
		 	<h4>새 비밀번호 확인</h4>
		 	<input type="password" name="newPwd2" id="newPwd2">
			
			<div>
				<input type="submit" value="비밀번호 변경하기">
		 	</div>
		 	</form>
		 </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>