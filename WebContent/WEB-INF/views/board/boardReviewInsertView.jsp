<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<title>여행후기</title>
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
	.select_category {
		width: 65px;
		height: 27px;
		margin-right: 10px;
	}
	.titleArea {
		display: flex;
		align-items: center;
	}
	.titleArea input {
		width: 730px;
		height: 25px;
	    border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	    display: flex;
	    align-items: center;
	    margin-right: 5px;
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
	.textarea {
		width: 1010px;
		height: 800px;
		resize: none;
		margin-top: 15px;
	}
	.tagArea {
		width: 780px;
		display: flex;
		justify-content: space-between;
	}
	.tagArea span {
		font-size: 20px;
		font-weight: bolder;
		margin-right: 5px;
	}
	.tagArea input[type=text] {
		width: 120px;
		margin-right: 20px;
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
		margin-left: 15px;
	}

	#confirm {
		background-color: rgba(255, 191, 132, 1);
	}
	#cancel {
		background-color: #FFFFFF;
		border: 1px solid rgba(255, 191, 132, 1);
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="outer">
		<article class="article_outer">
			<div class="board_title">
				<h1>#자유롭게 여행 이야기를 공유하세요</h1>			
			</div>
			<div id="insertBoardArea">
				<form id="insertBoard" action="${ contextPath }/board/review/insert" method="post"
				enctype="multipart/form-data">
					<div class="titleArea">
						<h4>제목</h4>
						<select class="select_category" name="btype">
							<option value="20">식당</option>
							<option value="30">숙소</option>
							<option value="40">기타</option>							
						</select>
						<input type="text" name="title"  id="title" required><span id="TitleLength">0</span>&nbsp;/ 50자
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
					<h4>내용</h4>
					<textarea class="textarea" rows="20" cols="100" name="content" required></textarea>
					<div class="lengthArea"><span id="ContentLength">0</span>&nbsp;/ 2000자</div>
					<div class="tagArea">
						<h4>해시태그</h4>
						<span>#</span><input type="text" name="tag">
						<span>#</span><input type="text" name="tag">
						<span>#</span><input type="text" name="tag">
						<span>#</span><input type="text" name="tag">
					</div>
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
		function check() {
			if(confirm('정말 취소하시겠습니까?')){
				return location.href='${ contextPath }/board/review/list';
			}			
		}
		
		document.querySelectorAll(".image_area").forEach(item => item.addEventListener('click', fileUpload));
		function fileUpload(){
			let index = Array.from(document.querySelectorAll(".image_area")).indexOf(this);
			document.querySelectorAll(".image_btn")[index].click();
		}
		
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