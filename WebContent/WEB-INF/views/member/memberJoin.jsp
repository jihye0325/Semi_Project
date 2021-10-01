<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
.accountInfo {
	margin-left: 42%;
}

.accountInfo input {
	border-style: ridge;
	font-size: 20px;
	color: black;
	border-radius: 5px;
}

.accountInfo button {
	background-color: rgba(254, 229, 207, 1);
	border: none;
	color: black;
	padding: 10px;
	margin-left: 10px;
	font-size: 13px;
	border-radius: 5px;
	display: inline-block;
	text-decoration: none;
	cursor: pointer;
}

#joinBtn {
	background-color: rgba(254, 229, 207, 1);
	border: none;
	color: black;
	padding: 10px;
	margin-left: 10px;
	font-size: 16px;
	border-radius: 5px;
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
	<div class="outer">
		<div id="infoArea" class="infoArea">
			<form class="joinForm"
				action="<%=request.getContextPath()%>/memberJoin" method="post"
				onsubmit="return validate();">
				<div class="accountInfo">
					<h4>아이디</h4>
					<span class="input_area"><input type="text" maxlength="20"
						name="userId" required></span>
					<button id="idCheck" type="button">중복확인</button>

					<h4>비밀번호</h4>
					<span class="input_area"><input type="password"
						maxlength="15" name="userPwd" required></span>

					<h4>비밀번호 확인</h4>
					<span class="input_area"><input type="password"
						maxlength="15" name="userPwd2" required></span> <label
						for="pwdResult"></label>

					<h4>이름</h4>
					<span class="input_area"><input type="text" maxlength="5"
						name="userName" required></span>

					<h4>전화번호</h4>
					<span class="input_area"><input type="text" maxlength="11"
						name="phone" required></span>

					<h4>성별</h4>
					<input type="radio" id="male" name="gender" value="M"> <label
						for="male">남자</label> <input type="radio" id="female"
						name="gender" value="F"> <label for="female">여자</label>

					<h4>닉네임</h4>
					<span class="input_area"><input type="text" maxlength="20"
						name="nickName" required></span>
					<button id="nickNameCheck" type="button">중복확인</button>

					<h4>우편번호</h4>
					<span class="input_area"><input type="text" name="address"
						class="postcodify_postcode5" readonly></span>
					<button id="postcodify_search_button" type="button">검색</button>
					<h4>도로명주소</h4>
					<span class="input_area"><input type="text" name="address"
						class="postcodify_address" readonly></span>
					<h4>상세주소</h4>
					<span class="input_area"><input type="text" name="address"
						class="postcodify_details" required></span>

					<h4>약관 동의</h4>
					<%--
                <h4>모두 동의</h4>
                <h4>서비스이용 약관동의(필수)</h4>
                <h4>개인정보 수집 및 이용동의(필수)</h4>
                <h4>광고성 메일 수신 동의(선택)</h4>
                --%>

					<ul class="chkList">
						<li><span class="chkbox1"> <input type="checkbox"
								id="chkjoin1" name="chkjoin1" required> <label
								for="chkjoin1">서비스 이용약관 동의(필수)</label>
						</span></li>
						<li><span> <input type="checkbox" id="chkjoin2"
								name="chkjoin2" required> <label for="chkjoin2">개인정보
									수집 및 이용동의(필수)</label>
						</span></li>
						<li><span> <input type="checkbox" id="chkjoin3"
								name="chkjoin3"> <label for="chkjoin3">광고성 정보
									수신(선택)</label>
						</span></li>
					</ul>
				</div>
				<div class="btnArea" style="text-align: center;">
					<button id="joinBtn">가입하기</button>
				</div>
				<%-- include footer --%>

			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

	<script>
		$(function() {
			$("#postcodify_search_button").postcodifyPopUp();
		});
	</script>

	<script>
		function validate() {
			return true;
		}

		// 아이디 중복 체크
		$("#idCheck").click(function() {
			var userId = $("[name=userId]");
			var isUsable = false;
			console.log(userId.val());

			if (!userId || userId.val().length < 4) {
				alert('아이디는 최소 4자리 이상이어야 합니다.');
				userId.focus();

			} else {
				$.ajax({
					url : "${ contextPath }/idCheck",
					data : {
						userId : userId.val()
					},
					type : "post",
					dataType : "text",
					success : function(result) {
						console.log(result);
						if (result == "fail") {
							alert("사용할 수 없는 아이디입니다.");
							userId.focus();
						} else {
							if (confirm('사용 가능한 아이디입니다. 사용하시겠습니까?')) {
								userId.attr('readonly', true);
								isUsable = true;
							} else {
								userId.attr('readonly', false);
								userId.focus();
								isUsable = false;
							}
						}
						if (isUsable) {
							$("#joinBtn").removeAttr("disabled");
						} else {
							$("#joinBtn").attr("disabled");
						}
					},
					error : function(e) {
						console.log(e);
					}
				})
			}
		})

		// 닉네임 중복 체크
		$("#nickNameCheck").click(function() {
			var userId = $("[name=nickName]");
			var isUsable = false;

			if (!userId || userId.val().length < 2) {
				alert('닉네임은 최소 2자리 이상이어야 합니다.');
				userId.focus();
			} else {
				$.ajax({
					url : "${ contextPath }/nicknameCheck",
					data : {
						userId : userId.val()
					},
					type : "post",
					success : function(result) {
						console.log(result);
						if (result == "fail") {
							alert("사용할 수 없는 닉네임입니다.");
							userId.focus();
						} else {
							if (confirm('사용 가능한 닉네임입니다. 사용하시겠습니까?')) {
								userId.attr('readonly', true);
								isUsable = true;
							} else {
								userId.attr('readonly', false);
								userId.focus();
								isUsable = false;
							}
						}
						if (isUsable) {
							$("#joinBtn").removeAttr("disabled");
						} else {
							$("#joinBtn").attr("disabled");
						}
					},
					error : function(e) {
						console.log(e);
					}
				})
			}
		})
	</script>
</body>
</html>