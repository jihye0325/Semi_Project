<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<%=request.getContextPath() %>/resources/css/qna/qnaDetail.css" rel="stylesheet"></head>
    <title>1:1 문의글</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp"%>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside1.jsp"%>
 	<%@ include file="/WEB-INF/views/common/aside2.jsp"%>
    <div class="outer">
        <div class="page_title">
            <h1>1:1 문의글</h1>
        </div>
        <div class="content">
            <div class="info">
                <div class="write">
                    <span> 작성자 : ${ qna.id } </span>
                    <span> 작성일 : 
                    <fmt:formatDate value="${ qna.q_create_date }" type="both"
                    pattern="yyyy.MM.dd HH:mm:ss"/></span>
                </div>
                <div class="write2">
                    <span> 문의유형 : ${ qna.q_cname } </span>&nbsp;|
                    <span> 글번호 : ${ qna.q_no } </span>&nbsp;|
                    <span> 조회수 : ${ qna.q_count } </span>&nbsp;|
                    <span> 
                    <c:choose>
                    <c:when test="${ qna.open_status == 'Y' }">공개</c:when>
                    <c:otherwise>비공개</c:otherwise>
                    </c:choose>
                    </span> 
                    <span> 수정일 : 
                    <fmt:formatDate value="${ qna.q_modify_date }" type="both"
                    pattern="yyyy.MM.dd HH:mm:ss"/></span>
                </div>
            </div>
            
            <div class="qna_title">
            	<span>제목 : </span>
                <span>${ qna.q_title }</span>
            </div>

            <div class="qna_content">
                <textarea class="content" readonly>${ qna.q_content }</textarea>
            </div>
            
            <!-- 첨부파일 없으면 첨부파일 div 안보이게 -->
            <c:if test="${ qna.q_image.image_name != null }">
            <span class="file">첨부파일</span>
			<div class="photo_area">
				<img class="photo" src="${ contextPath }${ qna.q_image.route }${ qna.q_image.image_r_name }" alt=" 첨부파일이 없습니다.">
			</div>
			</c:if>

        </div>

        <div class="reply_area">
            <div class="reply_title">댓글</div>
            <div class="reply_list">
            <c:if test="${empty qna.q_replyList}"><div class="noReply">작성된 댓글이 없습니다.</div></c:if>
            <c:forEach items="${ qna.q_replyList }" var="r">
                <div class="reply_ul">
                	<div class="reply_text">
	                	<ul class="writer_info">
		                    <li class="rwriter">${ r.id }</li> 
		                    <li>|</li>
		                    <li class="rdate"><fmt:formatDate value="${ r.c_create_date }"
		                    type="both" pattern="yyyy.MM.dd HH:mm:ss"/></li>
	                    </ul>
	                    <div class="rcontent">${ r.c_comment }</div>
                	</div>
                    
                    <div class= "ud_area">
                    <input type="hidden" value="${ r.comment2_no }" id="c_no" name="c_no">
                    <input type="hidden" value="${ qna.q_no }" id="q_no" name="q_no">
                    <c:if test="${ r.id == loginUser.userId }">
                    	<ul>
                    	<li class="update" id="rupdateView">수정</li>
                    	<li class="delete" onclick="rdelete(${ qna.q_no }, ${ r.comment2_no });">삭제</li>
                    	</ul>
                    </c:if>
                    </div>
                </div>
            </c:forEach>
            </div>
            <c:choose>
            <c:when test="${ loginUser.authority == 3 }">
            	<div class="reply_write">
                	<textarea class="reply_content" placeholder="댓글을 작성해주세요."></textarea>
                	<div class="length_btn">
	                	<div><span id="length">0</span> / 200자</div>
	                	<button onclick="addReply(${ qna.q_no });">댓글 등록</button>
                	</div>
            	</div>
            </c:when>
            <c:when test="${ qna.id == loginUser.userId }">
            	<div class="reply_write">
                	<textarea class="reply_content" placeholder="댓글을 작성해주세요."></textarea>
                	<div class="length_btn">
	                	<div><span id="length">0</span> / 200자</div>
	                	<button onclick="addReply(${ qna.q_no });">댓글 등록</button>
                	</div>
            	</div>
            </c:when>
            <c:otherwise>
                <div class="reply_write">
                	관리자와 게시글 작성자만 댓글을 작성할 수 있습니다.
            	</div>
            </c:otherwise>
            </c:choose>
        </div>
 
		<div class="button_area">
            <button type="button" onclick="location.href='${ contextPath }/qna/list'">목록으로</button>
            <c:if test="${ qna.id == loginUser.userId }">
            <button type="button" onclick="updateQnaView();">수정하기</button>
            <button type="button" onclick="deleteQna()">삭제하기</button>
            </c:if>
            <c:if test="${ loginUser.authority == 3 }">
            <button type="button" onclick="deleteQna()">삭제하기</button>
            </c:if>
            <!-- form 태그를 post 방식으로 submit -->
            <form name="qnaForm" method="post">
            	<input type="hidden" name="q_no" value="${ qna.q_no }">
            	<c:if test="${ qna.q_image.image_name != null }">
            	<input type="hidden" name="image_r_name" value="${ qna.q_image.image_r_name }">
            	</c:if>
            </form>
        </div>
    </div>

	<footer>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</footer>    
    
    <script>
    	function updateQnaView(){
    		document.forms.qnaForm.action = "${contextPath}/qna/updateView";
    		document.forms.qnaForm.submit();
    	}
    	
    	function deleteQna(){
    		if(confirm('이 게시글을 삭제하시겠습니까?')){
    			document.forms.qnaForm.action = "${contextPath}/qna/delete";
    			document.forms.qnaForm.submit();
    		}
    	}
    </script>
    
    <script>
    // 댓글 달기 버튼 클릭 시 댓글 저장(insert) 기능 수행 후
    // 비동기적으로 새로 갱신 된 replyList를 테이블에 적용시키는 ajax 통신
    
    // 글자수 실시간 변화 : 200자 미만이면 파란색, 200자 초과하면 빨간색으로 현재 글자수 표시
		$(".reply_content").keyup(function(){
			var content = $(this).val();
			$.ajax({
				url : "${contextPath}/qna/insertReply",
				type : "get",
				data : { content : $(".reply_content").val()},
				dataType : "json",
				success : function(data){
					if(data != null){
						$("#length").html(content.length);
						if(content.length > 200){
							$("#length").css("color", "red");
						} else {
							$("#length").css("color", "blue");
						}
					}
				},
				error : function(e){
					console.log(e);
				}
					
			});
		});
    
    function addReply(q_no){
		// 댓글내용 200자 이내로 입력
		var content = $(".reply_content");
		// 댓글 내용 200자 넘어가면 등록시 안내창 뜨고 , focus 
		if(content.val().length > 200) {
			alert('댓글 내용은 200자 이내로 입력 가능합니다.');
			$.ajax({
				url : "${contextPath}/qna/insertReply",
				type : "get",
				data : { content : $(".reply_content").val()},
				dataType : "json",
				success : function(data){
					if(data != null){
						content.focus();
					}
				},
				error : function(e){
					console.log(e);
				}
					
			});
			return;
		}
    	
    	$.ajax({
    		url : "${contextPath}/qna/insertReply",
    		type : "post",
    		data : { q_no : q_no, q_content : $(".reply_content").val() },
    		dataType : "json",
    		success :function(data){
    			if(data != null) {
    				console.log(data);
    				alert('댓글이 등록되었습니다.');
    				var html = '';
    				// 새로 갱신 된 댓글 목록을 for문을 통해서 html에 저장
    					for(var key in data) {
	    					html += '<div class="reply_ul"><div class="reply_text"><ul class="writer_info"><li class="rwriter">'
	    						  + data[key].id + '</li><li>|</li><li class="rdate">'
	    						  + data[key].c_create_date + '</li></ul>'
	    						  + '<div class="rcontent">'
	    						  + data[key].c_comment + '</div></div>'
	    						  + '<div class= "ud_area">'
	    						  + '<input type="hidden" value="' + data[key].comment2_no 
	    						  + '" id="c_no" name="c_no">'
	    						  + '<input type="hidden" value="' + data[key].q_no
	    						  + '" id="q_no" name="q_no">'
	    						  if("${ loginUser.userId }" == data[key].id){
	    	                    	html += '<ul><li class="update" id="rupdateView">수정</li>'
	    	                    	+ '<li class="delete" onclick="rdelete('
	    	                    	+ data[key].q_no + ', ' + data[key].comment2_no
	    	                    	+ ');">삭제</li></ul></div></div></div>'
	    						  }    				
    						}
    				
    				// 갱신 된 목록 화면에 적용
    				$(".reply_list").html(html);
    				// 댓글 작성 리셋
    				$(".reply_content").val("");
    				$("#length").html("0");
    				$("#length").css("color", "black");
    				
    			} else {
    				alert('댓글 입력 실패!');
    			}
    		},
    		error : function(e)	{
    			console.log(e);
    		}
    	});
    }
    
    </script>
    
    <!-- 댓글 수정/ 삭제-->
	<script>	
		var updateOuter = document.createElement('div');
		updateOuter.id = 'update_outer';
		var updateComment = document.createElement('textarea');
		var updateBtn = document.createElement('button');
		var updateResult;	// 결과를 담을 div 요소
		var originComment;	// 기존 댓글 내용
		updateComment.id = 'updateComment';
		updateBtn.id = 'updateBtn';
		updateBtn.type = 'button';
		updateBtn.textContent = '수정완료';
		updateOuter.append(updateComment);
		updateOuter.append(updateBtn);
		var originBtn;	// 수정/삭제 버튼
	
		// 댓글 수정화면
		$(document).on('click',"#rupdateView", function(e){
			//console.log(e.target.id);
			if(updateComment.textContent != null){
				updateComment.textContent = originComment;
				updateOuter.replaceWith(updateResult);
				if(originBtn){
					originBtn.style.visibility = 'visible';
				}
			}
			//console.log(e.currentTarget.parentElement.parentElement.parentElement.firstElementChild.lastElementChild.innerHTML);
			updateComment.value = e.currentTarget.parentElement.parentElement.parentElement.firstElementChild.lastElementChild.innerHTML;
			console.log(e.currentTarget.parentElement.parentElement.parentElement.firstElementChild.lastElementChild);
			updateResult = e.currentTarget.parentElement.parentElement.parentElement.firstElementChild.lastElementChild;
			originComment = updateResult.value;
			console.log(e.currentTarget.parentElement);
			originBtn = e.currentTarget.parentElement;
			originBtn.style.visibility = 'hidden';
			updateResult.replaceWith(updateOuter);
		});
	
		// 수정완료 버튼 클릭
		updateBtn.addEventListener('click', function(e){
			var c_no = $('#c_no').val();
			// console.log($('#c_no').val());
			// console.log($('#q_no').val());
			
			$.ajax({
				url: "${contextPath}/qna/updateReply",
				type: "post",
				data : {q_no : $('#q_no').val(), c_no : c_no, content : updateComment.value },
				dataType: "json",
				success : function(data){
					if(data != null){
						alert('댓글이 수정되었습니다.');
						console.log(data);
						var html ='';
						
	    				for(var key in data) {
	    					html += '<div class="reply_ul"><div class="reply_text"><ul class="writer_info"><li class="rwriter">'
	    						  + data[key].id + '</li><li>|</li><li class="rdate">'
	    						  + data[key].c_create_date + '</li></ul>'
	    						  + '<div class="rcontent">'
	    						  + data[key].c_comment + '</div></div>'
	    						  + '<div class= "ud_area">'
	    						  + '<input type="hidden" value="' + data[key].comment2_no 
	    						  + '" id="c_no" name="c_no">'
	    						  + '<input type="hidden" value="' + data[key].q_no
	    						  + '" id="q_no" name="q_no">'
	    						  if("${ loginUser.userId }" == data[key].id){
	    	                    	html += '<ul><li class="update" id="rupdateView">수정</li>'
	    	                    	+ '<li class="delete" onclick="rdelete('
	    	                    	+ data[key].q_no + ', ' + data[key].comment2_no
	    	                    	+ ');">삭제</li></ul></div></div></div>'
	    						  }    				
    						}
	    				
	    				// 갱신 된 목록 화면에 적용
	    				$(".reply_list").html(html);
					} else {
						alert('댓글 수정에 실패했습니다.');
					}
				},
				error: function(e){
					console.log(e);
				}
			});
		});
		
		
		// 댓글 삭제
		function rdelete(q_no, c_no){			
			if(confirm('댓글을 삭제하시겠습니까?')){
				$.ajax({
					url : "${contextPath}/qna/deleteReply",
					type : "post",
					data : { q_no : q_no, c_no : c_no},
					dataType: "json",
					success : function(data){
						if(data != null) {
							alert("댓글이 삭제되었습니다.");
		    				console.log(data);
		    				
		    				var html = '';
		    				// 새로 갱신 된 댓글 목록을 for문을 통해서 html에 저장
		    				for(var key in data) {
	    					html += '<div class="reply_ul"><div class="reply_text"><ul class="writer_info"><li class="rwriter">'
	    						  + data[key].id + '</li><li>|</li><li class="rdate">'
	    						  + data[key].c_create_date + '</li></ul>'
	    						  + '<div class="rcontent">'
	    						  + data[key].c_comment + '</div></div>'
	    						  + '<div class= "ud_area">'
	    						  + '<input type="hidden" value="' + data[key].comment2_no 
	    						  + '" id="c_no" name="c_no">'
	    						  + '<input type="hidden" value="' + data[key].q_no
	    						  + '" id="q_no" name="q_no">'
	    						  if("${ loginUser.userId }" == data[key].id){
	    	                    	html += '<ul><li class="update" id="rupdateView">수정</li>'
	    	                    	+ '<li class="delete" onclick="rdelete('
	    	                    	+ data[key].q_no + ', ' + data[key].comment2_no
	    	                    	+ ');">삭제</li></ul></div></div></div>'
	    						  }    				
    						}
    				
    						// 갱신 된 목록 화면에 적용
    						$(".reply_list").html(html);
		    				// 삭제하면 다시 리뷰 등록가능하도록
		    				// 글자수 세기
		    				$(".reply_content").keyup(function(){
		    					var content = $(this).val();
		    					$.ajax({
		    						url : "${contextPath}/store/insertReply",
		    						type : "get",
		    						data : { content : $(".reply_content").val()},
		    						dataType : "json",
		    						success : function(data){
		    							if(data != null){
		    								$("#length").html(content.length);
		    								if(content.length > 200){
		    									$("#length").css("color", "red");
		    								} else {
		    									$("#length").css("color", "blue");
		    								}
		    							}
		    						},
		    						error : function(e){
		    							console.log(e);
		    						}
		    							
		    					});
		    				});
						} else {
							alert('댓글 삭제 실패');
						}
					},
					error :function(e){
						console.log(e);
					}
				});	
			}
		}
	</script>
</body>
</html>






