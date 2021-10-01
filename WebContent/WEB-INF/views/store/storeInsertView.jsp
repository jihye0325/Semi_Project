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
    <title>식당 등록 페이지</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="page_title">
            <h1>신규 식당정보 추가</h1>
        </div>
        <div class=input_image>
        	<img class="icon" src="<%=request.getContextPath()%>/resources/images/store/store.png">
		</div>
        <form method="post" name="form" action="${ contextPath }/store/insert"
        enctype="multipart/form-data" onsubmit="return validate();">
        <div class="input_area">
        	<span>*모든 정보를 빠짐없이 입력해주세요.</span>
            <div class="input">
                <div class="title_div area">상호명</div>
                <input type="text" class="title" name="title" required>
            </div>

            <div class="input">
                <div class="tel_div area">전화번호</div>
                <input type="tel" class="tel" name="tel" required>
            </div>

            <div class="input">
                <div class="ad_div area">위치</div>
                <input type="text" class="address" name="address" required>
            </div>

            <div class="input">
                <div class="time_div area">영업시간</div>
                <textarea class="time" name="time" required></textarea>
            </div>
            
            <div class="input">
                <div class="menu_div area">메뉴&가격</div>
                <textarea class="menu" name="menu" required></textarea>
            </div>

            <div class="input">
                <div class="file_div area">대표 이미지</div>
                <input type="file" class="file" name="file_img" required>
            </div>
            
            <div class="image_area"></div>
        </div>
        
        <div class="button">
            <button type="button" onclick="location.href='${ contextPath }/store/list'">목록으로</button>
            <button id="submitBtn" type="submit">작성하기</button>
        </div>
        </form>
    </div>
    
    <footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>
	
    <script src="${ contextPath }/resources/js/imagePreview.js"></script>
   
   	<!-- jQuery와 Postcodify를 로딩한다 -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>

	<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
	<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>
   
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
   		
		if(form.postcodify_address.value.length == 0){
			alert("ㅇㅇ")
			return false;
		}
   		
   		if(form.menu.value.length > 300){
   			alert("메뉴는 300자 이내로 입력 가능합니다.");
   			form.menu.select();
   			return false;
   		}
   	}
   	
   	</script>
   	<script>

   	</script>
</body>
</html>