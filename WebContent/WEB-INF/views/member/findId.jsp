<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style>
.findArea {
	margin: 150px;
	text-align: center;
}


.findArea>form input[type=button] {
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
	<div class="header">
		<a href="${ contextPath }/home"> <img class="headerlogo"
			src="resources/images/jejulogo.png">
		</a>
	</div>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<div class="findArea">
		<form name="findId" action="<%=request.getContextPath()%>/findId"
			method="post" onsubmit="return validate();">
			<div class="findIdInput">
				<h4>이름</h4>
				<input type="text" name="userName" id="userName">
				<h4>전화번호</h4>
				<input type="text" name="phone" id="phone">
			</div>
			<div>


			 	<input type="button" name="enter" value="인증하기" onclick="id_search();">
		 	</div>
		 	</form>
		 </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
    
    <script>
    	function validate() {
			return true;
		}
    

    	function id_search() {
			var frm = document.findId;
			
			if(frm.userName.value < 1){
				alert('이름을 입력해주세요');
				return;
			}
			if(frm.phone.value < 1) {
				alert('전화번호를 입력해주세요');
				return;
			}
			
			frm.method = "post";
			frm.action = "<%=request.getContextPath()%>/findIdAfter";
			frm.submit();
		}
    </script>
</body>
</html>