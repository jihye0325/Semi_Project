<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<style>

.loginArea {
	margin: 150px;
	text-align: center;
}

.loginBtn {
	display: flex;
	justify-content: center;
}

.loginBtn input {
	background-color: rgba(254, 229, 207, 1);
	border: none;
	color: black;
	padding: 10px;
	margin-left: 10px;
	font-size: 16px;
	border-radius: 5px;
	transition: 0.4s;
	display: inline-block;
	text-decoration: none;
	cursor: pointer;
}

.inputTag {
	font-size: 15px;
	font-weight: bolder;
}

.account input {
	width: 30%;
	height: 30px;
	border-style: ridge;
	font-size: 20px;
	color: black;
	border-radius: 5px;
}
</style>
</head>
<body>
	<div class="header">
		<a href="${ contextPath }/home"> <img class="headerlogo"
			src="${ contextPath }/resources/images/jejulogo.png">
		</a>
	</div>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<div class="wrapper loginArea">
		<div class="outer">
			<form class="loginArea" action="<%=request.getContextPath()%>/login"
				method="post" onsubmit="return validate();">
				<!-- <h4>아이디</h4> -->
				<div class="account">
					<span class="input_area"><input type="text" name="userId"
						id="userId" placeholder="아이디를 입력하세요."></span><br> <br>
					<!-- <h4>비밀번호</h4> -->
					<span class="input_area"><input type="password"
						name="userPwd" id="userPwd" placeholder="비밀번호를 입력하세요."></span>
				</div>
				<h5>
					<input type="checkbox" name="remember" id="remember"><label
						for="remember">아이디 기억하기</label>
				</h5>
				<div class="loginBtn">
					<span class="input_area"><input type="submit" value="로그인"></span><br>
					<!-- <a href="https://kauth.kakao.com/oauth/authorize?client_id=fcc7248cb3ab514a1d0f0933e8045d2a&redirect_uri=http://localhost:8800/UnI_Jeju&response_type=code"><img src="resources/images/kakao_login_medium_narrow.png"></a> -->
					<!-- localhost:8800/UnI_Jeju/login/kakaoLogin?cmd 테스트 해보기 -->

					<!-- <span><a id="kakaologin"></a></span><br> -->
					<input type="hidden" name="kEmail" id="kEmail"> <input
						type="button" value="아이디 / 비밀번호 찾기"
						onclick="location.href='${contextPath}/findId'"><br>
					<input type="button" value="회원가입"
						onclick="location.href='${contextPath}/memberJoin'">
				</div>
			</form>
		</div>
	</div>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<%-- include footer --%>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script>
		// 아이디, 비밀번호 있을 때만 제출
		function validate() {
			let userId = document.getElementById("userId");
			let userPwd = document.getElementById("userPwd");

			if (!userId.value.trim().length) {
				alert("아이디를 입력하세요");
				userId.focus();
				return false;
			}

			if (!userPwd.value.trim().length) {
				alert("비밀번호를 입력하세요");
				userPwd.focus();
				return false;
			}
			return true;
		}
	</script>

	<script
		src="<%=request.getContextPath()%>/resource/js/rememberUserId.js"></script>

	<!-- 카카오 로그인 / javascriptSDK 등록 -->
	<script>
		// 로그인.js
		// 카카오 로그인 이미지버튼 미사용 시 주석 해제 처리
		/*
		Kakao.init('859a0089e4ac2ed00028af64678cb9e9');
		Kakao.isInitialized();
		console.log("Kakao.isinitialized()", Kakao.isInitialized());
		
		function KakoLogin() {
			Kakao.Auth.authorize({
				redirectUri : 'http://localhost:8800/UnI_Jeju'
			})
		}
		
		Kakao.Auth.createLoginButton({
			container : '#kakaologin' ,
			success : function (response) {
		    	Kakao.API.request({
		    		url : '/v2/user/me' ,
		    		success : function (response) {
						var userID = response.id;
						var userEmail = response.kakao_account.email;
						var userNickName = response.properties.nickname;
						
						console.log(userID);
						console.log(userEmail);
						console.log(userNickName);
					} ,
					fail : function (e) {
						console.log(e);
					} ,
		    	});
			} ,
			fail : function (e) {
				cnosole.log()
			}
		})
		 */

		// 카카오 사용자 정보 가져오기
		Kakao.init('859a0089e4ac2ed00028af64678cb9e9');
		Kakao.isInitialized();
		console.log("Kakao.isinitialized()", Kakao.isInitialized());

		Kakao.Auth
				.createLoginButton({
					container : '#kakaologin',
					success : function(authObj) {
						Kakao.API
								.request({
									url : '/v2/user/me',
									success : function(res) {
										var kEmail = res.kakao_account.email;
										var kPhone = res.kakao_account.phone_number;
										var kGender = res.kakao_account.gender

										console.log(kEmail);
										$('#kEmail').val(kEmail);
										console.log(kPhone);
										console.log(kGender);

										// alert(JSON.stringify(res))
									},
									fail : function(error) {
										alert('login success, but failed to request user information: '
												+ JSON.stringify(error))
									},
								})
					},
					fail : function(err) {
						alert('failed to login: ' + JSON.stringify(err))
					},
				})
	</script>

</body>
</html>