<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
footer {
	position: relative;
	background: #A3D5DB;
	bottom: 0;
	width: 100%;
	text-align: center;
	padding: 15px 0;
	float: left;
	margin-top: 30px;
}

footer p, a {
	text-align: left;
}

.footerLogo {
	width: 80px;
	margin: 20px;
	margin-right: 100px;
	margin-left: 100px;
}
</style>

</head>
<footer id="footer">
	<img alt="logo" class="footerLogo" style="float: left;"
		src="<%=request.getContextPath()%>/resources/images/footerlogo.png">
	<div>
		<p>
			<a href="#">개인정보 처리 방침</a> | <a href="#">이용약관</a>
		</p>

		<p>
			06234 서울특별시 강남구 테헤란로10길 9 (그랑프리빌딩) 7층 | TEL. 02-0000-0000 |
			www.unijeju.com<br> &copy; UnI JeJu all rights reserved


		</p>
	</div>
</footer>
</html>