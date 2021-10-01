<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상세페이지</title>

<link href="${contextPath }/resources/css/booking/bookingDetail.css" rel="stylesheet"></link>

<style>
/* ------ 예약하려는 날짜 지정 ------------*/
/*datepicer 버튼 롤오버 시 손가락 모양 표시*/
.ui-datepicker-trigger{
	cursor: pointer;
}
/*datepicer input 롤오버 시 손가락 모양 표시*/
.hasDatepicker{
	cursor: pointer;
}

</style>

</head>


<body >
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
<div id="first2">
	<div class="outer">
		<div class="wrap">
			<div class="list_div">
			
			
			<form name="BookingRequestForm" method="post" >
			
			
				<div class="board_list1">  <!--  a-1  -->
					<!-- 상단의 사진 및 내용들  -->
					<div class="a-1">
						<c:forEach var="selectroom" items="${ selectroom }" varStatus="s">
							<c:forEach var="roomimage" items="${ selectroom.roomimage }" varStatus="i">
								<div id="room_thub">
									<img class="big_room" src="${contextPath}${ roomimage.route }${roomimage.image_r_name }" alt="room1">
								</div>
						</c:forEach>
						<div id="left">
							<h3 style="margin-top:7px;"><img class="icon" src="${contextPath }/resources/images/star_n.png" >No. ${ selectroom.r_no }</h3>
								<input type="hidden" name="r_no" value="${ selectroom.r_no }">
							<h3><img class="icon" src="${contextPath }/resources/images/rooms/roomicon.png" >숙소이름 : ${ selectroom.r_name }</h3>
								<input type="hidden" name="r_name" value="${ selectroom.r_name }">
							<h3><img class="icon" src="${contextPath }/resources/images/jeju (1).png" >인원 : ${ selectroom.r_head }명</h3>
								
							<h3><img class="icon" src="${contextPath }/resources/images/rooms/money.png" > 가격 :  ${ selectroom.r_pay }원/1박</h3>
								<input type="hidden" name="r_pay" value="${ selectroom.r_pay }">
							<h3 style="font-size:17px;"><img class="icon" src="${contextPath }/resources/images/map.png" >주소 : ${ selectroom.r_address }</h3>
								<input type="hidden" name="r_address" value="${ selectroom.r_address }">
							<h3 style="font-size:17px;"> <img class="icon" src="${contextPath }/resources/images/rooms/schedule.png" > 예약 가능한 날짜 : ${ selectroom.r_start } ~ ${ selectroom.r_end }</h3>
								<input type="hidden" name="r_start" value="${selectroom.r_start}">
								<input type="hidden" name="r_end" value="${selectroom.r_end }">
						</div>
						
					<div id="right">
						<c:set var="loop_flag" value="false" />
						<c:forEach var="w" items="${ wishList }">
							<c:if test="${ w.rno  eq  selectroom.r_no }">
	            						<c:set var="loop_flag" value="true" />
	            			</c:if>
						</c:forEach>
						
						<c:choose>
							<c:when test="${loop_flag}">
	            						<img class="wish ${ selectroom.r_no }" id="wish1" src="${ contextPath }/resources/images/jeju (1).png" onclick="test(${ selectroom.r_no })" >
	            			</c:when>
	            			<c:otherwise>
	            						<img class="wish ${ selectroom.r_no }" id="wish" src="${ contextPath }/resources/images/jeju.png" onclick="test(${ selectroom.r_no })" >
	            			</c:otherwise>
						</c:choose>
						
					</div>
					
					
					</c:forEach>
					 </div>
					<!--  밑에 작은 사진들  클릭시 큰 사진으로 변경됨-->
					 <div>
						<div class="small">
						<c:forEach var="s" items="${ sroom }" varStatus="r">
							<div>
								<img class="room1" onclick="imgPop('${ contextPath }${sroom.get(r.index).route}${sroom.get(r.index).image_r_name }')"
									src="${ contextPath }${sroom.get(r.index).route}${sroom.get(r.index).image_r_name }"
									alt="room1" width="200px" height="122px">
							</div>
						</c:forEach>
						</div>
					</div>
					
					
					<!--  예약 날짜 + 인원 + 금액 변경 예약하기를 누르면 ~> bookingRequest.jsp -->
					<div id="selectDate">
							<div class="room_detail">
								<div style="width: 44%">
									<h4>
										<span class="title_span"></span> CHECK-IN
									</h4>
									<span class="input_area"> <input type="date"  name="r_start" min= "${selectroom.get(0).r_start }" required></span>
								</div>
								<div style="width: 44%">
									<h4>
										<span class="title_span"></span> CHECK-OUT
									</h4>
									<span class="input_area"> <input type="date" name="r_end"  min= "${selectroom.get(0).r_start }" max="${selectroom.get(0).r_end }" required></span>
								</div>
								<div style="width: 33%">
									<h4>
										<span class="title_span"></span> 인원수
									</h4>
									<span class="input_area"> <!--  select에서 들어온 값이 string으로 들어오네 -->
										<select id="head" name="head">
											<c:forEach var="head"  begin ="1" end ="${selectroom.get(0).r_head}" step="1" >
												<c:set var="i" value="${i+1}"/>
												<option value="${i+1-1}">${i }</option>
											</c:forEach>
										</select>
									</span>
								</div>
							</div>
					</div>
							<div class="btn_area">
								<button type="button" id="Room1" onclick="BookingRequest( '${ selectroom.get(0).r_no }');"> 예약하기 </button>
							</div>
				</div> 
				
				<input type="hidden" name="rno" value="${ selectroom.get(0).r_no }">
			</form>
								
			
