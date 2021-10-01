<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath()%>/resources/css/qna/qnaInsert.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>1:1 문의 작성페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="page_title">
            <h1>1:1 문의글 작성</h1>
        </div>
        <form method="post" name="form" action="${ contextPath }/qna/insert"
        enctype="multipart/form-data" onsubmit="return validate();">
        <div class="input_area">
            <div class="input">
                <div class="category_div">문의유형</div>
                <select name="category">
                    <option value="10">공통</option>
                    <option value="20">예약</option>
                    <option value="30">결제</option>
                    <option value="40">기타</option>
                </select>
            </div>
            <div class="input">
                <div class="title_div">제목</div>
                <input type="text" class="title" name="title" required>
            </div>
            <div class="input">
                <div class="file_div">첨부파일</div>
                <input type="file" class="file" name="file_img" accept="image/gif, image/jpeg, image/png">
            	<button type="button" class="file_delete" onclick="">파일 삭제</button>
            </div>
            <div class="checkbox">
            	<input type="checkbox" id="public" name="content_type" value="Y" checked>
                <label for="public">공개</label>
                <input type="checkbox" id="private" name="open_status" value="N" >
                <label for="private">비공개</label>
            </div>
        
            <textarea class="content" name="content" required></textarea>
            
            <div class="image_area"></div>
        </div>
        
        <div class="button">
            <button type="button" onclick="location.href='${ contextPath }/qna/list'">목록으로</button>
            <button type="submit">작성하기</button>
        </div>
        </form>
    </div>
    
	<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>
	
    <script> 
       $(function(){
           // 공개 비공개 체크 
           $('input[type="checkbox"]').click(function(){
            if($(this).prop('checked')){
                $('input[type="checkbox"]').prop('checked', false);
                $(this).prop('checked', true);
            }
           });
       })
    </script>
    <script>
   	// 입력 값 유효성 검사
   	function validate(){
   		var form = document.form;
   		if(form.title.value.length > 30){
   			alert("게시글 제목은 30자 이내로 입력 가능합니다.");
   			form.title.select();
   			return false;
   		}
   		
   		if(form.content.value.length > 1300){
   			alert("게시글 내용은 1300자 이내로 입력 가능합니다.");
   			form.content.select();
   			return false;
   		}
   	}
   	</script>
    <script src="${ contextPath }/resources/js/imagePreview.js"></script>
</body>
</html>