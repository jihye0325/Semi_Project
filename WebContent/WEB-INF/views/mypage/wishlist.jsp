<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/banner.jsp"%>
<style>
.board_title {
	margin-top: 20px;
	text-align: left;
	display: inline-block;
	border-radius: 4px;
	background-color: #FFBF84;
	border: none;
	color: black;
	text-align: center;
	font-size: 16px;
	padding: 10px;
	width: 140px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

.box {
	text-align: center;
}

.list_div {
	width: 100%;
}

.board_list {
	list-style: none;
	margin: 50px 15px;
	display: grid;
	grid-template-columns: 230px 230px 230px;
	gap: 30px;
}

.board_list .box:hover {
	cursor: pointer;
	transform: scale(1.05);
}

.board_list .category {
	color: lightgray;
	font-size: 0.8rem;
}

.board_list .title {
	font-weight: bold;
	text-align: center;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.board_list .no {
	color: lightgray;
	text-align: center;
	font-size: 0.8rem;
}

.board_list img {
	max-width: 230px;
	height: 180px;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 위시리스트</h2>
			<div class="board_title">
				<input type="radio" class="selectType" name="selectType"
					id="roomWish" value="숙소" onclick="selectType(event)" checked>
				<label for="roomWish"> 숙소</label> <input type="radio"
					class="selectType" name="selectType" id="storeWish" value="식당"
					onclick="selectType(event)"><label for="storeWish">
					식당</label>
			</div>
			<div class="list_div" id="roomWishList">
				<ul class="board_list">
					<c:forEach var="r" items="${ roomList }">
						<li>
							<div class="box" onclick="myRoomDetail(${ r.r_no });">
								<img
									src="${ contextPath }${ r.roomimage.get(0).route }${ r.roomimage.get(0).image_r_name }">
								<p class="title">${ r.r_name }</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="list_div" id="storeWishList" style="display: none">
				<ul class="board_list">
					<c:forEach var="s" items="${ storeList }">
						<li>
							<div class="box" onclick="myStoreDetail(${ s.s_no });">
								<img
									src="${ contextPath }${ s.s_image.route }${ s.s_image.image_r_name }">
								<p class="title">${ s.s_name }</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<script>
	function selectType(event) {
		var type = event.target.value;
		if( type == '숙소'){
			$('#roomWishList').show();
			$('#storeWishList').hide();
			
		}else if(type == '식당'){
			$('#roomWishList').hide();
			$('#storeWishList').show();
		}
	}
	
	
	function myRoomDetail(r_no) {
		location.href= '${ contextPath }/booking/detail?rno=' + r_no;
	}
	function myStoreDetail(s_no) {
		location.href= '${ contextPath }/store/detail?s_no=' + s_no;
	}
	</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>