<!-- **************************************************************************************************************************** -->
				<!-- 지도 API ******* -->
				
				<div class="API">
					<div id="map" style="width:800px;height:400px;"></div>
				</div>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=오픈기를 넣으세욤&libraries=services,clusterer,drawing"></script>
					<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						    mapOption = {
						        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						        level: 5 // 지도의 확대 레벨
						    };  
			
						// 지도를 생성합니다    
						var map = new kakao.maps.Map(mapContainer, mapOption); 
			
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder();
			
						// 주소로 좌표를 검색합니다
						geocoder.addressSearch('${ selectroom.get(0).r_address}', function(result, status) {
			
						    // 정상적으로 검색이 완료됐으면 
						     if (status === kakao.maps.services.Status.OK) {
						    	 console.log("정상적으로 주소 가져옴")
					
						        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
						        // 결과값으로 받은 위치를 마커로 표시합니다
						        var marker = new kakao.maps.Marker({
						            map: map,
						            position: coords
						        });
			
						        // 인포윈도우로 장소에 대한 설명을 표시합니다
						        var infowindow = new kakao.maps.InfoWindow({
						            content: '<div style="width:150px;text-align:center;padding:6px 0;">${ selectroom.get(0).r_name }</div>'
						        });
						        infowindow.open(map, marker);
			
						        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						        map.setCenter(coords);
						    } 
						});
					</script>
				
				
				<div class="Board">
				
					<div class="board_list2"> 
						<div id="rows">
							<c:forEach var="s" items="${ selectroom }">
								<div id=info>
									<img src="${contextPath }/resources/images/info4.PNG" width="500px;">
								</div>
								<div id=info>
									
									<img src="${contextPath }/resources/images/info2.PNG" width="500px;">
								</div>
								<div id=host >
								 	<div> 
								 	${ s.r_info}<br>
								 	</div>
								</div>
								
							</c:forEach>
						</div>
					</div>
					
					
					<div class="board_list2-1"> 
						<div id="rows">
						<%-- 오늘 달력 --%>
						    <%@ include file="/WEB-INF/views/booking/calendar.jsp"%>
						    
						    
						  <!-- --------------------------- 9/27 수정 --------------------------- -->
						   <!-- --------------------------- 9/27 수정 --------------------------- -->
						    <!-- ---------------------------  수정 --------------------------- -->
						</div>
						
						<div id=info>
							<img src="${contextPath }/resources/images/info1.PNG" width="500px;">
							<img src="${contextPath }/resources/images/info3.PNG" width="500px;">
						</div>
						
					</div>
					
				</div>
				
				
				
