<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행후기</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<style>
	html { scroll-behavior: smooth; /* 부드러게 */}
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
	
	.subject {
		background-color: rgb(254,229,207);
		border-radius: 5px;
		padding: 15px;
		display: flex;
		justify-content: space-between;
		margin-bottom: 15px;
	}
	
	#category,
	#title {
		font-size: 24px;
		line-height: 35px;
	}
	.writer span {
		margin-right: 15px;
		line-height: 30px;
	}

	.board-info {
		display: flex;
		align-items: flex-end;
	}
	.attachmentView,
	.attachmentList {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	.attachmentList p {
		font-size: 15px;
	}
	.content-outer,
	.cmtArea {
		border-top: 2px solid #ffbf84;
		border-bottom: 2px solid #ffbf84;
		padding: 15px;	
		margin-bottom: 15px;
	}

	.options {
		display: flex;
		align-items: center;
	}
	
	h4 {
		font-size: 20px;
		font-weight: bold;
	}
	li {
		list-style: none;
	}
	.options h4 {
		padding-right: 15px;
		border-right: 2px solid #ffbf84;
		margin-right: 15px;
	}
	.attachmentView img {
		width: 100%;
	}
	.btnArea {
		display: flex;
		justify-content: flex-end;
		margin-bottom: 15px;
	}
	
	.board-info a {
		text-decoration: none;
		padding: 5px;
		cursor: pointer;
	}
	
	.btnArea input[type=button],
	.cmt_write button,
	.writer-info input[type=button] {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border: 2px solid #ffbf84;
		border-radius: 5px;
		margin-left: 5px;
		cursor: pointer;
	}
	.cmt_outer {
		display: flex;
		justify-content: space-between;
		padding: 10px 0 15px 0;
		border-bottom: 1px solid lightgray;
	}
	.cmt_outer:last-child {
		border: none;
	}
	.cmt_write,
	.reCmt_write {
		display: flex;
		justify-content: space-between;
		align-items: flex-end;
	}
	.cmt_content,
	.reCmt_content {
		width: 1000px;
		height : 50px;
		resize: none;
		margin-right: 15px;
	}
	.writer_info {
		display: flex;
	}
	.writer_info li {
		padding-right: 30px;
	}
	.cwriter {
		font-size: 16px;
		font-weight: bold;
		border-right: 2px solid #ffbf84
	}
	.cdate {
		padding-left: 15px;
	}
	.ccontent {
		margin-left: 40px;
	}
	.cmtLengthArea,
	.reCmtLength {
		text-align: center;
		margin-bottom: 10px;
	}
	.cmt_btn {
		display: flex;
	}
	.cmt_btn li {
		padding-left: 15px;
	}
		#edit_outer {
		width : 1000px;
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 10px;
	}
	#editCmt {
 		margin-left: 40px;
		width: 800px;
		height : 50px;
	}
	#editBtn,
	#editEndBtn {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border: 2px solid #ffbf84;
		border-radius: 5px;
		margin-left: 5px;
		cursor: pointer;
	}
	.rePopup-wrap {
		background-color: rgba(0, 0, 0, 0.5);
		display: none;
		justify-content: center;
		align-items: center;
		position: fixed;
		z-index: 5;
		top: 0;
		left: 0;
		right: 0;
		bottom: 0;
		padding: 15px;
		backdrop-filter: blur(4px);
	}
	.reCmt_write {
		margin-top: 10px;
	}
	.reCmt_write button {
		height: 26px;
		width: 80px;
		border-style: none;
		background-color: rgba(255, 191, 132, 1);
		border: 2px solid #ffbf84;
		border-radius: 5px;
		margin-left: 5px;
		cursor: pointer;
	}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside1.jsp" %>
	<%@ include file="/WEB-INF/views/common/aside2.jsp" %>
	<%@ include file="/WEB-INF/views/board/reportView.jsp" %>
	<div class="outer">
		<article class="article_outer">
			<div class="board_title">
				<h1>#자유롭게 여행 이야기를 공유하세요</h1>			
			</div>
			<div id="insertBoardArea">
			<input type="hidden" value="${ board.bno }" id="bno">
				<div class="subject">
					<div class="writer-info">
						<span id="category">[${ board.bType }]</span>
						<span id="title">${ board.bTitle }</span>
						<div class="writer">
						<span>작성자 : ${ board.bWriter }</span>
						</div>
						<span>작성일 : <fmt:formatDate value="${ board.createDate }" pattern="yyyy.MM.dd HH:mm:ss"/></span><br>
						<span>수정일 : <fmt:formatDate value="${ board.modifyDate }" pattern="yyyy.MM.dd HH:mm:ss"/></span>
					</div>
					<div class="board-info">
						<a href="#cmtArea">댓글 ${ board.cmtList.size() }</a>
					</div>
				</div>
				<div class="content-outer">
					<div class="attachmentListArea">
						<h4>사진</h4>
						<div class="attachmentView">
						<!-- 이미지 클릭하면 원본 보기 -->
						<c:if test="${ board.imgList != null }">
							<c:forEach items="${ board.imgList }" var="i">
								<div class="attachmentList">
									<img src="${ contextPath }${ i.route }${ i.reName }">
									<p>${ i.originName }</p>
									<!-- <button onclick="" type="hidden"> 원본보기용 버튼 -->
								</div>
							</c:forEach>						
						</c:if>	
						</div>
					</div>
					<div class="contentArea">
						<h4>내용</h4>
						<pre class="content">${ board.bContent }</pre>
					</div>
				</div>
				<div class="btnArea">
 					<c:choose>
						<c:when test="${ loginUser.userNo == board.userNo }">
							<input type="button" value="수정하기" onclick="updateBoard();">
							<input type="button" value="삭제하기" style="background-color:#ffffff;" onclick="deleteBoard();">	
							<form name="boardForm" method="post">
								<input type="hidden" name="bno" value="${ board.bno }">
								<input type="hidden" name="bid" value="${ board.bid }">
								<c:forEach items="${ board.imgList }" var="i">
								<input type="hidden" name="changeName" value="${ i.reName }">
								</c:forEach>
							</form>						
  						</c:when>
						<c:when test="${ loginUser.userNo != board.userNo }">
							<input type="button" value="신고하기" style="background-color:#ffffff;"" onclick="showReport();">
							<form name="reportForm" method="post">
								<input type="hidden" name="reportUserNo" value="1">
							</form>
						</c:when>
					</c:choose>
				</div>
				<div class="cmtArea" id="cmtArea">
					<h4>댓글</h4>
					<div class="cmt_list">
   					<c:forEach items="${ board.cmtList }" var="c">
					<div class="cmt_outer" <c:if test="${ c.originCid ne 0 }">style="padding-left:60px;"</c:if>>
						<div class="cmt_text">
							<ul class="writer_info">
								<li class="cwriter">${ c.nickname }</li>						
								<li class="cdate">
								<fmt:formatDate value="${ c.modifyDate }" pattern="yyyy.MM.dd HH:mm:ss"/></li>						
							</ul>
							<div class="ccontent">${ c.cContent }</div>
						</div>
						<div>
							<input type="hidden" value="${ c.cid }" name="cid">
							<ul  class="cmt_btn">
								<c:if test="${ loginUser.userNo != c.cWriter }">
								<li id="insertReCmt">답글</li>
								</c:if>
								<c:if test="${ loginUser.userNo == c.cWriter }">
								<li id="updateCmt">수정</li>
								<li id="deleteCmt">삭제</li>
								</c:if>
							</ul>
						</div>
					</div>
					</c:forEach>
					</div>
				</div>
				<div class="cmt_write">
					<textarea class="cmt_content"></textarea>
					<div>
					<div class="cmtLengthArea"><span id="cmtLength">0</span>&nbsp;/&nbsp;200자</div>
					<button onclick="addCmt(${ board.bno });">댓글등록</button>
					</div>
				</div>
			</div>		
		</article>
	</div>
	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
 	<c:if test="${ board.userNo == loginUser.userNo }">
	<script>
		function deleteBoard() {
			if(confirm("이 게시글을 삭제하시겠습니까?")){
				document.forms.boardForm.action = "${ contextPath }/board/delete";
				document.forms.boardForm.submit();
			}
		}
		
		function updateBoard() {
			if(confirm("이 게시글을 수정하시겠습니까?")) {
				document.forms.boardForm.action = "${ contextPath }/board/review/updateView";
				document.forms.boardForm.submit();
			}
		}
	</script>
 	</c:if>
	<script>
		// 댓글 작성
		function addCmt(bno) {
			// console.log("${ contextPath }");
			if($(".cmt_content").val().length < 2) {
				alert("댓글 작성은 최소 2자 이상 입력이 필수입니다.");
				return;
			}
			$.ajax({
				url : "${ contextPath }/board/insertCmt",
				type : "post",
				data : { bno : bno, content : $(".cmt_content").val()},
				dataType : "json",
				success : function(data) {
					if(data != null){
						// console.log(data);
						alert('댓글이 작성되었습니다.');
						reloadCmt(data);
					} else {
						alert('댓글 입력 실패!');
					}
				},
				error : function(e) {
					console.log(e);
				}
			});
		}
		
		// 댓글 reload
		function reloadCmt(data){
			var html = '';
			for(var key in data) {
				if(data[key].originCid) {
					console.log(data[key].originCid + "있음");
					html += '<div class="cmt_outer" style="padding-left: 60px;">';
				} else {
					console.log(data[key].originCid + "없음");
					html += '<div class="cmt_outer">';
				}
				html += '<div class="cmt_text"><ul class="writer_info"><li class="cwriter">' 
					  + data[key].nickname + '</li><li class="cdate">' + data[key].modifyDate 
					  + '</li></ul><div class="ccontent">' + data[key].cContent + '</div></div>'
					  + '<div><input type="hidden" value="' + data[key].cid + '" name ="cid">'
					  + '<ul class="cmt_btn">'
				if("${ loginUser.userNo }" == data[key].cWriter){
					html += '<li id="updateCmt">수정</li><li id="deleteCmt">삭제</li></ul></div></div>'
				} else{
					html += '<li>답글</li></ul></div></div>'
				}
			}
			$(".cmt_list").html(html);
			$(".cmt_content").val("");
		}
		
		// 댓글 수정을 위한 요소 생성
		var editOuter = document.createElement('div');
		editOuter.id = 'edit_outer';
		var editCmt = document.createElement('textarea');
		var editBtn = document.createElement('button');
		var editResult;
		var originCmt;
		editCmt.id = 'editCmt';
		editBtn.id = 'editBtn';
		editBtn.type = 'button';
		editBtn.textContent = '수정완료';
		editOuter.append(editCmt);
		editOuter.append(editBtn);
		var originBtn;
		
		// 댓글영역 버튼 이벤트(수정/삭제)
		$(document).on('click', ".cmt_btn", function(e){
			if(e.target.id == 'deleteCmt') {
				var cid = $(this).prev().val();
				deleteReply(cid);
			} else if(e.target.id == 'updateCmt') {
 				if(editCmt.textContent != null) {
					editCmt.textContent = originCmt;
					editOuter.replaceWith(editResult);
					// originBtn이 undefined가 아닌 경우 다시 보이도록
					// null, undefined, 0 은 false
					if(originBtn){
						originBtn.style.visibility = 'visible';
					}
				}
				editCmt.value = e.currentTarget.parentElement.parentElement.firstElementChild.lastElementChild.innerHTML;
				editResult = e.currentTarget.parentElement.parentElement.firstElementChild.lastElementChild;
				originCmt = editResult.value;
				originBtn = e.currentTarget;
				originBtn.style.visibility = 'hidden';
				editResult.replaceWith(editOuter);
			}  else if(e.target.id == 'insertReCmt') {
				var parentEl = e.currentTarget.parentElement.parentElement;
				var parentCid = $(this).prev().val();
				// console.log(parentEl);
				// console.log(parentCid);
				insertReCmt(parentEl, parentCid);
			}
		});
		
		// 댓글 삭제
		function deleteReply(cid) {
			$.ajax({
				url : "${ contextPath }/board/deleteCmt",
				type : "post",
				data : { bno : $("#bno").val(), cid : cid },
				dataType : "json",
				success : function(data) {
					if(data != null){
						alert('댓글이 삭제되었습니다.');
						reloadCmt(data);
					} else {
						alert('댓글이 삭제되지 않았습니다.');
					}
				},
				error : function(e) {
					console.log(e)
				}
			})
		}
		// 수정 완료 버튼
 		editBtn.addEventListener('click', function(e){
 			if(editCmt.value.length < 2) {
 				alert("댓글 작성은 최소 2자 이상 입력이 필수입니다.");
				return;
 			}
			var cid = e.target.parentElement.parentElement.parentElement.lastElementChild.firstElementChild.value;
 			$.ajax({
				url : "${ contextPath }/board/updateCmt",
				type: "post",
				data : { bno : $("#bno").val(), cid : cid, content : editCmt.value },
				dataType : "json",
				success : function(data) {
					if(data != null){
						alert('댓글이 수정되었습니다.');
						reloadCmt(data);
					} else {
						alert('댓글이 수정되지 않았습니다.');
					}
				},
				error : function(e) {
					console.log(e)
				}
			})
		});
 		// 답댓 버튼
		function insertReCmt(parentEl, parentCid){
			if($(".reCmt_write")) {
				$(".reCmt_write").remove();
			}
			var cmtOuter = document.createElement("div");
			cmtOuter.classList.add('reCmt_write');
			var reCmt = document.createElement("textarea");
			reCmt.classList.add('reCmt_content');
			var lengthArea = document.createElement("div");
			cmtLength.classList.add('cmtLengthArea');
			lengthArea.innerHtml = '<span id="reCmtLength">0</span>&nbsp;&nbsp;200자';
			var reCmtBtn = document.createElement('button');
			reCmtBtn.textContent = '답댓등록';

			cmtOuter.append(reCmt, lengthArea, reCmtBtn);
			parentEl.after(cmtOuter);
			
			reCmtBtn.onclick = function() {
				if(reCmt.value.length < 2) {
					alert("댓글 작성은 최소 2자 이상 입력이 필수입니다.");
					return;
				}
				$.ajax({
					url : "${ contextPath }/board/insertReCmt",
					type : "post",
					data : { bno : $("#bno").val(),
							parentCid : parentCid,
							 content : reCmt.value },
					dataTyle : "json",
					success : function(data) {
						if(data != null) {
							alert('답글이 등록되었습니다.');
							reloadCmt(data);
						} else {
							alert('답글이 등록되지 않았습니다.');
						}
					},
					error : function(e) {
						console.log(e);
					}
				});
			};
		}
		<!-- 신고하기 창 show -->
		function showReport() {
			document.querySelector(".rePopup-wrap").style.display = 'flex';
		}
		
		// 댓글 유효성 검사
		$(".cmt_content").keyup(function(){
			var cmt = $(this).val();
			$("#cmtLength").html(cmt.length);
			if(cmt.length > 200) {
				alert("댓글은 최대 200자까지 입력 가능합니다.");
				$(this).val(cmt.substr(0, 200));
				$("#cmtLength").html(200);
			}
		});
	</script>
</body>
</html>