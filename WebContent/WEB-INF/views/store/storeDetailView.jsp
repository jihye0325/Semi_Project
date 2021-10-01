<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>  
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath() %>/resources/css/store/storeDetail.css" rel="stylesheet"></head>
    <title>식당 상세페이지</title>
</head>
<body>   
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="search_bar">
            <div class="location_list">
                <ul class="location_ul">
                	<!-- ** 지역별 검색 ** -->
                	<li class="lo all" onclick="selectArea('all')">전체</li>
                    <li class="lo jeju" onclick="selectArea('제주시')">제주시</li>
                    <li class="lo seogwipo" onclick="selectArea('서귀포')">서귀포</li>
                    <li class="lo hamduk" onclick="selectArea('함덕')">함덕</li>
                    <li class="lo gujwa" onclick="selectArea('구좌')">구좌</li>
                    <li class="lo seongsan" onclick="selectArea('성산')">성산</li>
                    <li class="lo udo" onclick="selectArea('우도')">우도</li>
                    <li class="lo pyosan" onclick="selectArea('표선')">표선</li>
                    <li class="lo jungmun" onclick="selectArea('중문')">중문</li>
                    <li class="lo hanlim" onclick="selectArea('한림')">한림</li>
                    <li class="lo hyeobjae" onclick="selectArea('협재')">협재</li>
                    <li class="lo aewol" onclick="selectArea('애월')">애월</li>
                </ul>
            </div>
          
            <form method="get" action="${ contextPath }/store/list">
            <div class="search">
                <input type="text" name="searchValue" placeholder="식당명/메뉴를 검색하세요">
                <input type="image" class="searchimg" src="<%=request.getContextPath()%>/resources/images/search.png">
            </div>
            </form>
        </div>

        <div class="content">
            <div class="info_area">
                <div class="img_area">
                    <img class="store_img" src="${ contextPath }${ store.s_image.route}${ store.s_image.image_r_name }" alt="이미지가 제공되지 않습니다.">
                </div>
                <div class="info">
                    <div class="title_wish">
						<span class="title">${ store.s_name }</span>
						<!-- ** 위시리스트 ** -->
                    	<c:set var="loop_flag" value="false"/>
                    	<c:forEach var="w" items="${ store.s_wishList }" varStatus="v">
                    		<c:if test="${ not loop_flag }">
                    		<c:choose>
                    			<c:when test="${ store.s_wishList.get(v.index).s_no eq store.s_no }">
                    				<!-- 같을 때 -->
                    				<img class="wishlist_img ${store.s_no }" id="wish1" 
                    					 src="<%=request.getContextPath()%>/resources/images/wishlist_y.png" onclick="wish(${ store.s_no }, '${ store.s_name }');">
                    				<c:set var="loop_flag" value="true"/>
                    			</c:when>
                    		</c:choose>
                   			</c:if>
                    	</c:forEach>
                        
                        <c:forEach var="w" items="${ store.s_wishList }" varStatus="v">
                        	<c:if test="${ not loop_flag }">
                        	<c:choose>
                        		<c:when test="${ store.s_wishList.get(v.index).s_no ne store.s_no }">
                        			<!-- 다를 때 -->
                        		    <img class="wishlist_img ${store.s_no }" id="wish" 
                    					 src="<%=request.getContextPath()%>/resources/images/wishlist_n.png" onclick="wish(${ store.s_no }, '${ store.s_name }');">
                    				<c:set var="loop_flag" value="true"/>
                        		</c:when>
                        	</c:choose>
                        	</c:if>
                        </c:forEach> 
                        <c:if test="${empty store.s_wishList}">
                                    <img class="wishlist_img ${store.s_no }" id="wish" 
                    					 src="<%=request.getContextPath()%>/resources/images/wishlist_n.png" onclick="wish(${ store.s_no }, '${ store.s_name }');">
                        </c:if>
                    </div>
                    <div class="tel_area info_img">
                        <img src="<%=request.getContextPath()%>/resources/images/tel.png">
                        <span>전화번호 :</span>&nbsp;
                        <span class="tel">${ store.s_tel }</span>
                    </div>
                    <div class="address_area info_img">
                        <img src="<%=request.getContextPath()%>/resources/images/map.png">
                        <span>위치 :</span>&nbsp;
                        <span class="address info_img">${ store.s_address }</span>
                    </div>
                    <div class="time_area info_img">
                        <img src="<%=request.getContextPath()%>/resources/images/time.png">
                        <span class="time_info">영업시간 :</span> 
                        <pre class="time">${ store.s_time }</pre>
                    </div>

                    <div class="menu_outer">
                        <div class="menu_area">
                            <img class ="menu_img" src="<%=request.getContextPath()%>/resources/images/menu.png">
                            <span>메뉴</span>
                        </div>
                        <div class="menu">
                            ${ store.menu }
                        </div>
                    </div>
                    
                </div>
            </div>
            
            <!-- 지도 -->
            <div class="map_area">
	            <div class="map" id="map">
	            <!-- 오픈키 지움 -->
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=오픈키&libraries=services"></script>
				<script>
				if('${store.s_address}'){
					console.log('${store.s_address}');
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					    mapOption = {
					        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
					        level: 3 // 지도의 확대 레벨
					    };  
		
					// 지도를 생성합니다    
					var map = new kakao.maps.Map(mapContainer, mapOption); 
		
					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();
		
					// 주소로 좌표를 검색합니다
					geocoder.addressSearch('${ store.s_address }', function(result, status) {
		
					    // 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {
		
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });
		
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					            content: '<div style="width:150px;text-align:center;padding:6px 0;">${ store.s_name }</div>'
					        });
					        infowindow.open(map, marker);
		
					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
					    } 
					});
				} else {
					var infowindow = new kakao.maps.InfoWindow({zIndex:1});
					
					var mapContainer = document.getElementById('map'),
						mapOption = {
							center: new kakao.maps.LatLng(37.566826, 126.9786567),
							level : 2
					};
					
					var map = new kakao.maps.Map(mapContainer, mapOption);
					
					var ps = new kakao.maps.services.Places();
					
					ps.keywordSearch('${ store.s_name }', placesSearchCB);
					
					function placesSearchCB(data, status, pagination){
						if(status === kakao.maps.services.Status.OK){
							var bounds = new kakao.maps.LatLngBounds();
							
							for(var i=0; i<data.length; i++){
								displayMarker(data[i]);
								bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
							}
							
							map.setBounds(bounds);
						}
					}
					function displayMarker(place){
						var marker = new kakao.maps.Marker({
							map: map,
							position: new kakao.maps.LatLng(place.y, place.x)
						});
						
						kakao.maps.event.addListener(marker, 'click', function(){
							infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
							infowindow.open(map, marker);
						});
					}
				}
				</script>
	            </div>
            </div>
            <div class="reply_outer">
            <div class="score_reply">
                <div class="score_area">
                    <img class="score_img" src="<%=request.getContextPath()%>/resources/images/star.png">
                    <div class="avgscore" value=${ store.s_score }> 별점 : ${ store.s_score } / 5 </div>
                </div>

                    <div class="reply_list">
                    <c:forEach items="${ store.s_replyList }" var="r">
                        <ul class="reply_ul">
                        <!-- 성별에 따라 아이콘 다르게 표시 -->
                            <li><img class="gender" 
                            <c:choose>
                            <c:when test="${ r.gender == 'F' }">
                            src="<%=request.getContextPath()%>/resources/images/reply_w.png"
             				</c:when>
             				<c:otherwise>
             				src="<%=request.getContextPath()%>/resources/images/reply_m.png"
             				</c:otherwise>
                            </c:choose>
                            ></li>
                            <li class="rwriter">${ r.nickname }</li>
                            <li>|</li>
                            <li class="rcontent"><div>${ r.comment_info } </div></li>
                            <c:if test="${ r.user_no == loginUser.userNo }">
                            	<li class="delete" onclick="rdelete(${ store.s_no }, ${ r.comment1_no });">삭제</li>
                            </c:if>
                            <c:if test="${ loginUser.authority == 3 }">
                            	<li class="delete" onclick="rdelete(${ store.s_no }, ${ r.comment1_no });">삭제</li>
                            </c:if>
                            <li>|</li>
                            <li class="rdate"><fmt:formatDate value="${ r.comment_create_date }"
                            type="both" pattern="yyyy-MM-dd"/></li>
                            <li class="score_img">
                            <c:if test="${ r.score >= 1 }">
                                <img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">
                            </c:if>
                            <c:if test="${ r.score >= 2 }">
                                <img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">
                            </c:if>
                            <c:if test="${ r.score >= 3 }">
                                <img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">
                            </c:if>
                            <c:if test="${ r.score >= 4 }">
                                <img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">
                            </c:if>
                            <c:if test="${ r.score >= 5 }">
                                <img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">
                            </c:if>
                            </li>
                        </ul>
                    </c:forEach>
                    </div>
                    <!-- **식당 별점/리뷰는 한번만 등록 가능 -->
                    	<c:set var="loop_flag2" value="false"/>
                    	<c:forEach items="${ store.s_replyList }" var="r">
                    		<c:if test="${ not loop_flag2 }">
                    		<c:choose>
                    			<c:when test="${ r.user_no == loginUser.userNo}">
                    				<div class="reply_write">리뷰등록은 한번만 가능합니다.</div>
                    				<c:set var="loop_flag2" value="true"/>
                    			</c:when>
                    		</c:choose>
                    		</c:if>
                    	</c:forEach>
                        
                        <c:forEach items="${ store.s_replyList }" var="r">
                    		<c:if test="${ not loop_flag2 }">
                    		<c:choose>
                    			<c:when test="${ r.user_no != loginUser.userNo }">
                    			<div class="reply_write">
		                    	<div>(<span id="length">0</span> / 100 )</div>
		                        <textarea class="reply_content"></textarea>
		                        <!-- ** 별점주기 ** -->
		                        <div class="reply_score">
		                        <select class="score name ="score">
		                        	<option value="5">5점</option>
		                        	<option value="4">4점</option>
		                        	<option value="3">3점</option>
		                        	<option value="2">2점</option>
		                        	<option value="1">1점</option>
		                        </select>
		                        </div>
		                        
                        		<button id="reply_insert" onclick="addReply(${ store.s_no });">댓글 등록</button>
                    				<c:set var="loop_flag2" value="true"/>
                    			</c:when>
                    		</c:choose>
                    		</c:if>
                    	</c:forEach>
                    	<div>
                    	
                    	<c:if test="${ empty store.s_replyList }">
                    			<div class="reply_write">
		                    	<div>(
		                    	<span id="length">0</span> / 100 )
		                    	</div>
		                        <textarea class="reply_content"></textarea>
		                        <!-- ** 별점주기 ** -->
		                        <div class="reply_score">
		                        <select class="score name ="score">
		                        	<option value="5">5점</option>
		                        	<option value="4">4점</option>
		                        	<option value="3">3점</option>
		                        	<option value="2">2점</option>
		                        	<option value="1">1점</option>
		                        </select>
		                        </div>
                        		<button id="reply_insert" onclick="addReply(${ store.s_no });">댓글 등록</button>
                    		</c:if>
                	</div>
               </div>
            </div>
        </div>

        <div class="button_area">
            <button type="button" onclick="location.href='${ contextPath }/store/list'">목록으로</button>
            <!-- 관리자인 경우 보이는 버튼 -->
            <c:if test="${ loginUser.authority == 3 }">
            <button type="button" onclick="updateStore();">수정하기</button>
            <button type="button" onclick="deleteStore()">삭제하기</button>
            </c:if>
            <!-- form 태그를 post 방식으로 submit -->
            <form name="storeForm" method="post">
            	<input type="hidden" name="s_no" value="${store.s_no }">
            	<input type="hidden" name="image_r_name" value="${ store.s_image.image_r_name }">
            </form>
        </div>
    </div>

    <footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>
	
    <script>
		// 지역에 mouseover/mouseout 시 onmouseover 클래스 추가/제거
		const location_list = document.querySelector(".location_list");
		
		location_list.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('lo'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('lo'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		location_list.addEventListener('mouseout', function(){	
			
			if(event.target.classList.contains('lo'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('lo'))
				event.target.parentNode.classList.remove('onmouseover');
		});
	</script>
	
	<!-- 지역 검색 -->
	<script>
		function selectArea(area){
			console.log(area);
			location.href = '${ contextPath }/store/list?area=' + area;
		}
	</script>
	
	<!-- 댓글 등록 -->
	<script>	
		// 글자수 실시간 변화 : 100자 미만이면 파란색, 100자 초과하면 빨간색으로 현재 글자수 표시
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
	
		function addReply(s_no){
			// 댓글내용 100자 이내로 입력
			var content = $(".reply_content");
			// 댓글 내용 100자 넘어가면 등록시 안내창 뜨고 , focus 
			if(content.val().length > 100) {
				alert('댓글 내용은 100자 이내로 입력 가능합니다.');
				$.ajax({
					url : "${contextPath}/store/insertReply",
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
				url : "${contextPath}/store/insertReply",
				type : "post",
				data : { s_no : s_no, content : $(".reply_content").val(), score : $(".score option:selected").val()},
				dataType : "json",
				success : function(data){
					if(data != null){
						console.log(data);
						alert("댓글이 등록되었습니다.");
						
						var html = '';
						var html2 = '<div class="reply_write">리뷰등록은 한번만 가능합니다.</div>';
						
						var img = '';
						
						var score = '';
						
						for(var key in data){
							if(data[key].gender == 'F'){
								img = ' src="<%=request.getContextPath()%>/resources/images/reply_w.png"';
							} else {
								img = ' src="<%=request.getContextPath()%>/resources/images/reply_m.png"';
							}
							// 별 개수
							if(data[key].score == 1){
								score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
							} else if(data[key]. score == 2){
								score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
							} else if(data[key]. score == 3){
								score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
							} else if(data[key]. score == 4){
								score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
							} else if(data[key]. score == 5){
								score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
									  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
							}
							
							var user_no = '${ user_no }'; 
							
							html += '<ul class="reply_ul">'
								+ '<li><img class="gender"'
								+ img
								+ '></li>'
								+ '<li class="rwriter">' + data[key].nickname 
								+ '</li><li>|</li><li class="rcontent"><div>' + data[key].comment_info
								if("${ loginUser.userNo }" == data[key].user_no){
									html += '<li class="delete" onclick="rdelete('
										 + data[key].s_no + ', ' 
										 + data[key].comment1_no + ');">삭제</li>'
								}
								html += '</div><li></li></li><li class="rdate">' + data[key].comment_create_date 
								+ '</li><li class="score_img">'
								+ score
								+ '</li></ul>';
						}		
						   // 등록 된 후 리뷰 못 쓰게 막음
							$(".reply_list").html(html);
							$(".reply_write").replaceWith(html2);
							
							$(".avgscore").html('별점 : ' + data[key].avg_score.toFixed(1)  + ' / 5');
						} else {
							alert('댓글 입력 실패!');
						}
					},
					error : function(e){
						console.log(e);
					}
			});
		}	
	</script>
	
	<!-- 댓글 삭제 -->
	<script>
		function rdelete(s_no, c_no){	
			console.log(c_no);
			if(confirm('댓글을 삭제하시겠습니까?')){
				$.ajax({
					url : "${contextPath}/store/deleteReply",
					type : "post",
					data : { s_no : s_no, c_no : c_no},
					dataType: "json",
					success : function(data){
						if(data != null) {
							alert("댓글이 삭제되었습니다.");
		    				console.log(data);
		    				
		    				var html = '';
		    				var html2 = '';
		    				var score = '';
		    				// 새로 갱신 된 댓글 목록을 for문을 통해서 html에 저장
		    				for(var key in data) {
		    					if(data[key].gender == 'F'){
									img = ' src="<%=request.getContextPath()%>/resources/images/reply_w.png"';
								} else {
									img = ' src="<%=request.getContextPath()%>/resources/images/reply_m.png"';
								}
								// 별 개수
								if(data[key].score == 1){
									score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
								} else if(data[key]. score == 2){
									score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
								} else if(data[key]. score == 3){
									score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
								} else if(data[key]. score == 4){
									score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
								} else if(data[key]. score == 5){
									score = '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">'
										  + '<img class="star" src="<%=request.getContextPath()%>/resources/images/star.png">';
								}
								
								html += '<ul class="reply_ul">'
									+ '<li><img class="gender"'
									+ img
									+ '></li>'
									+ '<li class="rwriter">' + data[key].nickname 
									+ '</li>|<li class="rcontent">' + data[key].comment_info
									if("${ loginUser.userNo }" == data[key].user_no){
										html += '<li class="delete" onclick="rdelete(${ store.s_no }, ${ r.comment1_no });">삭제</li>'
									}
									if("${loginUser.authority}" == 3) {
										html += '<li class="delete" onclick="rdelete(${ store.s_no }, ${ r.comment1_no });">삭제</li>'
									}
										
									html += '</li><li class="rdate">' + data[key].comment_create_date 
									+ '</li><li class="score_img">'
									+ score
									+ '</li></ul>';
									
									score = data[key].avg_score.toFixed(1);
		    				}
		    				
							html2 += '<div class="reply_write"><div>('
				                   + '<span id="length">0</span> / 100 )</div>'
				                   + '<textarea class="reply_content"></textarea>'
				                   + '<div class="reply_score">'
				                   + '<select class="score name ="score">'
				                   + 	'<option value="5">5점</option>'
				                   +	'<option value="4">4점</option>'
				                   +	'<option value="3">3점</option>'
				                   +	'<option value="2">2점</option>'
				                   +	'<option value="1">1점</option>'
				                   + '</select></div>'
		                           + '<button id="reply_insert" onclick="addReply(${ store.s_no });">댓글 등록</button> ';
		    				
		    				// 갱신 된 목록 화면에 적용
		    				$(".reply_list").html(html);
		    				// 삭제하면 다시 리뷰 등록가능하도록
		    				$(".reply_write").replaceWith(html2);
		    				// 별점
		    				$(".avgscore").html('별점 : ' + score  + ' / 5');		    				
		    				// 글자수 세기
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
						} else {
							alert('댓글 삭제 실패');
						}
					},
					error :function(e){
						console.log(e);
					}
				});	
			}
		}
	</script>
	<!-- 위시리스트 -->
	<script>
	// 이미지 클릭시 s_no, user_no 서블릿으로
	// ajax로 s_no - user_no insert하고 수행이  잘 되면 화면에서 표시
	// 위시리스트 제거 로직 추가
	// /list 요청시 해당 유저가 가지고 있는 wishList 조회해서 화면에서 표시
	
	function wish(s_no, s_name){
		// alert("식당번호: " + s_no + "가 눌렸습니다.");
		// console.log(event.target);
		
		// 위시리스트에 없던 식당  			
		if(event.target.id == 'wish'){
			$.ajax({
				url : "${pageContext.servletContext.contextPath}/store/insertWish",
				data : { s_no : s_no},
				type : "get",
				success : function(result){
					alert("식당 '" + s_name + "'를 위시리스트에 담았습니다.");
					console.log('위시리스트에 담았습니다.');
					$("." + s_no).attr("src", "${ contextPath }/resources/images/wishlist_y.png");	// 회색으로 
					$("." + s_no).attr("alt", "wish1");		// ** alt??
					$("." + s_no).attr("id", "wish1");	// 위시리스트 담은 식당으로 바꿔줌
				},
				error : function(){
					console.log('위시리스트 담기에 실패했습니다.');
				}				
			});
		
		// 위시리스트에 있던 식당
		} else if(event.target.id == 'wish1'){
			$.ajax({
				url : "${pageContext.servletContext.contextPath}/store/deleteWish",
				data : { s_no : s_no},
				type : "get",
				success : function(result){
					alert("식당 '" + s_name + "'를 위시리스트에서 삭제했습니다.");
					console.log('위시리스트에서 삭제했습니다.');
					$("." + s_no).attr("src", "${ contextPath }/resources/images/wishlist_n.png");	// 흰색으로
					$("." + s_no).attr("alt", "wish");
					$("." + s_no).attr("id", "wish");	// 위시리스트에 없는 식당으로 바꿔줌
				},
				error : function(){
					console.log('위시리스트 삭제에 실패했습니다.');
				}
			});
		}
	}
	</script>
	
	<!-- 식당 수정/삭제 -> **관리자인 경우** -->
	<script>
		function updateStore(){
			document.forms.storeForm.action = "${ contextPath }/store/updateView";
			document.forms.storeForm.submit();
		}
		
		function deleteStore(){
			if(confirm('식당 정보를 삭제하시겠습니까?')){
				document.forms.storeForm.action = "${ contextPath }/store/delete";
				document.forms.storeForm.submit();	
			}
		}
	</script>
</body>
</html>