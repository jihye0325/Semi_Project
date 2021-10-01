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
	text-align: right;
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

.board_list .date {
	color: black;
	text-align: center;
	font-size: 0.8rem;
}

.confirmText {
	color: lightgreen;
	text-align: center;
	font-size: 1.2rem;
}

.rejectText {
	color: red;
	text-align: center;
	font-size: 1.2rem;
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
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 예약 확인</h2>
			<div class="list_div">
				<ul class="board_list">
					<c:forEach var="r" items="${ roomList }">
						<li>
							<div class="box" onclick="myRoomDetail(${ r.r_no });">
								<img
									src="${ contextPath }${ r.roomimage.get(0).route }${ r.roomimage.get(0).image_r_name }">
								<p class="title">${ r.r_name }(${ r.hostName })</p>
								<p class="date">${ r.r_start }~${r.r_end }</p>
							</div> <c:choose>
								<c:when test="${r.host_confirm eq 'Y' }">
									<div class="confirmBtn">
										<c:choose>
											<c:when test="${ r.r_status eq 'Y'}">
												<p class="confirmText">예약 확정</p>
											</c:when>
											<c:otherwise>
												<form action="${ contextPath }/mypage/bookingconfirm"
													method="post">
													<button>예약 확정</button>
													<input type="hidden" name="booking_no"
														value="${ r.booking_no }">
												</form>
											</c:otherwise>
										</c:choose>
										<form action="${ contextPath }/mypage/bookingcancle"
											method="post">
											<button>예약 취소</button>
											<input type="hidden" name="booking_no"
												value="${ r.booking_no }">
										</form>
									</div>
								</c:when>
								<c:when test="${r.host_confirm eq 'N' }">
									<div class="confirmBtn">
										<p class="rejectText">요청 거절</p>
										<form action="${ contextPath }/mypage/bookingcancle"
											method="post">
											<button>예약 취소</button>
											<input type="hidden" name="booking_no"
												value="${ r.booking_no }">
										</form>
									</div>
								</c:when>
								<c:otherwise>
									<p class="confirmText">요청 확인을 대기중</p>
								</c:otherwise>
							</c:choose>
						</li>
					</c:forEach>
				</ul>
			</div>

		</div>
	</div>
	<script>
	function myRoomDetail(r_no) {
		location.href= '${ contextPath }/booking/detail?rno=' + r_no;
	}
	</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>