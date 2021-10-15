<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<link>
<head>
    <!--  다시 변경  -->
  
    <title>예약하기 화면</title>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" /> 
    <!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script> 
    <script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> -->
    <!-- 다운로드하여서 적용할 경우에는 (https://jqueryui.com/download/)로 가서 다운로드하시면 됩니다.-->
   
                                        <!-- <link> 태그의 rel 속성은 현재 문서와 외부 리소스 사이의 연관 관계를 명시합니다. -->
    <link href ="<%=request.getContextPath()%>/resources/css/booking/booking.css" rel="stylesheet"></link>
   

   <%-- <link rel="icon" type="image/x-icon" href="<%=request.getContextPath()%>/resources/images/favicon.png">  --%>

	
    

    <style>

        /* -------------------------- 달력 -------------------------- */ 

        /*달력크기*/
        .ui-datepicker{ 
            font-size: 15px; 
            width: 350px; 
            height: auto;
            text-align: center; 
        }
       /*글씨크기*/
        .ui-datepicker select.ui-datepicker-month{ width:30%;height: 500px; font-size: 10px; }
        .ui-datepicker select.ui-datepicker-year{ width:40%; height: 500px;font-size: 11px; }
        /*주말색*/
        .ui-datepicker-calendar > tbody td.ui-datepicker-week-end:first-child a { color: red; }
        .ui-datepicker-calendar > tbody td.ui-datepicker-week-end:last-child a { color: blue; }
        /*배경색*/
        .ui-widget-content{
            background: rgb(231, 208, 195) none;
        }
        
        /*상단 색상*/
        .ui-datepicker-header {
            background: rgb(214, 154, 154);
            
        }
        /*년도 중앙 정렬*/
        .ui-datepicker-title {
            text-align: center;
        }
        /*달력일자 색 + 일자 가운데 배열*/
        .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
            border: 1px solid #d3d3d3/*{borderColorDefault}*/;
            background: #c99e9e;
            font-weight: normal/*{fwDefault}*/;
            color: #000000/*{fcDefault}*/;
            text-align: center;
        }

        /*날짜호버시 색*/
        .ui-datepicker-calendar .ui-state-hover {
            background: #ca6060;
        }
        /*확정된날짜 색*/
        .ui-datepicker-calendar .ui-state-active {
            background: rgb(68, 164, 209);
            
        }
    </style>

</head>
<body>
   <%@ include file="/WEB-INF/views/common/header.jsp" %>
   
   
   <div class ="logo">
            <a href="<%=request.getContextPath()%>">   
                <img class ="second_logo" src="${ contextPath }/resources/images/logo (2).png" alt="jejudo" >
            </a>
            <img class ="first_logo" src="${ contextPath }/resources/images/common/banner.png" width="100%" alt="jejudo" >
    </div>
   
   
<div id = "first">

 
<!-- 여기에서 사용하는 jquery가 내 jquery랑 충돌함 -->


<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
<%@ include file="/WEB-INF/views/common/aside2.jsp" %>

