<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  
<meta charset="UTF-8">
<title></title>
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
    width: 900px;
    margin-left: 15px;
	background: rgba(254, 229, 207, 1);
	margin-top: 40px;
	margin-bottom: 20px;
	text-align: center;
	border-radius: 15px;
	height: 50px;
}

.notice_content {
	padding: 0px 20px;
}

.notice_content .subject {
    width : 900px;
	height: 800px;
	line-height: 50px;
	display: flex;
	justify-content: space-between;
	border-radius: 50px; 
	background: rgba(254, 229, 207, 1);
	
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
.textarea{
font-size: 20px;
font-weight: border;

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
					<h1>신고 상세 페이지</h1>
				</div>
				<div class="notice_content">
					
					<div class = "subject">
						<ul class = "memberinfo">
							<li>신고자 이름 : ${report.user_name }</li>
							<li>아이디 : ${report.id }</li>
							<li>신고 사유 : ${report.report_con }</li>
							<h3>신고 게시물</h3>
							<li>제목 : ${ report.b_title }</li>
							<li>번호 : ${report.b_no }</li>
							<li>내용 :</li>
							 <p><textarea class="textarea" cols = "80" rows="10" readonly="readonly">${report.b_content }</textarea></p>
						</ul>
					</div>
					<div class="btn_area">
						<button type="button" onclick="location.href='${contextPath}/admin/reportManagement'">목록으로</button>
						<button type = "button" onclick="location.href='${contextPath}/board/accompany/detail?bno='+${report.b_no}">게시물 이동</button>
						<button type = "button" onclick="deleteBoard()">게시물 삭제</button>
						<form name="boardForm" method="post">
							<input type = "hidden" id = "b_no" name = "b_no" value="${report.b_no}">												
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
		<script>
			function deleteBoard(){
				if(confirm('이 게시물을 삭제하시겠습니까?')){
				document.forms.boardForm.action="${contextPath}/admin/deleteBoard";
				document.forms.boardForm.submit();
				}
			}
		</script>
	
    
  
</body>
</html>