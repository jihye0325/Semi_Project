<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>식당</title>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath()%>/resources/css/store/storeList.css" rel="stylesheet"></link>
    <title>식당</title>
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
                	<li class="lo all" onclick="selectArea('all', '${ param.array}', '${ param.searchValue }')">전체</li>
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
         
            <div class="search">
                <input type="text" id="searchValue" name="searchValue" placeholder="식당명/메뉴를 검색하세요" 
                <c:if test="${param.searchValue != 'undefined'}">value="${ param.searchValue }"</c:if>>
                <input type="image" class="searchimg" src="<%=request.getContextPath()%>/resources/images/search.png" onclick="search('score', '${ param.area }');">
            </div>
        </div>
        <span class="array">
            <input type="radio" id="score" name="array" value="score" checked onclick="array('score', '${ param.area }', '${ param.searchValue }');">
            <label for="score">별점순</label>
            <input type="radio" id="new" name="array" value="date" <c:if test="${ param.array == 'date' }">checked</c:if> onclick="array('date', '${ param.area }', '${ param.searchValue }');">
            <label for="new">최신순</label>
        </span>
        <span class="insert">
        	<!-- 관리자만 보이는 버튼 -->
        	<c:if test="${ loginUser.authority == 3 }">
        	<button type="button" onclick="location.href='${contextPath}/store/insert'">식당정보 추가</button>
        	</c:if>
        </span>
        <div class="storeview">
        	<c:forEach var="s" items="${ storeList }">
            <div class="store">
                <div><img class="store_img" <c:if test="${!empty s.s_image.image_r_name}">src="${ contextPath }${ s.s_image.route }${ s.s_image.image_r_name }"</c:if>
                <c:if test="${empty s.s_image.image_r_name}">src="<%=request.getContextPath()%>/resources/images/store/noimage.png"</c:if>></div>
                <div class="store_info">
                	<!-- 위시리스트  --> 
                    <div class="wishlist">
                    
                    <c:choose>
                    <c:when test="${ !empty loginUser }" >
                    
                    <c:set var="loop_flag" value="false"/>
                    <c:forEach var="w" items="${ wishList }">
                    	<c:if test="${ w.s_no eq s.s_no }">
                    		<c:set var="loop_flag" value="true"/>
                    	</c:if>
                    </c:forEach>
                    
                    <c:choose>
                    	<c:when test="${ loop_flag }">
                    		<img class="wishlist_img ${s.s_no }" id="wish1" 
                    				src="<%=request.getContextPath()%>/resources/images/wishlist_y.png" onclick="wish(${ s.s_no }, '${ s.s_name }');">
						</c:when>
                        
                   		<c:otherwise>
                    		    <img class="wishlist_img ${s.s_no }" id="wish" 
                					 src="<%=request.getContextPath()%>/resources/images/wishlist_n.png" onclick="wish(${ s.s_no }, '${ s.s_name }');">
                		</c:otherwise>
                    	</c:choose>
                    </c:when>
                    
                    <c:otherwise>
                    	<img class="wishlist_img"
                				src="<%=request.getContextPath()%>/resources/images/wishlist_n.png" onclick="nwish();">
                    </c:otherwise>
                    </c:choose>
                    </div>
                    
                    <!-- 식당 정보 -->
                    <div class="content">
                        <div class="store_title">${ s.s_name }</div>
                        <div class="score_review">
                        	<div class="score"><img class="score simg" src="<%=request.getContextPath()%>/resources/images/star.png">${ s.s_score }/5</div>
                        	<div class="review">후기(${ s.reply_count })</div>
                        </div>
                        <div class="tel">
                            <img class="tel simg" src="<%=request.getContextPath()%>/resources/images/tel.png">
                            ${ s.s_tel }
                        </div>
                        <div class="map">
                            <img class="map simg" src="<%=request.getContextPath()%>/resources/images/map.png">
                           ${ s.s_address }
                        </div>
                    </div>
                    <button type="button" onclick="detailView(${ s.s_no })">상세보기</button>
                </div>
            </div>
            </c:forEach>
        </div>
        		<ul class="store_paging">
				<%-- 검색한 화면인 경우 searchPararm 정의 --%>
				<c:if test="${!empty param.searchValue }">
					<c:set var="searchParam"
						value="&searchValue=${ param.searchValue }"/>
				</c:if>
				<%-- 지역별 검색한 화면  --%>
				<c:if test="${ !empty param.area }">
					<c:set var="area"
						value="&area=${ param.area }"/>
				</c:if>
				<%-- 최신순 정렬 --%>
				<c:if test="${ param.array == 'date' }">
					<c:set var="array"
						value="&array=${ param.array }"/>
				</c:if>
				<c:if test="${ param.array == 'score' }">
					<c:set var="array"
						value="&array=${ param.array }"/>
				</c:if>
					<!-- 맨 앞으로(<<)-->
                    <li><a href="${ contextPath }/store/list?page=1${ searchParam }${ area }${ array}">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
							<a href="${ contextPath }/store/list?page=${ pi.page -1 }${ searchParam }${ area }${ array}">&lt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&lt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 페이지 목록(최대 10개 -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
					<li>
						<c:choose>
							<c:when test="${ p eq pi.page }">
								<a href="#" class="current_page">${ p }</a>
							</c:when>
							<c:otherwise>
								<a href="${ contextPath }/store/list?page=${ p }${ searchParam }${ area }${ array}">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>			
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
							<a href="${ contextPath }/store/list?page=${ pi.page + 1}${ searchParam }${ area }${ array}">&gt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨 끝으로(>>) -->
					<li><a href="${ contextPath }/store/list?page=${ pi.maxPage }${ searchParam }${ area }${ array}">&gt;&gt;</a></li>
					</li>
				</ul>
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
	
	<!-- ** 로그인 여부 확인 ** -->
	<c:choose>
		<c:when test="${ !empty loginUser }">
			<script>
				function detailView(s_no){
					location.href = '${ contextPath }/store/detail?s_no=' + s_no;
				}
			</script>
		</c:when>
		<c:otherwise>
			<script>
				function detailView(){
					alert('로그인 후 이용 가능합니다.');
					location.href = '${ contextPath }/login';
				}
			</script>
		</c:otherwise>
	</c:choose>
	<!-- 지역 선택-->
	<script>
		function selectArea(area, array, searchValue){
			console.log(area);
			location.href = '${ contextPath }/store/list?area=' + area + '&array=' + array + "&searchValue=" + searchValue; 
		}
	</script>
	<!-- 정렬 -->
	<script>
		function array(array, area, searchValue){
			console.log(array);
			location.href = '${ contextPath }/store/list?area=' + area + '&array=' + array + "&searchValue=" + searchValue; 
		}
	</script>
	<!-- 검색 -->
	<script>
		function search(array, area){
			var searchValue = document.getElementById("searchValue").value;
			console.log(searchValue);
			location.href = '${ contextPath }/store/list?area=' + area + '&array=' + array + "&searchValue=" + searchValue; 
			console.log(searchValue);
	}
	</script>
	
	<!-- 위시리스트 -->
	<script>
	// 이미지 클릭시 s_no, user_no 서블릿으로
	// ajax로 s_no - user_no insert하고 수행이  잘 되면 화면에서 표시
	// 위시리스트 제거 로직 추가
	// /list 요청시 해당 유저가 가지고 있는 wishList 조회해서 화면에서 표시
	function nwish(){
		alert('로그인 후 이용 가능합니다.');
		location.href = '${ contextPath }/login';
	}
	
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
</body>
</html>