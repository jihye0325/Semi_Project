<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
.outer {
	margin: auto;
	display: flex;
	justify-content: center;
}

.wrap {
	
	width: 900px;
	}

.notice_title {
	background: rgba(254, 229, 207, 1);
	margin-bottom: 40px;
	text-align: center;
	border-radius: 15px;
	height: 50px;
}

.notice_content {
	padding: 0px 20px;
}

.notice_content .subject {
	background: rgba(254, 229, 207, 1);
	height: 500px;
	line-height: 50px;
	display: flex;
	justify-content: space-between;
	
	border-radius: 50px; 
	
}

.notice_content .content {
	height: 500px;
	overflow: auto;
	margin-bottom: 30px;
}

.title_span {
	background-color: #282A35;
}

.notice_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
}

.btn_area {
	text-align: center;
	padding: 30px;
}
.memberinfo{
	font-size: 20px;
	font-weight: bolder;
}

</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
	
	<div class="outer">
	<%@ include file="/WEB-INF/views/admin/aside.jsp"%>
		<div class="wrap">
			<div class="notice_area">
				<div class="notice_title">
					<h1>숙소 상세정보</h1>
				</div>
				<div class="notice_content">
					
					<div class = "subject">
						<h3>아직 매출 현황이 없습니다.</h3>
					</div>
				

				
					
					<div class="btn_area">
						<button type="button" onclick="location.href='${contextPath}/admin/bookingManagement'">목록으로</button>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
		<script>
			
			function deleteMember(){
				if(confirm('이 회원을 삭제하시겠습니까?')){
				document.forms.memberForm.action="${contextPath}/admin/deleteMember";
				document.forms.memberForm.submit();
				}
			}
		</script>
	
	
</body>
</html>