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
.modify_account {
	border-radius: 15%;
	margin-top: 50px;
	text-align: left;
	padding: 50px;
	background: #b3f3fd;
}

.modify_account .disabled {
	display: flex;
}

.modifyimg {
	margin-left: 30%;
	background: #3399FF;
	border-radius: 30%;
	text-align: center;
	height: 100%;
}

.modifyimg>img {
	width: 70%;
}

.modify_account h2 {
	margin-left: 100px;
}

.modify_account input {
	margin-left: 100px;
	width: 30%;
	height: 30px;
	border-style: ridge;
	font-size: 20px;
	color: black;
	border: 3px solid black;
	border-radius: 5px;
}

.modify_account form input[name=address] {
	width: 50%;
}

.modify_account form input[readonly] {
	width: 50%;
	border: 0px;
	background: lightgray;
}

.modify_account form>.disabled input {
	border: 0px;
	width: 100%;
}

.modify_account form>.submitBtn {
	text-align: center;
}

.modify_account form #postcodify_search_button {
	background: #3399FF;
	border: none;
	color: black;
	padding: 10px;
	margin-left: 10px;
	font-size: 16px;
	border-radius: 5px;
	opacity: 0.6;
	transition: 0.4s;
	display: inline-block;
	text-decoration: none;
	cursor: pointer;
}

.modify_account form #postcodify_search_button:hover {
	opacity: 1;
}

.modify_account form>.submitBtn>button {
	width: 150px;
	height: 55px;
	border: none;
	border-radius: 5px;
	background: #3399FF;
	margin: 30px;
	display: inline-block;
	cursor: pointer;
	outline: none;
	box-shadow: 0 7px #0066CC;
}

.modify_account form>.submitBtn>button:hover {
	background: #0066CC;
	box-shadow: 0 5px #3399FF;
}

.modify_account form>.submitBtn>button:active {
	background: #0066CC;
	box-shadow: 0 5px #3399FF;
	transform: translateY(5px);
}
</style>

</head>
<body>
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 개인 정보 수정</h2>
			<div class="modify_account">
				<form action="<%=request.getContextPath()%>/memberModify"
					method="post" onsubmit="return checkPwd();">
					<div class="disabled">
						<div>
							<h2>이름</h2>
							<input type="text" id="name" value=<%=loginUser.getUserName()%>
								disabled="disabled"><br> <br>
							<h2>성별</h2>
							<%
								String gender = "";
							if (loginUser.getGender().equals("M"))
								gender = "남자";
							else
								gender = "여자";
							%>
							<input type="text" id="gender" value="<%=gender%>"
								disabled="disabled"><br> <br>
							<h2>아이디</h2>
							<input type="text" id="email" value="<%=loginUser.getUserId()%>"
								disabled="disabled"><br> <br>
						</div>
						<div class="modifyimg">
							<img
								src="<%=request.getContextPath()%>/resources/images/footerlogo.png">
						</div>
					</div>
					<h2>전화번호</h2>
					<input type="tel" value="<%=loginUser.getPhone()%>" name="tel"
						required><br> <br>
					<h2>닉네임</h2>
					<input type="text" value="<%=loginUser.getNickName()%>"
						name="nickName" maxlength="13" required><br> <br>
					<h2>비밀번호 변경</h2>
					<input type="password" value="<%=loginUser.getUserPwd()%>"
						name="userPwd" id="newPwd" maxlength="15" required><br> <br>
					<h2>비밀번호 변경 확인</h2>
					<input type="password" maxlength="15"
						value="<%=loginUser.getUserPwd()%>" name="userPwd" id="newPwd2"
						required><br> <br>

					<%
						String[] addressArr;
					if (loginUser.getAddress() != null)
						addressArr = loginUser.getAddress().split(", ");
					else {
						addressArr = new String[3];
						addressArr[0] = "";
						addressArr[1] = "";
						addressArr[2] = "";
					}
					%>
					<h2>
						우편번호
						<button type="button" id="postcodify_search_button">주소 검색</button>
					</h2>
					<input type="text" name="address" class="postcodify_postcode5"
						value="<%=addressArr[0]%>" readonly><br> <br>
					<h2>도로명 주소</h2>
					<input type="text" name="address" class="postcodify_address"
						value="<%=addressArr[1]%>" readonly><br> <br>
					<h2>상세 주소</h2>
					<input type="text" name="address" class="postcodify_details"
						value="<%=addressArr[2]%>" required><br> <br>
					<div class="submitBtn">
						<button id="modify" type="submit">수정하기</button>
						<button id="cancle" type="button">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
<script>
	$(function() {
		$("#postcodify_search_button").postcodifyPopUp();
	});

	function checkPwd() {
		const newPwd = document.getElementById('newPwd');
		const newPwd2 = document.getElementById('newPwd2');

		if (newPwd.value == "" || newPwd2.value == "") {
			alert('비밀번호를 입력해주세요');
			return false;
		} else {
			if (newPwd.value != newPwd2.value) {
				alert('비밀번호가 다릅니다.');
				newPwd2.select();
				return false;
			}
			return true;
		}
	}
</script>
</html>