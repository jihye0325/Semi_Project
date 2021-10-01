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
	border-bottom: 1px solid #282A35;
}

.board_content {
	width: 80%;
	padding: 5%;
	background: rgba(254, 229, 207, 1);
	border-radius: 15%;
}

.board_content .content {
	padding: 10px 0px 10px 100px;
}

.input_area {
	padding: 10px 10px 14px 10px;
	background: white;
	border-style: inset;
}

.input_area input {
	width: 80%;
	height: 30px;
	border: 0px
}

.input_area input:focus, .input_area select:focus {
	outline: none;
}

.textarea {
	resize: none;
	border: solid 1px #dadada;
}

.textarea:focus {
	outline: none;
}

.title_span {
	background-color: #282A35;
}

.image_area {
	text-align: center;
}

.image_area img {
	margin: 20px;
	width: 100%;
}

.board_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
	cursor: pointer;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}

.room_detail {
	width: 100%;
}

.btn_area>button {
	width: 150px;
	height: 55px;
	border: none;
	border-radius: 5px;
	background: #FFBF84;
	margin: 30px;
	display: inline-block;
	cursor: pointer;
	outline: none;
	box-shadow: 0 7px #FFBF84;
}

.btn_area>button:hover {
	background: #FFBF84;
	box-shadow: 0 5px #FFBF84;
}

.btn_area>button:active {
	background: #FFBF84;
	box-shadow: 0 5px #FFBF84;
	transform: translateY(5px);
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="mypage_wrapper">
		<%@ include file="/WEB-INF/views/mypage/mypage_aside.jsp"%>
		<div class="mypageMain">
			<h2>마이페이지 > 숙소 관리</h2>
			<div class="board_content">
				<form method="post" action="${ contextPath }/mypage/roominsert"
					enctype="multipart/form-data">
					<!-- 파일 업로드를 위해서는 반드시 enctype을 지정해주어야함
						[참조]
						application/x-www-form-urlencoded
						: 기본값. 모든 문자들을 서버로 보내기 전 인코딩 됨.
						multipart/form-data
						: 모든 문자를 인코딩하지 않음. 파일 전송 시 사용.
						text/plain
						: 공백 문자만 변환하고 나머지 문자는 인코딩 되지 않음
					 -->
					<div class="content">

						<h4>
							<span class="title_span">&nbsp;</span> 숙소 이름
						</h4>
						<span class="input_area"> <input type="text" name="title"
							required>
						</span>
						<h4>
							<span class="title_span">&nbsp;</span> 주소
						</h4>
						<span class="input_area"> <input type="text" name="address"
							required>
						</span>
						<div class="room_detail" style="display: flex;">
							<div style="width: 29%">
								<h4>
									<span class="title_span">&nbsp;</span> 전화번호
								</h4>
								<span class="input_area"> <input type="tel" name="tel"
									required>
								</span>
							</div>
							<div style="width: 29%">
								<h4>
									<span class="title_span">&nbsp;</span> 가격
								</h4>
								<span class="input_area"> <input type="number" name="pay"
									required>
								</span>
							</div>
							<div style="width: 27%">
								<h4>
									<span class="title_span">&nbsp;</span> 인원
								</h4>
								<span class="input_area"> <input type="number" name="max_people"
									required>
								</span>
							</div>
						</div>
						<div class="room_detail" style="display: flex;">
							<div style="width: 44%">
								<h4>
									<span class="title_span">&nbsp;</span> 시작 날짜
								</h4>
								<span class="input_area"> <input type="date" name="start"
									required>
								</span>
							</div>
							<div style="width: 44%">
								<h4>
									<span class="title_span">&nbsp;</span> 종료 날짜
								</h4>
								<span class="input_area"> <input type="date" name="end"
									required>
								</span>
							</div>
						</div>

						<h4>
							<span class="title_span">&nbsp;</span> 내용
						</h4>
						<textarea class="textarea" rows="20" cols="100" name="content"
							style="width: 80%; font-size: 25px;" required></textarea>

						<h4>
							<span class="title_span">&nbsp;</span> 대표 이미지 첨부
						</h4>

						<div class="image_area"></div>

						<input type="file" name="thumbnail"
							accept="image/gif, image/jpeg, image/png" required>

						<h4>
							<span class="title_span">&nbsp;</span> 추가 이미지 첨부(최대 3개) <!-- 수정 -->
						</h4>

						<div class="image_area"></div>
						<div class="image_area"></div>
						<div class="image_area"></div> <!-- 수정 -->

						<input type="file" name="contentImg1"
							accept="image/gif, image/jpeg, image/png" required> <input
							type="file" name="contentImg2"
							accept="image/gif, image/jpeg, image/png" required> <input
							type="file" name="contentImg3"
							accept="image/gif, image/jpeg, image/png" required> <!-- 수정 -->


					</div>
					<div class="btn_area">
						<button type="button">목록으로</button>
						<button type="submit">작성하기</button>
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
</html>