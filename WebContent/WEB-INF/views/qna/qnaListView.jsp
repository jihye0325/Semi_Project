<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>1:1 문의 게시판</title>
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
	  				<h1>1:1 문의게시판</h1>
					<c:if test="${ !empty loginUser }">
                    <button type="button" class="myqna" onclick="location.href='${ contextPath }/qna/myQnalist'">나의 문의글 보기</button>
					</c:if>
				</div>
				<div class="qna_list">
					<ul class="qna_header">
						<li class="id">글번호</li>
						<li class="category">분류</li>
						<li class="title">제목</li>
						<li class="writer">작성자</li>
						<li class="count">조회수</li>
						<li class="open">공개</li>
						<li class="date">작성일</li>
					</ul>
					<c:forEach var = "b" items="${ qnaList }">
					<ul class="qna_ul" onclick="detailView(${b.q_no}, '${b.id }', '${ b.open_status }')">
						<li class="id">${ b.q_no }</li>
						<li class="category">${ b.q_cname }</li>
						<li class="title">${ b.q_title } 
						<c:if test="${ b.reply_count != 0 }">
						(${ b.reply_count })
						</c:if>
						</li>
						<li class="writer">${ b.id }</li>
						<li class="count">${ b.q_count }</li>
						<li class="open">
						<c:choose>
                    	<c:when test="${ b.open_status == 'Y' }">공개</c:when>
                   	 	<c:otherwise>비공개</c:otherwise>
                    	</c:choose>
						</li>           
						<li class="date">${ b.q_create_date }</li>
					</ul>
					</c:forEach>  
				</div>
				<ul class="qna_paging">
				<%-- 검색한 화면인 경우 searchPararm 정의 --%>
				<c:if test="${!empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam"
						value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:if>
					<!-- 맨 앞으로(<<)-->
                    <li><a href="${ contextPath }/qna/list?page=1${ searchParam }">&lt;&lt;</a></li>
					
					<!-- 이전 페이지로(<) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page > 1 }">
							<a href="${ contextPath }/qna/list?page=${ pi.page -1 }${ searchParam }">&lt;</a>
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
								<a href="${ contextPath }/qna/list?page=${ p }${ searchParam }">${ p }</a>
							</c:otherwise>
						</c:choose>
					</li>			
					</c:forEach>

					<!-- 다음 페이지로(>) -->
					<li>
					<c:choose>
						<c:when test="${ pi.page < pi.maxPage }">
							<a href="${ contextPath }/qna/list?page=${ pi.page + 1}${ searchParam }">&gt;</a>
						</c:when>
						<c:otherwise>
							<a href="#">&gt;</a>
						</c:otherwise>
					</c:choose>
					</li>
					
					<!-- 맨 끝으로(>>) -->
					<li><a href="${ contextPath }/qna/list?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>
					</li>
				</ul>
			</div>
			<div class="search_area">
				<form method="get" action="${ contextPath }/qna/list">
					<select id="searchCondition" name="searchCondition">
						<option value="title" <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
						<option value="content" <c:if test="${ param.searchCondition == 'content' }">selected</c:if>>내용</option>
						<option value="writer" <c:if test="${ param.searchCondition == 'writer' }">selected</c:if>>작성자</option>
					</select> 
					<span class="input_area"> 
						<input type="search" name="searchValue" placeholder="검색어를 입력하세요" value="${ param.searchValue }">
					</span>
					<button type="submit" onclick="">검색하기</button>
					<c:if test="${ !empty loginUser }">
					<button type="button" onclick="location.href='${ contextPath }/qna/insert'">작성하기</button>
					</c:if>
				</form>
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
	
	<!-- **로그인 여부에 따라 비공개 글 조회 못하도록 script를 다르게 정의 ** -->
	<c:choose>
	<c:when test="${ !empty loginUser }">
			<script>
				function detailView(q_no, id, open_status){
					var user_id = '${ user_id }';
					if(user_id != id ){
						if(open_status == "N") {
							if(user_id == 'admin'){
								location.href = '${contextPath}/qna/detail?q_no=' + q_no;	
							} else {
								alert('작성자만 볼 수 있는 글입니다.');
								location.href = '${contextPath}/qna/list';		
							}
						} else {
							location.href = '${contextPath}/qna/detail?q_no=' + q_no;	
						}	
					} else {
						location.href = '${contextPath}/qna/detail?q_no=' + q_no;
					}
				}
			</script>
	</c:when>
	<c:otherwise>
		<script>
			function detailView(){
				alert('로그인 후 이용 가능합니다.');
				location.href = '${contextPath}/login';	
			}	
		</script>
	</c:otherwise>
	</c:choose>
	
</body>
</html>