<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
html {
	background-color: rgba(254, 229, 207, 1);
}

.outer {
	width: 90%;
	min-width: 450px;
	margin: auto;
}

.outer h1 {
	text-align: center;
}

#updatePwdForm {
	width: 300px;
	margin: auto;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	background-color: rgba(254, 229, 207, 1);
}

.input_area input {
	width: 270px;
	height: 30px;
	border: 0px;
}

.btnArea {
	text-align: center;
	padding: 30px;
}

.msgtag {
	font-size: 16px;
	font-weight: bolder;
	border: none;
	background-color: rgba(254, 229, 207, 1);
	border: none;
	width: 15%;
	margin: 5px;
}

.msgheader {
	width: 100%;
	background-color: rgba(254, 229, 207, 1);
	margin: 5px;
}

.msgcontent {
	width: 100%;
	background-color: rgba(254, 229, 207, 1);
	text-align: center;
	margin-top: 20px;
}

.msgcontent>textarea {
	width: 80%;
	background-color: rgba(254, 229, 207, 1);
	resize: none;
	border: ridge;
	font-size: 14px;
}

button:hover {
	cursor: pointer
}

button {
	width: 100px;
	height: 35px;
	display: inline-block;
	border-radius: 4px;
	background-color: #FFBF84;
	border: none;
	color: black;
	text-align: center;
	font-size: 15px;
	padding: 5px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

input {
	border: none;
}
</style>
<title>쪽지보기</title>
</head>
<body>
	<div class="outer">
		<div style="display: flex;">
			<input type="text" class="msgtag" value="제목" disabled="disabled">
			<input type="text" id="msgTitle" class="msgheader" value="${ title }">
		</div>
		<div style="display: flex;">
			<input type="text" class="msgtag" value="수신인" disabled="disabled">
			<input type="text" class="msgheader" value="${sender}">
		</div>
		<div class="msgcontent">
			<textarea rows="18" cols="30" maxlength="500" disabled="disabled">${ content }</textarea>
		</div>
		<div class="btnArea">
			<form name="msgdelete" action="" method="get">
				<button class="msg_delete_btn" onclick="deleteMsg()">삭제</button>
				<input type="hidden" name="msgNo" value="${ msgNo }">
			</form>
		</div>
	</div>

	<script>
		function deleteMsg() {
			var left = (document.body.clientWidth / 2) - 250;
			left += window.screenLeft;
			var top = (screen.availHeight / 2) - 250;
			top += window.screenTop;

			// 삭제가 됐다 안됐다 함 (서버 문제?...)
			var m = document.msgdelete;
			window.open('about:blank', '_self',
					'width=500,height=500,left=' + left + ',top=' + top)
					.close();
			console.log(m)
			m.action = '${ contextPath }/mypage/sendmsgdelete';
			m.methos = 'get';
			m.target = 'delete'
			m.submit();

		}
	</script>
</body>
</html>