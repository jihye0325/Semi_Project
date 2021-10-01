<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
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
	#insertBoardArea h1 {
		padding-left: 15px;
		border-left: 3px solid #000000;
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
	<!-- test용 -->
	.ul_list li {
		line-height: 100px;
	}
	
	.titleArea input {
		width: 875px;
	    border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	}
	
	.checkList {
		width: 300px;
		margin-right: 15px;
		border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	}
	
	.attachmentList {
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
		
	}
	.image_area {
		cursor: crosshair;
	}
	.image_area img {
		width: 100%;
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
				<h1>#함께 여행하는거 어때요?</h1>			
			</div>
			<div id="insertBoardArea">
				<form id="insertBoard" method="post" action="${ contextPath }/board/accompany/update" enctype="multipart/form-data">
				<input type="hidden" name="bno" value="${ board.bno }">
				<c:forEach items="${ board.imgList }" var="i">
				<input type="hidden" name="changeName" value="${ i.reName }">
				</c:forEach>
					<h1>함께해요 게시글 수정</h1>
					<div class="titleArea">
						<h4>제목</h4>
						<input type="text" name="title" value="${ board.bTitle }" required>
					</div>
					<div class="attachmentListArea">
						<h4>첨부파일</h4>
						<div class="attachment_box">
							<div class="attachmentList">
								<ul class="ul_header">
									<li class="attachName">첨부파일명</li>
									<li class="attachSize">용량</li>
									<li class="attachStatus">업로드 상태</li>
								</ul>
								<!-- 업로드 파일에 따라 목록 표시 -->
								<ul class="ul_list">
									<li class="attachName"></li>
									<li class="attachSize"></li>
									<li class="attachStatus"></li>
								</ul>
							</div>
							<div class="preview_box">
								<div class="image_area" id="preview">
									<c:if test="${ board.imgList.size() > 0 }">
									<img src="${ contextPath }${ board.imgList.get(0).route}${ board.imgList.get(0).reName }">
									</c:if>
								</div>
								<div class="image_area" id="preview">
									<c:if test="${ board.imgList.size() > 1 }">
									<img src="${ contextPath }${ board.imgList.get(0).route}${ board.imgList.get(1).reName }">
									</c:if>								
								</div>
								<div class="image_area" id="preview">
									<c:if test="${ board.imgList.size() > 2 }">
									<img src="${ contextPath }${ board.imgList.get(0).route}${ board.imgList.get(2).reName }">
									</c:if>																
								</div>
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
								<input type="checkbox" id="accompany_agreement" value="10" name="chk" <c:if test="${ board.bid == 10 }">checked</c:if> disabled="true">
								<label for="accompany_agreement">동행허용</label>
							</div>
							<span>※ 동행여부&일자는 수정이 불가합니다.</span>
						</div>				
						<div class="dateChkArea">
							<h4>여행일자</h4>
							<div class="inputDate">
								<span><fmt:formatDate value="${ board.bDate }" pattern="yyyy.MM.dd HH:mm"/></span>	
							</div>					
						</div>					
					</div>
					<h4>내용</h4>
					<textarea class="textarea" rows="20" cols="100" name="content" required>${ board.bContent }</textarea>
					<div class="btn_Area">
						<input type="submit" value="수정 완료" id="confirm">
						<input type="button" onclick="check()" value="수정 취소" id="cancel">
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
				return location.href='${ contextPath }/board/accompany/list';
			}			
		}
		
		document.querySelectorAll(".image_area").forEach(item => item.addEventListener('click', fileUpload));
		function fileUpload(){
			let index = Array.from(document.querySelectorAll(".image_area")).indexOf(this);
			document.querySelectorAll(".image_btn")[index].click();
		}
	</script>
</body>
</html>