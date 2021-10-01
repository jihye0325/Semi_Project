<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous"></script>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/banner.jsp"%>
<style>
.msgBoard {
	list-style: none;
	padding: 0;
	margin: 0;
}

.msgBoard li {
	font-size: 18px;
}

.msgBoard>ul {
	justify-content: space-around;
	display: flex;
	font-weight: bold;
	line-height: 30px;
	padding-left: 15px;
	list-style: none;
}

.msgBoard ul:nth-of-type(2n-1) {
	background-color: rgba(254, 229, 207, 1);
	border-radius: 15px;
}

.msgBoard .msg_writer {
	width: 15%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	margin-left: 15px;
}

.msgBoard .msg_title {
	width: 20%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.msgBoard .msg_content {
	width: 40%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.msgBoard .msg_date {
	width: 15%;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

.msgBoard .msg_delete {
	width: 10%;
	text-align: center;
}

.msgBoard .msg_delete button {
	display: inline-block;
	border-radius: 4px;
	background-color: #FFBF84;
	border: none;
	color: black;
	text-align: center;
	font-size: 15px;
	padding: 5px;
	transition: all 0.5s;
	cursor: pointer;
	margin: 5px;
}

.board_title {
	margin-top: 20px;
	text-align: left;
	display: inline-block;
	border-radius: 4px;
	border: none;
	color: black;
	font-size: 16px;
	padding: 10px;
	width: 100%;
	transition: all 0.5s;
	cursor: pointer;
}

.board_ul {
	list-style: none;
	display: flex;
	margin: 0;
}

.board_ul li {
	font-weight: normal;
}

.board_paging {
	height: 50px;
	line-height: 50px;
	display: flex;
	justify-content: center;
	list-style: none;
	width: 600px;
	margin: auto;
}

.board_paging a {
	text-decoration: none;
	color: #282A35;
	width: 40px;
	display: block;
	text-align: center;
}

.board_paging li {
	margin: 6px;
	font-size: 18px;
	text-align: center;
}

.pages {
	border-radius: 4px;
	background-color: #ffffff;
}

.board_paging a.current_page {
	border-bottom: 2px solid orange;
	font-weight: bold;
	color: orange;
}

.btn_area {
	display: flex;
	justify-content: flex-end;
}

.btn_area input[type=button] {
	height: 26px;
	width: 80px;
	border-style: none;
	background-color: rgba(255, 191, 132, 1);
	border-radius: 5px;
	margin-left: 5px;
}

.msg_detail {
	padding: 0px;
	margin: 0px;
	height: 100%;
}

.msg_detail>button {
	font-size: 18px;
	padding: 0px;
	margin: 0px;
	border: 0px;
	background: none;
	cursor: pointer;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 쪽지 관리</h2>
			<div class="board_title">
				<input type="radio" class="selectType" name="selectType"
					id="roomWish" value="받은쪽지" onclick="selectType(event)" checked>
				<label for="roomWish"> 받은 쪽지</label> <input type="radio"
					class="selectType" name="selectType" id="storeWish" value="보낸쪽지"
					onclick="selectType(event)"><label for="storeWish">
					보낸 쪽지</label>
			</div>
			<div class="board_page" id="receiveList">
				<div class="msgBoard">
					<ul class="board_header">
						<li class="msg_writer">보낸사람</li>
						<li class="msg_title">제목</li>
						<li class="msg_content">내용</li>
						<li class="msg_date">작성일</li>
						<li class="msg_delete"></li>
					</ul>
					<c:forEach var="r" items="${ messageReceiveList }">
						<ul class="board_ul">
							<li class="msg_writer">${ r.senderName }</li>
							<li class="msg_title msgView" id="msgtitle">${ r.msgTitle }</li>
							<li class="msg_content msgView">
								<form name="msg_receive" class="msg_detail" action=""
									method="post">
									<button type="button"
										onclick="openReceive(${ r.msgNo }, ${ fn:length(messageReceiveList) })">${ r.msgContent }</button>
									<input type="hidden" id="msgNo" name="msgNo"
										value="${ r.msgNo }"> <input type="hidden"
										name="sender" value="${ r.senderName}"> <input
										type="hidden" name="title" value="${ r.msgTitle }"> <input
										type="hidden" name="content" value="${ r.msgContent}">
								</form>
							</li>
							<li class="msg_date">${ r.msgDate }</li>
							<li class="msg_delete">
								<form action="${ contextPath }/mypage/msgdelete">
									<button class="msg_delete_btn">삭제</button>
									<input type="hidden" name="msgNo" value="${ r.msgNo }">
								</form>
							</li>

						</ul>
					</c:forEach>
				</div>

				<ul class="board_paging">
					<!-- 맨 처음으로 -->
					<li><a href="${ contextPath }/mypage/mymessage?page=1">&lt;&lt;</a></li>
					<!-- 이전 페이지로 -->
					<li><c:choose>
							<c:when test="${ r_pi.page > 1 }">
								<a
									href="${ contextPath }/mypage/mymessage?page=${ r_pi.page - 1}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&lt;</a>
							</c:otherwise>
						</c:choose></li>

					<!-- 페이지 목록 -->
					<c:forEach var="p" begin="${ r_pi.startPage }"
						end="${ r_pi.endPage }">
						<li><c:choose>
								<c:when test="${ p eq r_pi.page }">
									<a href="#" class="current_page">${ p }</a>
								</c:when>
								<c:otherwise>
									<a class="pages"
										href="${ contextPath }/mypage/mymessage?page=${ p }">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 데이지로 -->
					<li><c:choose>
							<c:when test="${ r_pi.page < r_pi.maxPage }">
								<a
									href="${ contextPath }/mypage/mymessage?page=${ r_pi.page + 1}">&gt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&gt;</a>
							</c:otherwise>
						</c:choose></li>

					<!-- 맨 끝으로 -->
					<li><a
						href="${ contextPath }/mypage/mymessage?page=${ r_pi.maxPage }">&gt;&gt;</a></li>
				</ul>
			</div>
			<script> 
				function openReceive(selectmsg, size){
					var left = (document.body.clientWidth/2) - 250;
						left += window.screenLeft;
					var top = (screen.availHeight/2) - 250;
					    top += window.screenTop;
					    
				    var f = document.msg_receive;
				    
				    if(size>1){
				    	for(var li of f){
				    		if(li[1].value == selectmsg){
				    			f = li;
				    		}
				    	}
				    }
				    
				    window.open('about:blank','message','width=500,height=500,left='+left+',top='+top);
				    
				    f.action = '${ contextPath }/messagedetail';
				    f.target ='message';
				    f.method ='post';
				    
				    f.submit();
			    } 
			</script>
			<div class="board_page" id="sendList" style="display: none">
				<div class="msgBoard">
					<ul class="board_header">
						<li class="msg_writer">받는사람</li>
						<li class="msg_title">제목</li>
						<li class="msg_content">내용</li>
						<li class="msg_date">작성일</li>
						<li class="msg_delete"></li>
					</ul>
					<c:forEach var="m" items="${ messageSendList }">
						<ul class="board_ul">
							<li class="msg_writer">${ m.senderName}</li>
							<li class="msg_title msgView" id="msgtitle">${ m.msgTitle }</li>
							<li class="msg_content msgView">
								<form name="msg_send" class="msg_detail" action=""
									method="post">
									<button type="button"
										onclick="openSend(${ m.msgNo }, ${ fn:length(messageSendList) })">${ m.msgContent }</button>
									<input type="hidden" id="msgNo" name="msgNo"
										value="${ m.msgNo }"> <input type="hidden"
										name="sender" value="${ m.senderName}"> <input
										type="hidden" name="title" value="${ m.msgTitle }"> <input
										type="hidden" name="content" value="${ m.msgContent}">
								</form>
							</li>
							<li class="msg_date">${ m.msgDate }</li>
							<li class="msg_delete"><form
									action="${ contextPath }/mypage/sendmsgdelete">
									<button class="msg_delete_btn">삭제</button>
									<input type="hidden" name="msgNo" value="${ m.msgNo }">
								</form></li>
						</ul>
					</c:forEach>
				</div>
				<script> 
				function openSend(selectmsg, size){
					var left = (document.body.clientWidth/2) - 250;
						left += window.screenLeft;
					var top = (screen.availHeight/2) - 250;
					    top += window.screenTop;
					    
				    var f = document.msg_send;
				    
				    if(size>1){
				    	for(var li of f){
				    		if(li[1].value == selectmsg){
				    			f = li;
				    		}
				    	}
				    }
				    window.open('about:blank','message','width=500,height=500,left='+left+',top='+top);
				    f.action = '${ contextPath }/sendmessagedetail';
				    f.target ='message';
				    f.method ='post';
				    
				    f.submit();
			    } 
			</script>
				<ul class="board_paging">
					<!-- 맨 처음으로 -->
					<li><a href="${ contextPath }/mypage/mymessage?page=1">&lt;&lt;</a></li>
					<!-- 이전 페이지로 -->
					<li><c:choose>
							<c:when test="${ r_pi.page > 1 }">
								<a
									href="${ contextPath }/mypage/mymessage?page=${ r_pi.page - 1}">&lt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&lt;</a>
							</c:otherwise>
						</c:choose></li>

					<!-- 페이지 목록 -->
					<c:forEach var="p" begin="${ r_pi.startPage }"
						end="${ r_pi.endPage }">
						<li><c:choose>
								<c:when test="${ p eq r_pi.page }">
									<a href="#" class="current_page">${ p }</a>
								</c:when>
								<c:otherwise>
									<a class="pages"
										href="${ contextPath }/mypage/mymessage?page=${ p }">${ p }</a>
								</c:otherwise>
							</c:choose></li>
					</c:forEach>

					<!-- 다음 데이지로 -->
					<li><c:choose>
							<c:when test="${ r_pi.page < r_pi.maxPage }">
								<a
									href="${ contextPath }/mypage/mymessage?page=${ r_pi.page + 1}">&gt;</a>
							</c:when>
							<c:otherwise>
								<a href="#">&gt;</a>
							</c:otherwise>
						</c:choose></li>

					<!-- 맨 끝으로 -->
					<li><a
						href="${ contextPath }/mypage/mymessage?page=${ r_pi.maxPage }">&gt;&gt;</a></li>
				</ul>
			</div>

		</div>




	</div>
	<script>
		const messageList = document.querySelector(".msgBoard");
		messageList.addEventListener('mouseover', function() {
			if (event.target.classList.contains('msgView'))
				event.target.classList.add('onmouseover');
			else if (event.target.parentNode.classList.contains('msgView'))
				event.target.parentNode.classList.add('onmouseover');
		});
		messageList.addEventListener('mouseout', function() {
			if (event.target.classList.contains('msgView'))
				event.target.classList.remove('onmouseover');
			else if (event.target.parentNode.classList.contains('msgView'))
				event.target.parentNode.classList.remove('onmouseover');
		});
		function selectType(event) {
			var type = event.target.value;
			console.log(type);
			if (type == '받은쪽지') {
				$('#receiveList').show();
				$('#sendList').hide();

			} else if (type == '보낸쪽지') {
				$('#receiveList').hide();
				$('#sendList').show();
			}
		}
	</script>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>