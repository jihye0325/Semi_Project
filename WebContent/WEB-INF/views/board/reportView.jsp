<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고하기</title>
<style>
	.rePopup-head {
		background: #ffbf84;
		width : 500px;
		display: flex;
		justify-content: space-between;
	}
	.rePopup-head img {
		padding: 5px;
	}
	.rePopup-head div {
		display:flex;
		align-items: center;
	}
	.rePopup-body {
		display: flex;
		flex-direction: column;
		align-items: center;
		background: #fff;
	}
	.rePopup-body>p {
		margin: 0;
		padding: 5px;
		font-size: 15px;
	}
	.rePopup-body>p:nth-child(3) {
		margin: 0;
		padding: 5px;
		font-size: 12px;
		margin: 10px 0 5px 0;
	}
	.rePopup-body>form {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.insertReport {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border: 2px solid #ffbf84;
		border-radius: 5px;
		margin-bottom: 10px;
		cursor: pointer;
	}
</style>
</head>
<body>
	<div class="rePopup-wrap">
		<div class="rePopup">
			<div class="rePopup-head">
				<div>
					<img src="<%= request.getContextPath() %>/resources/images/board/warning.png" width="30px;" id="close">
					<span>신고하기</span>
				</div>
				<img src="<%= request.getContextPath() %>/resources/images/board/close.png" width="30px;" id="close" onclick="rePopupClose()">			
			</div>
			<div class="rePopup-body">
			<p>신고하면 관리자 확인 후 삭제 처리됩니다.</p>
			<p>신고하시겠습니까?</p>
			<p>사유 선택 : 여러 사유에 해당되는 경우, 대표적인 사유 1개를 선택해주세요</p>
				<form>
					<table class="selectReason">
						<tr>
							<td><input type="radio" id="reason1" name="reason" value="1" required></td>
							<td><label for="reson1">부적절한 홍보 게시글</label></td>
						</tr>
						<tr>
							<td><input type="radio" id="reason2" name="reason" value="2"></td>
							<td><label for="reson2">음란성 또는 청소년에게 부적합한 내용</label></td>
						</tr>
						<tr>
							<td><input type="radio" id="reason3" name="reason" value="3"></td>
							<td><label for="reson3">명예훼손/사생활 침해 및 저작권 침해 등</label></td>
						</tr>
						<tr>
							<td><input type="radio" id="reason4" name="reason" value="4"></td>
							<td><label for="reson4">불법촬영물등 신고</label></td>
						</tr>
						<tr>
							<td><input type="radio" id="reason5" name="reason" value="5"></td>
							<td><label for="reson5">기타</label></td>
						</tr>
					</table>
					<input type="button" onclick="insertReport()" value="신고하기" class="insertReport">	
				</form>
			</div>
		</div>
	</div>
	<script>
		function rePopupClose() {
			document.querySelector(".rePopup-wrap").style.display = 'none';
		}
		
		function insertReport() {
			$.ajax({
				url : "${ contextPath }/board/insertReport",
				type : "post",
				data : { bno : $("#bno").val(),
						 reportId : $("input[name=reason]:checked").val()},
				dataType : "json",
				success : function(result) {
					if(result > 0) {
						alert('신고되었습니다. 관리자 확인 후 삭제 처리됩니다.');
					} else {
						alert('이미 신고 처리되었습니다. 신고가 불가능합니다.');
					}
					$("input[value='신고하기']").attr('style', 'background-color: #4f4f4f; border-color: #4f4f4f; color: #fff');
					$("input[value='신고하기']").attr('disabled', true);
					$(".rePopup-wrap").attr('style', 'display:none;');
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
	</script>
</body>
</html>