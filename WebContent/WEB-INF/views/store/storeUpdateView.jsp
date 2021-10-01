<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath()%>/resources/css/store/storeInsert.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <title>식당정보 수정 페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="page_title">
            <h1>식당정보 수정</h1>
        </div>
        <div class=input_image>
        	<img class="icon" src="<%=request.getContextPath()%>/resources/images/store/store.png">
		</div>
        <form method="post" name="form" action="${ contextPath }/store/update"
        enctype="multipart/form-data" onsubmit="return validate();">
        <input type="hidden" name="s_no" value="${ s.s_no }">
        <input type="hidden" name="image_r_name" value="${ s.s_image.image_r_name }">
        <div class="input_area">
            <div class="input">
                <div class="title_div area">상호명</div>
                <input type="text" class="title" name="title" required value="${ s.s_name }">
            </div>

            <div class="input">
                <div class="tel_div area">전화번호</div>
                <input type="tel" class="tel" name="tel" required value="${ s.s_tel }">
            </div>

            <div class="input">
                <div class="ad_div area">위치</div>
                <input type="text" class="address" name="address" required value="${ s.s_address }">
            </div>

            <div class="input">
                <div class="time_div area">영업시간</div>
                <textarea class="time" name="time" required>${ s.s_time }</textarea>
            </div>
            
            <div class="input">
                <div class="menu_div area">메뉴&가격</div>
                <textarea class="menu" name="menu" required>${ s.menu }</textarea>
            </div>

            <div class="input">
                <div class="file_div area">파일 수정</div>
                <input type="file" class="file" name="file_img">
            </div>
            
            <div class="image_area">
            <img class="photo" name="file_img" <c:if test="${ s.s_image.image_name != null }">
            src="${ contextPath }${ s.s_image.route }${ s.s_image.image_r_name }"
  			</c:if>>
  			</div>
        </div>
        
        <div class="button">
            <button type="button" onclick="location.href='${ contextPath }/store/list'">목록으로</button>
            <button type="submit">수정하기</button>
        </div>
        </form>
    </div>
    
    <footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>
	
    <script src="${ contextPath }/resources/js/imagePreview.js"></script>
    
    <script>
   	// 입력 값 유효성 검사
   	function validate(){
   		var form = document.form;
   		if(form.title.value.length > 15){
   			alert("상호명은 15자 이내로 입력 가능합니다.");
   			form.title.select();
   			return false;
   		}
   		
   		if(form.tel.value.length > 15){
   			alert("전화번호는 '-' 포함 15자 이내로 입력 가능합니다.");
   			form.tel.select();
   			return false;
   		}
   		
   		if(form.time.value.length > 60){
   			alert("영업시간은 60자 이내로 입력 가능합니다.");
   			form.time.select();
   			return false;
   		}
   		
   		if(form.menu.value.length > 300){
   			alert("메뉴는 300자 이내로 입력 가능합니다.");
   			form.menu.select();
   			return false;
   		}
   	}
   	</script>
</body>
</html>