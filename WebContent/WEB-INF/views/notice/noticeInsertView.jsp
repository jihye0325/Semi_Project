<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="https://code.jquery.com/jquery-3.2.0.min.js" integrity="sha256-JAW99MJVpJBGcbzEuXk4Az05s/XyDdBomFqNlM3ic+I=" crossorigin="anonymous"></script>
<script src="${ contextPath }/resources/js/summernote/summernote-lite.js"></script>
<script src="${ contextPath }/resources/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${ contextPath }/resources/css/summernote/summernote-lite.css">
<style>
	.outer {
		margin: auto;
	    display: flex;
	    justify-content : center;
	}
	
	.article_outer {
		width: 1100px;
	    margin: 0 200px 0 100px;
	}
	
	.board_title {
	    margin-bottom: 100px;
	    text-align: center;
	}
	
	article {
		width: 800px;
		margin : 0 auto;
	}
	
	.notice_title h1 {
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
	.select_category {
		width: 65px;
		margin-right: 10px;
	}
	
	.titleArea input {
		width: 875px;
	    border: 1px solid rgba(84, 84, 84, 1);
	    border-radius: 5px;
	    display: flex;
	    align-items: center;
	}
	.textarea {
		margin-top: 15px;
		border-top: 2px solid #ffbf84;
		border-bottom: 2px solid #ffbf84;
		padding: 20px;
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
<%-- summernote jQuery 버전과 충돌 발생 	
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %> --%>
	<div class="outer">
		<article class="article_outer">
			<div class="notice_title">
				<h1>#공지사항</h1>			
			</div>
			<div id="insertNoticeArea">
 				<form id="insertNotice" action="${ contextPath }/notice/insert" method="post">
					<div class="titleArea">
						<h4>제목</h4>
						<input type="text" name="title" required>
					</div>
					<h4>내용</h4>
					<div class="textarea">
						<textarea id="summernote" name="editordata"></textarea>
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
	<script>
		$(document).ready(function(){
			// 써머노트 웹 에디터 로딩
			$('#summernote').summernote({
				height: 450,
				minHeigt: null,
				maxHeight: null,
				focus: false,
				lang: "ko-KR",
				placeholder: '최대 2000자까지 쓸 수 있습니다',
				callbacks: {
					onImageUpload: function(files){
						sendFile(files[0], this);
					}
				}
			});
		});
		
		function sendFile(file, editor) {
			var data = new FormData();
			data.append("file", file);
			// console.log(file);
			$.ajax({
				data: data,
				type: "post",
				url : "${ contextPath }/notice/summernoteImgFile",
				contentType: false,
				enctype: 'multipart/form-data',
				processData: false,
				success: function(data){
					console.log(data);
					console.log(editor);
					console.log("출력 test");
					$(editor).summernote('editor.insertImage', "${ contextPath }" + data);
				},
				error: function(e){
					console.log(e);
				}
			})
		}
		
		function check() {
			if(confirm('정말 취소하시겠습니까?')){
				return location.href='${ contextPath }/notice/list';
			}			
		}
		
		// 작성완료 버튼
		$("#confirm").click(function(){
			
		})
	</script>
</body>
</html>