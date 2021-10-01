<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 모아보기</title>
<style>
	.outer {
		margin: auto;
	    display: flex;
	    justify-content : center;
	}
	
	.article_outer {
		width: 1100px;
	    min-height: 700px;
	}
	
	.board_title {
	    margin-bottom: 100px;
	    text-align: center;
	}
	
	article {
		width: 800px;
		margin : 0 auto;
	}
	
	.board_title h1 {
		font-size: 32px;
	}
	
	.search {
		display: flex;
		justify-content: flex-end;
		margin: 0 0 15px 0;
	}
	
	form input[type=search] {
		height: 22px;
		width: 185px;
		border-style: none;
		border: 1px solid rgba(84, 84, 84, 1);
		border-radius: 5px;
	}
	
	form input[type=submit] {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border-radius: 5px;
		margin-left: 5px;
	}	
		
	.grid {
		width: 50%;
		display: grid;
		position: relative;
		grid-template-columns: 340px 340px 340px;
		row-gap: 20px;
        column-gap: 36px;
	}
	.grid-item {
		border: 1px solid #dfdfdf;
		border-radius: 5px;
	}
	.info {
		display: flex;
		justify-content: space-between;
	}
	.info span,
	.title span {
		margin: 5px;
	}
	.title {
		border-bottom: 1px solid #dfdfdf;	
	}
	.thumbnail {
		width: 90%;
		margin: auto;
		overflow: hidden;
		padding: 15px 5px;
	}
	.thumbnail img {
		width: 100%;
		height: 100%;
		object-fit: contain;
	}
	.tag {
		padding: 10px
	}
	.tag a {
		margin : 5px;
		color: #0095ff;
	}
	.tag a:hover {
		text-decoration: underline;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<div class="outer">
		<article class="article_outer">
			<div class="board_title">
				<h1>#사람들과 공유하고 싶은 사진</h1>	
			</div>
			<div class="search">
				<form method="get" action="${ contextPath }/board/gallery/list">
					<input type="search" name="searchValue" placeholder="키워드 입력" value="${ param.searchValue }">
					<input type="submit" value="검색">
				</form>
			</div>
			<div class="grid">
 				<c:forEach var="b" items="${ bList }">
					<div class="grid-item">
						<div class="info">
							<span class="writer">${ b.bWriter }</span>
							<span class="bcount">조회수 : ${ b.bCount }</span>						
						</div>
						<div class="title">
							<span style="font-size: 20px;">${ b.bTitle }</span>
						</div>
						<div class="thumbnail">
							<img src="${ contextPath }${ b.imgList[0].route }${ b.imgList[0].reName }">
						</div>
						<div class="tag">
							<c:set var="tags">${ b.bTag }</c:set>
							<c:forTokens var="t" items="${ tags }" delims=",">
								<a class="a_tag">#${ t }</a>
							</c:forTokens>
						</div>
					</div>
 				</c:forEach>
			</div>
		</article>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
	
	<script>
		$(".a_tag").click(function() {
			var searchValue = $(this).html().substr(1, );
			$("input[type=search]").val(searchValue);
			$("input[type=submit]").click();
		});
	</script>
</body>
</html>