<!-- 해더 부분-->
	<div id ="nav_radiuns">
	
	<form>
		<nav id = "nav">
			<ul>
				<li class="area" onclick="selectArea('제주')"><a>제주시</a></li>
                <li class="area" onclick="selectArea('서귀포')"><a>서귀포</a></li>
                <li class="area" onclick="selectArea('함덕')"><a>함덕</a></li>
                <li class="area" onclick="selectArea('성산')"><a>성산/우도  </a></li>
                <li class="area" onclick="selectArea('표선')"><a>표선</a></li>
                <li class="area" onclick="selectArea('중문')"><a>중문</a></li>
                <li class="area" onclick="selectArea('협재')"><a>한림/협재</a></li>
                <li class="area" onclick="selectArea('애월')"><a>애월</a></li>
				<li>
					<input type="date" id="datepicker1" value="출발일">
					
					 <script>
					 	//dateFormat 해줌
			               $(function() {
			                  
			                  $('#datepicker1').datepicker({
			                	  dateFormat: 'yy-mm-dd',
			                	  onSelect: function(date) {
			                      
			                	}});
			                });	
			               
		             </script>
				</li>
				<li style="width:10px;">&nbsp;~</li>
				<li>
					<input type="date" id="datepicker2" value="도착일">
					<script>
					 $(function() {
		                  
		                  $('#datepicker2').datepicker({
		                	  dateFormat: 'yy/mm/dd',
		                	  onSelect: function(date) {
		                     
		                	}});
		                });	
		            </script>
				</li>
				
				<li>
					<select id="max" name="max" style="width:100px">
		                    <option selected="selected" >인원</option>
		                    <option value="1">1</option>
		                    <option value="2">2</option>
		                    <option value="3">3</option>
		                    <option value="4">4</option>
		                    <option value="5">5</option>
		                    <option value="6">6 이상</option>
		             </select>
		             <script>
		             	$("[name=max]").change(selectChange);
		             	
		             	function selectChange() {
		            		let value = $("option:selected").val();
		            		$("#id").val(value);
		            	 	console.log($('#id').val(value));
		             	}
		             </script>
				</li>
				<li>
		              <!--   <input type="button" id="search" value="검색" onclick="search()"> --> 
		              <!-- 하려는 기능, 검색 버튼을 눌렀을때, datepicker + max 의 value가 RoomList로 넘어가서 select해 오는거 -->
		                <button  id="search" style="width: 70px;">검색</button>
				</li>
			</ul>
		</nav>
	</form>
		
	</div>	
	<!-- 예약 화면 -->
	<div class="booking-content">
	    <!--******* 숙소 ****************  ******  ******  ******  ******  ******  ******  ******  ******  ******  -->
	    <!-- 예약 화면 -->
	    <div class="booking">
	        <ul class="rooms">
	            <c:forEach var="r" items="${ roomimage }">
	            <li id="li">
	            
	        
	            
	            	<div class="box">
	            		<div id="img"><img class="room" src="${ contextPath }${r.roomimage.get(0).route}${r.roomimage.get(0).image_r_name}"  alt="room1"></div>
	            		<div id="left2"> 
		            		<h3 style="margin-top:0px;"> 
		            		     <img class="icon" src="${contextPath }/resources/images/star_n.png" > 숙소 NO . ${ r.r_no }</h3>
		            		<h3> <img class="icon" src="${contextPath }/resources/images/rooms/roomicon.png" > 숙소이름 : ${ r.r_name }</h3>
		            		<h3> <img class="icon" src="${contextPath }/resources/images/map.png" > 주소 : ${r.r_address }</h3>
		            		<h3> <img class="icon" src="${contextPath }/resources/images/tel.png" > 전화번호 : ${ r.r_tel }</h3>
		            	    <h3> <img class="icon" src="${contextPath }/resources/images/rooms/money.png" > 숙소가격 : ${ r.r_pay } 원</h3>
		            		<h3> <img class="icon" src="${contextPath }/resources/images/rooms/schedule.png" > 기간 : ${r.r_start } ~ ${r.r_end }</h3>
	            		<div id="A">
	            		<c:set var="loop_flag" value="false" /> 
	            		<!-- 이거 지워지면 다 지워지는 거니까 신중하게 지우자 -->
	            		<c:forEach var= "w" items="${ wishList }">
	            			<c:if test = "${ w.rno eq r.r_no }">
	            				<c:set var="loop_flag" value="true"/>
	            			</c:if>
	            		</c:forEach>
	            		
	            		<c:choose>
							<c:when test="${loop_flag}">
	            						<img class="wish ${ r.r_no }" id="wish1" src="${ contextPath }/resources/images/jeju (1).png" onclick="test(${ r.r_no })" >
	            						
	            			</c:when>
	            			<c:otherwise>
	            						<img class="wish ${ r.r_no }" id="wish" src="${ contextPath }/resources/images/jeju.png" onclick="test(${ r.r_no })" >
	            			</c:otherwise>
						</c:choose>
	            			
	            			
	            			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;위시리스트
		                    <p id="bottom"><input type="button" id="Room1" onclick="BookingDetail(${ r.r_no })" value="상세보기"> </p>
		                    

	            		</div>
	            		</div>
	            		

	                 	 <!-- 위시리스트 + 상세보기 -->
	            		<!-- 위시리스트 + 상세보기 -->
	            	</div>
	            		
	            </li>
	            </c:forEach>
	        </ul>
	    </div>
	</div>
	
	<!--******* 페이징바 ****************  ******  ******  ******  ******  ******  ******  ******  ******  ******  -->
	
		<ul class="board_paging">
	    		<!--  맨 처음으로 (<<) -->
					<li><a href="${ contextPath }/booking/list?page=1">&lt;&lt;</a></li>
					
				<!--  이전 페이지(<) -->
					<li>
						<c:choose>
						<c:when test="${ pi.page > 1 }">
							<a href="${ contextPath }/booking/list?page=${ pi.page -1 }">&lt;</a>
						</c:when>
						<c:otherwise>
						<a href="#">&lt;</a>
						</c:otherwise>
						</c:choose>
					</li>
					
				<!--  페이지 목록(최대10개) -->
					<c:forEach var="p" begin="${pi.startPage }" end = "${pi.endPage}">
						<li>
							<c:choose>
								<c:when test="${ p eq pi.page }">
									<a href="#" class="current_page">${ p }</a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath }/booking/list?page=${p}${searchParam}">${p }</a>
								</c:otherwise>
							</c:choose>
						</li>
					</c:forEach>
				<!-- 다음 페이지로(>) -->
					<li>
						<c:choose>
							<c:when test="${ pi.page < pi.maxPage }">
							<a href="${ contextPath }/booking/list?page=${ pi.page +1 }">&gt;</a>
							</c:when>
						
							<c:otherwise>
								<a href="#">&gt;</a>
							</c:otherwise>
						</c:choose>
					</li>
					
				<!-- 맨 끝으로 -->
					<li><a href="${ contextPath }/booking/list?page=${pi.maxPage }">&gt;&gt;</a></li>
	    </ul>
