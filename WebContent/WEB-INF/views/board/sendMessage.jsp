<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지보내기</title>
<style>
   	.popup-head {
   		background: #ffbf84;
   		display: flex;
   		justify-content: space-between;
   	}
   	.popup-head img {
   		background: none;
   		padding: 5px;
   	}
   	.popup-body {
   		border-color: rgba(255, 191, 132, 1); 	
   		display: flex;
   		flex-direction: column;
   	}
   	.popup-body>p {
   		margin: 0;
   		background-color: #fff;
   		font-size: 15px;
   	}
   	.msgTitle-wrapper {
   		background: #fff;
   		padding-bottom: 5px;
   	}
   	.msgTitle-wrapper input[type=text] {
   	 	width: 80%;
   	}
   	.popup-body>textarea {
   		resize: none;
   	}
   	.popup-body>button {
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border: 2px solid #ffbf84;
		cursor: pointer;
   	}
   	.msgTitle {
   		border: none;
   	}
</style>
</head>
<body>
	<div class="popup-wrap">
		<div class="popup">
			<div class="popup-head">
				<img src="<%= request.getContextPath() %>/resources/images/board/chatting.png" width="30px;" id="close">
				<img src="<%= request.getContextPath() %>/resources/images/board/close.png" width="30px;" id="close" onclick="popupClose()">				
			</div>
			<div class="popup-body">
				<p>보내는 사람 : ${ loginUser.nickName }</p><input type="hidden" value="${ loginUser.userNo }" class="sender">
				<p>받는 사람 : ${ board.bWriter }</p><input type="hidden" value="${ board.userNo }" class="receiver">
				<div class="msgTitle-wrapper">
					<span>제목 : </span><input type="text" class="msgTitle" placeholder="제목을 입력하세요" required>
				</div>
				<textarea rows="15" cols="50" class="msgContent"></textarea>				
				<button type="button" onclick="sendMsg()">쪽지보내기</button>
			</div>
		</div>
	</div>

	<script>
		function popupClose() {
			document.querySelector(".popup-wrap").style.display = 'none';
		}
		function sendMsg() {
			$.ajax({
				url : "${ contextPath }/board/insertMsg",
				type: "post",
				data : { sender : $(".sender").val(), 
						 receiver : $(".receiver").val(),
						 msgTitle : $(".msgTitle").val(),
						 msg : $(".msgContent").val() },
				dataType : "json",
				success : function(result) {
					if(result > 0) {
						alert('쪽지가 전송되었습니다.');
						$(".msgTitle").val('');
						$(".msgContent").val('');
						$(".popup-wrap").attr('style', 'display:none;');
					} else {
						alert('쪽지 전송이 실패했습니다.');
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
	</script>
</body>
</html>