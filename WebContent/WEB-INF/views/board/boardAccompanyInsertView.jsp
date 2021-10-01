<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행구함</title>
<style>
	.outer {
		margin: auto;
	    display: flex;
	    justify-content : center;
	}
	
	.article_outer {
		width: 1100px;
	    min-height: 700px;
	}
	
	.board_title {
	    margin-bottom: 100px;
	    text-align: center;
	}
	
	article {
		width: 800px;
		margin : 0 auto;
	}
	
	.board_title h1 {
		font-size: 32px;
	}
	
	div[class*=Area] {
		display: flex;
		margin-bottom: 15px;
	}
	
	h4 {
		line-height: 30px;
		width: 120px;
		background-color: rgba(255, 191, 132, 1);
		border-radius: 5px;
		margin: 0 15px 0 0;
		text-align: center;
	}
	
	.ul_header,
	.ul_list {
		list-style: none;
		display: flex;
		margin: 0 5px;
		padding: 0;
		justify-content:space-between;
	}
	
	.ul_header {
			border-bottom: 1px solid #dfdfdf;
	}
	
	.ul_header li,
	.ul_list li {
		font-size: 16px;
	}
	
	.attachName {
		width: 650px;
	}
	
	.attachSize,
	.attachStatus {
		width: 110px;
	}