</div>
<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</footer>

<c:choose>
<c:when test = "${ !empty loginUser }">



<script>



      // 하려는거. 이미지를 눌렀을때 R.no, User.no로 데이터를 를 입력 받는다.
      // int r.no = request.getParameter("R.no"); 서블릿에 요청
      // int user.no = request.getParameter("user.no"); 
      // 1. ajax로 rno-userno 테이블 insert 후 잘 insert 되면 화면에서 표시하기
      // 2. 1번 기능에 위시리스트 제거 로직도 추가해야함 
      // 3. /list 요청 시 해당 유저가 가지고 있는 wishlist 조회해서 화면에서 표시하기 
      
      // <img class="wish ${ r.r_no }" id="wish" src="${ contextPath }/resources/images/jeju.png" onclick="test(${r.r_no})" >
      
      function test(rno){
    	  alert("숙소번호 : [ " +rno+" ]가 눌렸습니다.");
    	  /* var a = document.querySelector(".wish"); */
    	  // var a = document.querySelectorAll(".wish");
    	  /* 문제사항 : rno 7이 들어오면 a[6].id가 됨   4로 나눈수를  넣으면..?  4*0+1     */
    	
    	  console.log(event.target);
    	  
    	  if(event.target.id == 'wish') {
	  	    	$.ajax({
	    			url : "${pageContext.servletContext.contextPath}/booking/wishinsert",
	    			data : {rno:rno},  
	     			type : "get",
	    			success : function(result){
	  				// 성공 시 호출 된는  함수에 서버에서 돌아온 응답 데이터가 넘어옴
	  				alert("숙소번호 : [ " +rno+" ]를위시리스트에 담았습니다");
	  				console.log('위시리스트에 담았습니다.');
	  				$("."+rno).attr("src", "${ contextPath }/resources/images/jeju (1).png"); //회색 돌하르방
	  				$("."+rno).attr("alt", "wish1");
	  				$("."+rno).attr("id", "wish1");
	  				
	  			},
	    			error : function(){
	  				console.log('위시리스트에 담기 실패하였습니다.');
	  			},
	  			
	    		}); 
	  	    	
    	  }else if(event.target.id == 'wish1' ){
    		  $.ajax({
	    			url : "${pageContext.servletContext.contextPath}/booking/wishdelete",
	    			data : {rno:rno},  
	     			type : "get",
	    			success : function(result){
	  				// 성공 시 호출 된는  함수에 서버에서 돌아온 응답 데이터가 넘어옴
	  				alert("숙소번호 : [ " +rno+" ]를위시리스트에 담기 취소하였습니다.");
	  				console.log('위시리스트에 담기 취소하였습니다..');
	  				$("."+rno).attr("src", "${ contextPath }/resources/images/jeju.png"); // 하얀 돌하르방
	  				$("."+rno).attr("alt", "wish");
	  				$("."+rno).attr("id", "wish");
	  				
	  			},
	    			error : function(){
	  				console.log('위시리스트에 담기 실패하였습니다.');
	  			},
	  			
	    		}); 
	    	  
    	  }
    	    	
    	  }
      
      
</script>
</c:when>
<c:otherwise>
	<script>
		function test(rno){
			alert('로그인 후 이용 가능합니다.');
			location.href='${contextPath}/login';
		}
	</script>
</c:otherwise>

</c:choose>


<script>
      //상세보기를 눌렀을때 여기에 저장된 해당 숙소 정보 받아서 Detail.jsp에 보여기게 하기
      function BookingDetail(rno){
    	  location.href='${contextPath}/booking/detail?rno='+rno;
    	  
      }
      
      // 지역 검색 
      function selectArea(area){
    	  console.log(area);
    	  location.href='${contextPath}/booking/list?area=' + area;
      }
      
      
      
</script>

</body>
</html>
    