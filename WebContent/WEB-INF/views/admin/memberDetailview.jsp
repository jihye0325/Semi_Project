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
	margin-inline: auto;
	margin-top:revert; 
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
					<h1>회원 상세 조회</h1>
				</div>
				<div class="notice_content">
					
					<div class = "subject">
						<ul class = "memberinfo">
							<li>회원 번호 : ${member.user_no }</li>
							<li>아이디 : ${member.id }</li>
							<li>닉네임 : ${member.nickname }</li>
							<li>이름 : ${member.user_name }</li>
							<li>전화번호 : ${member.phone }</li>
							<li>성별 : ${member.gender }</li>
							<li>권한 : <c:set var="authority" value="${member.authority }" /> 
									<c:choose>
										<c:when test="${member.authority eq 1}">
											<a>게스트</a>
										</c:when>
										<c:when test="${member.authority eq 2}">
											<a>호스트</a>
										</c:when>
										<c:when test="${member.authority eq 3}">
											<a>관리자</a>
										</c:when>
										<c:otherwise>
											<a>없음</a>
										</c:otherwise>
							</c:choose></li>
						</ul>
					</div>
				
					<div class="btn_area">
						<button type="button" onclick="location.href='${contextPath}/admin/memberManagement'">목록으로</button>
						<button type = "button" onclick="updateAuthority()">수정</button>
						<button type = "button" onclick="deleteMember()">삭제</button>
						<form name="memberForm" method="post">
							<input type = "hidden" name = "user_no" value="${member.user_no}">							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
		<script>
			function updateAuthority(){
				document.forms.memberForm.action="${contextPath}/admin/updateAuthority";
				document.forms.memberForm.submit();
			}
			function deleteMember(){
				if(confirm('이 회원을 삭제하시겠습니까?')){
				document.forms.memberForm.action="${contextPath}/admin/deleteMember";
				document.forms.memberForm.submit();
				}
			}
		</script>
	
	
</body>
</html>