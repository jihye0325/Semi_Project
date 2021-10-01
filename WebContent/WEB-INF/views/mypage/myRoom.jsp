<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/banner.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<style>
.board_title {
	text-align: right;
}

.board_select {
	text-align: left;
}

.addRoom {
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

.addRoom span {
	cursor: pointer;
	display: inline-block;
	position: relative;
	transition: 0.5s;
}

.addRoom span:after {
	content: '\00bb';
	position: absolute;
	opacity: 0;
	top: 0;
	right: -20px;
	transition: 0.5s;
}

.addRoom:hover span {
	padding-right: 25px;
}

.addRoom:hover span:after {
	opacity: 1;
	right: 0;
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

.confirmBtn {
	display: flex;
	text-align: center;
}

.confirmBtn form {
	margin: auto;
}

.confirmBtn form button {
	background: #FFBF84;
	border: none;
	color: black;
	padding: 10px;
	font-size: 16px;
	border-radius: 5px;
	opacity: 0.6;
	transition: 0.4s;
	display: inline-block;
	text-decoration: none;
	cursor: pointer;
	font-size: 16px;
}

.confirmBtn>form>button:hover {
	opacity: 1;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 숙소 관리</h2>
			<div class="board_title">
				<input type="radio" class="selectType" name="selectType"
					id="roomWish" value="숙소" onclick="selectType(event)" checked>
				<label for="roomWish"> 숙소</label> <input type="radio"
					class="selectType" name="selectType" id="storeWish" value="예약요청"
					onclick="selectType(event)"><label for="storeWish">
					예약 요청</label>
				<button class="addRoom"
					onclick="location.href='${ contextPath }/mypage/roominsert'">
					<span>숙소 등록</span>
				</button>
			</div>
			<div class="list_div" id="room">
				<ul class="board_list">
					<c:forEach var="r" items="${ roomList }">
						<li onclick="myRoomDetail(${ r.r_no });">
							<div class="box" onclick="detailView(${ r.r_no })">
								<img
									src="${ contextPath }${ r.roomimage.get(0).route }${ r.roomimage.get(0).image_r_name }">
								<p class="no">${ r.r_no }</p>
								<p class="title">${ r.r_name }</p>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="list_div" id="request" style="display: none;">
				<ul class="board_list">
					<c:forEach var="request" items="${ requestList }">
						<li onclick="myRoomDetail(${ request.r_no });">
							<div class="box" onclick="detailView(${ request.r_no })">
								<img
									src="${ contextPath }${ request.roomimage.get(0).route }${ request.roomimage.get(0).image_r_name }">
								<p class="no">${request.r_name }</p>
								<p class="title">요청자 : ${ request.guest_name }</p>
							</div>
							<div class="confirmBtn">
								<form action="${ contextPath }/mypage/requestconfirm"
									method="post">
									<button>요청 확인</button>
									<input type="hidden" name="booking_no"
										value="${ request.booking_no }">
								</form>
								<form action="${ contextPath }/mypage/requestcancle"
									method="post">
									<button>요청 거절</button>
									<input type="hidden" name="booking_no"
										value="${ request.booking_no }">
								</form>
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
		console.log(type);
		if( type == '숙소'){
			$('#room').show();
			$('#request').hide();
			
		}else if(type == '예약요청'){
			$('#room').hide();
			$('#request').show();
		}
	}
	function myRoomDetail(r_no) {
		location.href= '${ contextPath }/booking/detail?rno=' + r_no;
	}
	</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>