<!-- **************************************************************************************************************************** -->
				
				<div class="board_list3"> 
				<div class="reply_area">
					<div class="score_area">
                    	<img class="score_img" src="<%=request.getContextPath()%>/resources/images/star.png">
                    <!-- 	<div class="score"> 별점 : 4.5 / 5 </div> -->
                   <%-- 			<div class="avgscore" value=${selectroom.get(0).r_score }> 별점 : ${ selectroom.get(0).r_score } / 5</div> --%>
                   			<div class="avgscore" value=${selectroom.get(0).r_score }> 별점 : ${ selectroom.get(0).r_score } / 5</div>
                	</div>
					
					
					<div class="reply_list">
					
						<c:forEach  var="r" items="${selectroom.get(0).replyList}">
						<ul class = "reply_ul">
							<li><img class="gender" 
                            <c:choose>
                            <c:when test="${ r.gender == 'F' }">
                            src="${contextPath}/resources/images/reply_w.png"
             				</c:when>
             				<c:otherwise>
             				src="${contextPath}/resources/images/reply_m.png"
             				</c:otherwise>
                            </c:choose>
                            ></li>

                            <li class="rwriter">${r.nickName }</li>
							<li>|</li>
							<li class="rcontent">${r.comment_info }</li>
							<li>|</li>
							<li class="rdate"><fmt:formatDate value="${r.create_date}" type="both" pattern="yyyy-MM-dd"/></li>
							<li class="score_img"> <!-- 별점  -->
								<c:choose>
									<c:when test="${r.score  == '1' }">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									</c:when>
									
									<c:when test="${r.score == '2' }">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									</c:when>
									
									<c:when test="${r.score == '3' }">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									</c:when>
									
									<c:when test="${r.score == '4' }">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									</c:when>

									<c:otherwise>
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									<img class="star" src="${ contextPath }/resources/images/star.png">
									</c:otherwise>
								</c:choose>
							</li>
						</ul>
						</c:forEach>
					</div>
					
					<div class="reply_write">
						<div>(<span id="length"></span> / 100 )</div>
						
						<textarea class="reply_content"></textarea>
						
						<!-- 별점 모양  -->
						<select class="score" name="score"  onchange="myFunction(this.value)">
			                    <option value="0" >별점</option>
			                    <option value="1">1</option>
			                    <option value="2">2</option>
			                    <option value="3">3</option>
			                    <option value="4">4</option>
			                    <option value="5">5</option>
			            </select>
			            <script>
				            function myFunction(str) {
				                alert("별점을  "+ str + " 점 주었습니다." );
				            }
			            </script>
			            
							<button onclick="addReply(${selectroom.get(0).r_no});">등록</button>
					
					</div>
					
					
				<div class="button_area">
					<button type="button" id="Room2"  style="WIDTH: 150px; HEIGHT: 50px; font-size: 20px;"
								onclick="location.href='${contextPath}/booking/list'">목록으로</button>
				    <%-- <c:if test = "${loginUser.userNo == selectroom.get(0).user_no && loginUser.userNo == 3 }">
				    <button type ="button" onclick ="updateRoom();">수정하기</button>
				    </c:if> --%>
				</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>

<footer>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</footer>

<!--  예약 신청  -->
<c:choose>
<c:when test = "${ !empty loginUser }">
	<script>
	function BookingRequest(rno){
		 document.forms.BookingRequestForm.action='${contextPath}/booking/Rq?rno='+rno;
		 document.forms.BookingRequestForm.submit();
	}
	</script>
</c:when>
<c:otherwise>
	<script>
		function BookingRequest(rno){
			alert('로그인 후 이용 가능합니다.');
			location.href='${contextPath}/login';
		}
	</script>
</c:otherwise>
</c:choose>

