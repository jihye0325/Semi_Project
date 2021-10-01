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
    <title>1:1 문의 수정페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="page_title">
            <h1>1:1 문의글 수정</h1>  
        </div>
        <form method="post" name="form" action="${ contextPath }/qna/update"
        enctype="multipart/form-data" onsubmit="return validate();">
        <input type="hidden" name="q_no" value="${ qna.q_no }">
        <input type="hidden" name="image_r_name" value="${ qna.q_image.image_r_name }">
        <div class="input_area">
            <div class="input">
                <div class="category_div">문의유형</div>
                <select name="category">
                    <option value="10" <c:if test="${ qna.q_cid == 10 }">selected</c:if>>공통</option>
                    <option value="20" <c:if test="${ qna.q_cid == 20 }">selected</c:if>>예약</option>
                    <option value="30" <c:if test="${ qna.q_cid == 30 }">selected</c:if>>결제</option>
                    <option value="40" <c:if test="${ qna.q_cid == 40 }">selected</c:if>>기타</option>
                </select>
            </div>
            <div class="input">
                <div class="title_div">제목</div>
                <input type="text" class="title" name="q_title" value="${ qna.q_title }" required>
            </div>
            <div class="input">
                <div class="file_div">파일 수정</div>
                <input type="file" class="file" name="file_img" accept="image/gif, image/jpeg, image/png">
                <!--  
                <button type="button" class="file_delete" onclick="">파일 삭제</button>
                -->
            </div>
            <div class="checkbox">
            	<input type="checkbox" id="public" name="open_status" value="Y" <c:if test="${ qna.open_status == 'Y' }">checked</c:if>>
                <label for="public">공개</label>
                <input type="checkbox" id="private" name="open_status" value="N"
                <c:if test="${ qna.open_status == 'N' }">checked</c:if>>
                <label for="private">비공개</label>
            </div>
        
            <textarea class="content" name="q_content">${ qna.q_content }</textarea>
            

            <div class="image_area">
            	<img class="photo" name="file_img" <c:if test="${ qna.q_image.image_name != null }">
            	src="${ contextPath }${ qna.q_image.route }${ qna.q_image.image_r_name }" alt=" 첨부파일이 없습니다."
            	</c:if>> 
            </div>
            
        </div>
        
        <div class="button">
            <button type="button" onclick="location.href='${ contextPath }/qna/list'">목록으로</button>
            <button type="submit">수정하기</button>
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
   		if(form.q_title.value.length > 30){
   			alert("게시글 제목은 30자 이내로 입력 가능합니다.");
   			form.q_title.select();
   			return false;
   		}
   		
   		if(form.q_content.value.length > 1300){
   			alert("게시글 내용은 1300자 이내로 입력 가능합니다.");
   			form.q_content.select();
   			return false;
   		}
   	}
   	</script>
    <!-- 첨부파일 프리뷰 -->
	<script src="${ contextPath }/resources/js/imagePreview.js"></script>
</body>
</html>