<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>나의 문의글</title>
    <link href="<%=request.getContextPath()%>/resources/css/qna/qnaList.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
	<div class="outer">
			<div class="qna_area">
		  		<div class="qna_title">
	  				<h1>나의 문의글</h1>
				</div>
				<div class="qna_list">
					<ul class="qna_header">
						<li class="id">글번호</li>
						<li class="category">분류</li>
						<li class="title">제목</li>
						<li class="writer">작성자</li>
						<li class="count">조회수</li>
						<li class="date">작성일</li>
					</ul>
					<c:forEach var = "b" items="${ qnaList }">
					<ul class="qna_ul" onclick="detailView(${b.q_no})">
						<li class="id">${ b.q_no }</li>
						<li class="category">${ b.q_cname }</li>
						<li class="title">${ b.q_title }
						<c:if test="${ b.reply_count != 0 }">
						(${ b.reply_count })
						</c:if>
						</li>
						<li class="writer">${ b.id }</li>
						<li class="count">${ b.q_count }</li>
						<li class="date">${ b.q_create_date }</li>
					</ul>
					</c:forEach>  
				</div>
				<ul class="qna_paging">
					<!-- 맨 앞으로(<<)-->
                    <li><a href="${ contextPath }/qna/myQnalist?page=1">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
							<a href="${ contextPath }/qna/myQnalist?page=${ pi.page -1 }">&lt;</a>
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
								<a href="${ contextPath }/qna/myQnalist?page=${ p }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>			
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
							<a href="${ contextPath }/qna/myQnalist?page=${ pi.page + 1}">&gt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨 끝으로(>>) -->
					<li><a href="${ contextPath }/qna/myQnalist?page=${ pi.maxPage }">&gt;&gt;</a></li>
					</li>
				</ul>
			</div>
				<div class="button_area">
					<button type="button" class="allqna" onclick="location.href='${ contextPath }/qna/list'">전체글 보기</button>
					<button type="button" class="myinsert" onclick="location.href='${ contextPath }/qna/insert'">작성하기</button>
				</div>
	</div>
	
	<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>
		
	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제거
		const boardList = document.querySelector(".qna_list");
		
		boardList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('qna_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('qna_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		boardList.addEventListener('mouseout', function(){	
			
			if(event.target.classList.contains('qna_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('qna_ul'))
				event.target.parentNode.classList.remove('onmouseover');
		});
	</script>
	<!-- **로그인 여부에 따라 script를 다르게 정의 ** -->
	<script>
		function detailView(q_no){
			location.href = '${contextPath}/qna/detail?q_no=' + q_no;
		}
	</script>
</body>
</html>