<!-- 위시리스트 -->
<c:choose>
<c:when test = "${ !empty loginUser }">
<script>
  
    function test(rno){
  	  alert("숙소번호 : [ " +rno+" ]가 눌렸습니다.");
  	 
  	
  	  console.log(event.target);
  	  
  	  if(event.target.id == 'wish') {
	  	    	$.ajax({
	    			url : "${pageContext.servletContext.contextPath}/booking/wishinsert",
	    			data : {rno:rno},  
	     			type : "get",
	    			success : function(result){
	  				// 성공 시 호출 된는  함수에 서버에서 돌아온 응답 데이터가 넘어옴
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

<!-- 댓글달기  -->	
<c:choose>
<c:when test = "${ !empty loginUser }">
<script>

	$(".reply_content").keyup(function(){
		var content = $(this).val();
		$.ajax({
			url : "${contextPath}/store/insertReply",
			type : "get",
			data : { content : $(".reply_content").val()},
			dataType : "json",
			success : function(data){
				if(data != null){
					$("#length").html(content.length);
					if(content.length > 100){
						$("#length").css("color", "red");
					} else {
						$("#length").css("color", "blue");
					}
				}
			},
			error : function(e){
				console.log(e);
			}
				
		});
	});

//댓글 달기 버튼 클릭 시 댓글 저장(insert) 기능 수행 후
function addReply(rno){
	
	// 댓글내용 100자 이내로 입력
	var content = $(".reply_content");
	// 댓글 내용 100자 넘어가면 등록시 안내창 뜨고 , focus 
	if(content.val().length > 100) {
		alert('댓글 내용은 100자 이내로 입력 가능합니다.');
		$.ajax({
			url : "${contextPath}/booking/insertReply",
			type : "get",
			data : { content : $(".reply_content").val()},
			dataType : "json",
			success : function(data){
				if(data != null){
					content.focus();
				}
			},
			error : function(e){
				console.log(e);
			}
				
		});
		return;
	}
	
	$.ajax({
		url : "${contextPath}/booking/insertReply",
		type:"post",
		data : {rno:rno, content:$(".reply_content").val(), score:$(".score option:selected").val()},
		dataType:"json",
		success:function(data){
				if(data !=null){
					console.log(data);
					var html='';
					var img = '';
	                var score = '';
	               
					//새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
					
					for(var key in data){
						
						if(data[key].gender == 'F'){
							img = ' src="${contextPath}/resources/images/reply_w.png"';
						} else {
							img = ' src="${contextPath}/resources/images/reply_m.png"';
						}
						
						
						if(data[key].score == 1){
							score = '<img class="star" src="${contextPath}/resources/images/star.png">';
						}else if(data[key].score == 2){
							score = '<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">';
						}else if(data[key].score == 3){
							score = '<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">';
						}else if(data[key].score == 4){
							score = '<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">';
						}else if(data[key].score == 5){
							score = '<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">'+
							'<img class="star" src="${contextPath}/resources/images/star.png">';
						}
						
						html +='<ul class="reply_ul">'
							+ '<li><img class="gender"' + img+ '></li>'
						+'</li><li class="rwriter">'+ data[key].nickName 
						+'</li><li class="rcontent">'+data[key].comment_info
						+'</li><li class="rdate">'+data[key].create_date
					    +'</li><li class="score_img">'+score+'</li></ul>';
					}
					// 갱신 된 댓글 목록을 다시 적용
					$(".reply_list").html(html);
					// 댓글 작성 부분 리셋
					$(".reply_content").val("");
					$("#length").html(0);
					
					$(".avgscore").html('별점 : ' + data[key].avg_score.toFixed(1)  + ' / 5');
				}else{
					alert('댓글달기 실패');
					
				}
			},
		error:function(e){
			console.log(e);
			}
		
		
	});
}
    
</script>
</c:when>
<c:otherwise>
	<script>
		function addReply(){
			alert('로그인 후 이용 가능합니다.');
			location.href='${contextPath}/login';
		}
	</script>
</c:otherwise>
</c:choose>
	
	
<!-- 작은 사진 누르면 팝업창으로 생성 -->
<script>
function imgPop(img){
	img1=new Image();
	img1.src=(img);
	imgControll(img);
}

function imgControll(img){
	if( (img1.width != 0 && img1.height != 0 ) ){
		viewImage(img);
	}else{
		controller = "imgController('"+img+"')";
		intervalID= setTimeout(controller,20);
	}
}
function viewImage(img){
	W = img1.width;
	H = img1.height;
	O = "width=" + W + ",height=" + H + ",scrollbars=yes";
	imgWin=window.open("","",O);
	imgWin.document.write("<html><head><title>: 숙소 자세히 보기 :</title></head>");
	imgWin.document.write("<body>");
	imgWin.document.write("<img src="+ img +" onclick='self.close()' style='cursor:pointer;' width=80%; height=80%; title='클릭하면 닫힘'>");
	imgWin.document.close();
}
</script>





</html>




