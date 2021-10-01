<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<%
	if(request.getAttribute("result") != null){
		if(request.getAttribute("result").equals("success")){
%>
<script>
	alert('성공적으로 비밀번호를 변경하였습니다.');
	window.close();
</script>
<%
		} else {
%>
<script>
	alert('비밀번호 변경에 실패하였습니다. 비밀번호를 확인해주세요.');
</script>
<%
		}
	}
%>
</head>
<body>
	<div class="header">
        <a href="">
            <img class="headerlogo" src="resources/images/jejulogo.png">
        </a>
    </div>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<h1>비밀번수 수정</h1>	
	<form action="<%= request.getContextPath() %>/pwdModify" method="post" onsubmit="return checkPwd();">
		<h4>현재 비밀번호</h4>
		<input type="password" name="userPwd" id="userPwd" maxlength="15">
		<h4>변경할 비밀번호</h4>
		<input type="password" name="newPwd" id="newPwd" maxlength="15">
		<h4>변경할 비밀번호 확인</h4>
		<input type="password" name="newPwd2" id="newPwd2" maxlength="15">
		
		<div>
			<button id="updatePwdBtn">변경하기</button>
		</div>
	</form>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	
	<script>
		function checkPwd() {
			const userPwd = document.getElementById('userPwd');
			const newPwd = document.getElementById('newPwd');
			const newPwd2 = document.getElementById('newPwd2');
			
			if(userPwd.value == "" || newPwd.value == "" || newPwd2.value == ""){
				alert('비밀번호를 입력해주세요.');
				return false;
			}
			if(newPwd.value != newPwd2.value){
				alert('비밀번호가 다릅니다.');
				newPwd2.select();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>