/* 	.ul_list li {
		line-height: 100px;
	} */
	.titleArea {
		display: flex;
		align-items: center;
	}
	.titleArea input {
		width: 780px;
		height: 25px;
	    border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	    margin-right: 5px;
	}
	.checkList {
		width: 300px;
		margin-right: 15px;
		border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	}
	
 	.attachment_inner {
		width: 880px;
		margin-bottom: 15px;
		border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	}
	
	.preview_box {
		width: 880px;
		display: flex;
		justify-content: space-between;
	}
	
	.preview_box div {
		width : 250px;
		height : 250px;
		border: 1px solid #828282;
		display: flex;
		align-items: center;
		text-align: center;
		overflow: hidden;
		cursor: crosshair;
	}
	.image_area img {
		width: 100%;
		height: 100%;
		object-fit: contain;
	}
 	.files {
		display: none;
	}
	.options {
		width: 1010px;
		display: flex;
		justify-content: space-between;
	}
	
	.options div{
		height: 26px;
		display: flex;
		align-items: center;
	}
	
	#accompany_agreement {
		margin-left: 15px;
		padding: auto;
	}
	.inputDate input[type=time] {
		padding: 0;
		margin-left: 15px;
	}
	.placeNames_outer {
		width: 875px;
		display: flex;
		align-items: center;
	}
	.placeNames_outer span {
		width: 220px;
		padding: 3px 5px;
		border: 2px solid rgba(255, 191, 132, 1);
		margin-right: 5px;
		border-radius: 5px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.textarea {
		width: 1010px;
		height: 800px;
		resize: none;
		margin-top: 15px;
	}
	.btn_Area {
		margin-top: 30px;
		justify-content: center;
	}
	#confirm,
	#cancel {
		height: 30px;
		width: 100px;
		border-style: none;
		border-radius: 5px;
		margin-right: 15px;
	}

	#confirm {
		background-color: rgba(255, 191, 132, 1);
	}
	#cancel {
		background-color: #FFFFFF;
		border: 1px solid rgba(255, 191, 132, 1);
	}
	.popup-wrap {
		background-color: rgba(0, 0, 0, 0.5);
		display: flex;
		justify-content: center;
		align-items: center;
		position: fixed;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		padding: 15px;
		backdrop-filter: blur(4px);
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<%@ include file="/WEB-INF/views/board/kakaoMap.jsp" %>
	<div class="outer">
		<article class="article_outer">
			<div class="board_title">
				<h1>#함께 여행하는거 어때요?</h1>			
			</div>
			<div id="insertBoardArea">
				<form id="insertBoard" method="post" action="${ contextPath }/board/accompany/insert"
				enctype="multipart/form-data">
					<div class="titleArea">
						<h4>제목</h4>
						<input type="text" name="title" id="title" required><span id="TitleLength">0</span>&nbsp;/ 50자
					</div>
					<div class="attachmentListArea">
						<h4>첨부파일</h4>
						<div class="attachment_box">
							<div class="attachment_inner">
								<div class="attachmentList">
									<ul class="ul_header">
										<li class="attachName">첨부파일명</li>
										<li class="attachSize">용량</li>
										<li class="attachStatus">업로드 상태</li>
									</ul>
								</div>
								<!-- 업로드 파일에 따라 목록 표시 -->
								<div class="attachmentList 2"></div>
							</div>
								<div class="preview_box">
									<div class="image_area" id="preview"></div>
									<div class="image_area" id="preview"></div>
									<div class="image_area" id="preview"></div>
								</div>
							<div class="files">
								<input type="file" class="image_btn" name="contentImg1" accept="image/gif, image/jpeg, image/png">
								<input type="file" class="image_btn" name="contentImg2" accept="image/gif, image/jpeg, image/png">
								<input type="file" class="image_btn" name="contentImg3" accept="image/gif, image/jpeg, image/png">							
							</div>
						</div>
					</div>
					<div class="options">
						<div class="optionChkArea">
							<h4>선택</h4>
							<div class="checkList">
								<input type="checkbox" id="accompany_agreement" value="10" name="chk">
								<label for="accompany_agreement">동행허용</label>
							</div>
						</div>
						<!-- 동행허용 체크박스 여부에 따른 달력입력 활성화 -->
						<div class="dateChkArea">
							<h4 style="background-color:#4f4f4f;color:#fff">여행일자</h4>
							<div class="inputDate">
								<input type="datetime-local" value="여행일" name="date" id="bDate" disabled>
							</div>					
						</div>					
					</div>
					<div class="staticMapArea">
						<h4>장소</h4>
						<input type="hidden" id="positions" name="positions">
						<div class="placeNames_outer"></div>
					</div>
					<h4>내용</h4>
					<textarea class="textarea" rows="20" cols="100" name="content" required></textarea>
					<div class="lengthArea"><span id="ContentLength">0</span>&nbsp;/ 2000자</div>
					<div class="btn_Area">
						<input type="submit" value="작성 완료" id="confirm">
						<input type="button" onclick="check()" value="작성 취소" id="cancel">
					</div>
				</form>
			</div>
		</article>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
 	<script src="${ contextPath }/resources/js/board/imagePreview.js"></script>
	<script>
		$(document).ready(function(){
			// 오늘부터 날짜 입력 가능하도록
			var today = new Date();

			var dd = today.getDate() + 1;
			var mm = today.getMonth() + 1;
			var yyyy = today.getFullYear();
			
			if(dd < 10) {
				dd = '0' + dd
			}
			if(mm < 10) {
				mm = '0' + mm
			}
			
			today = yyyy + '-' + mm + '-' + dd + 'T00:00';
 			document.getElementById("bDate").setAttribute("min", today);
		});
		
		function check() {
			if(confirm('정말 취소하시겠습니까?')){
				return location.href='${ contextPath }/board/accompany/list';
			}			
		}
		
		document.querySelectorAll(".image_area").forEach(item => item.addEventListener('click', fileUpload));
		function fileUpload(){
			let index = Array.from(document.querySelectorAll(".image_area")).indexOf(this);
			document.querySelectorAll(".image_btn")[index].click();
		}
		
		// 선택한 마커 상호명&좌표 문자열로 저장
        function savePosition() {
        	var positions = '';
        	var count = 0;
        	if(selectedMarker.length > 0) {
        		for(let i = 0; i < selectedMarker.length; i++) {
        			if(count == 0){
		        		positions += placenames[i] + "|" + selectedMarker[i].getPosition().toString();
		        		count++;
        			} else {
		        		positions += "/" + placenames[i] + "|" + selectedMarker[i].getPosition().toString();    			        				        				
        			}
        			
        			// 선택한 장소 화면에 출력
        			var placeOuter = document.createElement('span');        			
        			placeOuter.id = 'place_outer';
        			placeOuter.textContent = placenames[i];
        			document.querySelector(".staticMapArea").lastElementChild.append(placeOuter);
        		}
        	}
        	document.querySelector("#positions").value = positions;
        	document.querySelector(".popup-wrap").style.display = "none";
        }
	</script>
	
	<script>
		// 제목 유효성 검사
		$("#title").keyup(function(){
			console.log("keyup");
			var title = $(this).val();
			$("#TitleLength").html(title.length);
			if(title.length > 50) {
				alert("최대 50자까지 입력 가능합니다.");
				$(this).val(title.substr(0, 50));
				$("#TitleLength").html(50);
			}
		});
		// 내용 유효성 검사
		$(".textarea").keyup(function(){
			var content = $(this).val();
			$("#ContentLength").html(content.length);
			if(content.length > 2000) {
				alert("최대 2000자까지 입력 가능합니다.");
				$(this).val(content.substr(0, 2000));
				$("#ContentLength").html(2000);
			}
		});
		// 동행허용 체크시 날짜&시간 입력 가능
		$("#accompany_agreement").on('click', function(){
			if($("#accompany_agreement").is(":checked") == true) {
				$(".dateChkArea>h4").css('background-color', 'rgba(255, 191, 132, 1)');
				$(".dateChkArea>h4").css('color', 'black');
				$("#bDate").attr("disabled", false);
			} else if($("#accompany_agreement").is(":checked") == false) {
				$(".dateChkArea>h4").css('background-color', '#4f4f4f');
				$(".dateChkArea>h4").css('color', '#fff');
				$("#bDate").attr("disabled", true);
			}
		});
		// 첨부파일 목록 표시
		$(".image_btn").change(function(){
			var html = '';
			var inputs = $(".image_btn");
			for(var i = 0; i < inputs.length; i++) {
				if(inputs[i].files[0]) {
					// console.log(inputs[i].files[0].name);
					html += '<ul class="ul_list"><li class="attachName">' + inputs[i].files[0].name
						  + '</li><li class="attachSize">' + (inputs[i].files[0].size / 1024 / 1024).toFixed(2)
						  + 'MB</li><li class="attachStatus">완료</li></ul>'
				}
			}
			$(".2").html(html);
		});
	</script>
</body>
</html>