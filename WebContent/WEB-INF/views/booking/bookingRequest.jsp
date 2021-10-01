<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import= "member.model.vo.Member" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>

<title>예약요청하기</title>
<link href="<%=request.getContextPath()%>/resources/css/booking/bookingRequest.css" rel="stylesheet"></link>
</head>


<body>

<!-- 해더 부분-->
<div id="booking" class="big-bg">
    
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class ="logo">
            <a href="메인페이지.html"><img class ="second_logo" src="<%=request.getContextPath()%>/resources/images/logo (2).png" alt="jejudo" ></a>
            <img class ="first_logo" src="${ contextPath }/resources/images/common/banner.png" width="100%" alt="jejudo" >
    </div>
    
    <div id="text" ><h1>예약하기</h1></div>
    
    <c:forEach var="selectroom" items="${ selectroom }" varStatus="s">
    	<c:forEach var="roomimage" items="${selectroom.roomimage }" varStatus="i">
    	
    <div class ="area">
		<img class ="Room1" src="${contextPath}${ roomimage.route }${roomimage.image_r_name }">
	</div>
    	
    	</c:forEach>
    </c:forEach>
    
    

   
    <div id="area_1">
    
    	<img class="jeju" src="${ contextPath }/resources/images/jeju (1).png" alt="jeju(1)">
    	
	    	<div class="content">
	    	
	    		<form name = "bookingRq" method="post">
	    		
		    		<label>숙소 No.${selectroom.get(0).r_no }</label><br>
		    			<input type = "hidden" class ="booking_no" name="booking_no" value="${selectroom.get(0).r_no  }">
			    	<label>숙소 이름 : ${selectroom.get(0).r_name }</label><br>
		    			<input type = "hidden" class ="r_name" name="r_name" value="${selectroom.get(0).r_name  }">
			    	<label>예약자 : ${ loginUser.userName}</label><br>
			    		<input type = "hidden" class ="user_name" name="user_name" value="${ loginUser.userName}">
			    		<input type ="hidden" class="user_no" name="user_no" value="${loginUser.userNo }">
			    	
			    	<label>주소 : ${selectroom.get(0).r_address }</label><br>
			    		<input type = "hidden" class="r_address" name="r_address" value="${selectroom.get(0).r_address }">
			    	
			    	<label>연락처 : ${ loginUser.phone }</label><br>
			    		<input type = "hidden" class="phone" name="phone" value="${ loginUser.phone }">
			    	
			    	<label>예약날짜 : <fmt:formatDate value = "${r_start}" type="both" pattern="yyyy-MM-dd"/>
			    	~
			    	<fmt:formatDate value ="${r_end}"  type="both" pattern="yyyy-MM-dd"/></label><br>
			    	
			    		<input type = "hidden" class="r_start" name="r_start" value="<fmt:formatDate value = "${r_start}"  type="both" pattern="yyyy-MM-dd"/>">
			    		<input type = "hidden" class="r_end" name="r_end" value="<fmt:formatDate value = "${r_end}"  type="both" pattern="yyyy-MM-dd"/>">
			    	
			    	<label>금액: ${calDateDays*selectroom.get(0).r_pay} 원</label> <br>
			    		<input type = "hidden" name="r_pay" id="r_pay" value="${calDateDays*selectroom.get(0).r_pay}">
			    	
			    	<label>인원수: ${r_r_head} 명<br> </label>
			    		<input type = "hidden" name="r_r_head" id="r_r_head" value="${r_r_head}">
			    		
			    	<label>결제방법 :</label>
	                    <input type="radio" id="cash" name="pay" value="cash" checked><label for="cash">계좌이체</label>
						<input type="radio" id="credit" name="pay" value="credit"><label for="credit">신용거래</label>
		    	</form> 
					    	
	    	</div>
    	
    	<img class="jeju" src="${ contextPath }/resources/images/jeju (1).png" alt="jeju(1)">
    	
    </div>
    
    	<div id="area_2"> <button type="button" id="reqestBtn">예약신청</button>  </div>
   

  
    <div id="area_3" class="modal-overlay">
    	<div class ="window">
    		<div class ="title">
    				<h2>동의서</h2>
    		</div>
    		
		    <div class="close-area">x</div>
		    <div  class="content2"> <%@ include file="/WEB-INF/views/booking/degree.jsp" %></div>
		    
		    <div id="btns">
		    				
		    	<button type="button" id="agreeBtn">동의합니다.</button> 
		     	<button type="button" id="button4" onclick="deagree(${selectroom.get(0).r_no })">동의하지 않습니다.</button> 
		    </div>
    	</div>
    </div>

   
   <div id="account" class="modal-overlay2">
   		<div class ="window2">
    		<div class ="title2">
    				계좌번호
    		</div>
    		
		    <div class="close-area2" >x</div>
		    <div  class="content2"> 
		    		<h2>국민은행</h2>
    				<h2>5137-02-446684</h2>
    				<h2>감사합니다. 좋은 하루되세요!</h2>
    		 </div>
    	</div>
   </div>
    	   
</div>


<script>

	let reservation;
	//약관이 나오기 전에 라디오버튼의 값에 따라 반응이 다름
	// 신용버튼 -> 아임포트
	// 계좌이체 -> 계좌번호 창( account )
	
	// 1. 동의서 띄우고
	let area_3 = document.getElementById("area_3");
	let reqestBtn = document.getElementById("reqestBtn"); //예약신청
	reqestBtn.addEventListener("click", function(){
		area_3.style.display = "flex"; //약관동의창띄우기
	});
	
	/* 1-1. 동의 버튼 누르면 약관 닫히고*/
	let agreeBtn = document.querySelector("#agreeBtn"); //약관동의버튼
	agreeBtn.addEventListener("click", function() {
		area_3.style.display = "none"; // 동의 누르면 닫힘
		let rno = document.querySelector("input[name=booking_no]").value;
		paySelect(rno);
	});
	
	function paySelect(rno) {
		let pay = document.querySelector('input[name=pay]:checked').value;
		
			if(pay == "cash") {// checked 속성이 true 와 같은지 비교합니다.
					console.log("계좌이체");
					console.log($(".r_start").val());
				
					//2. 계좌번호 창 
					let account = document.getElementById("account"); // account  계좌번호창
					account.style.display = "flex";
					
					bookInsert();
					
					//3. 계좌번호 창을 끄면 마이페이지 예약확인창으로 
					let closeBtn2 = document.querySelector(".close-area2") //closeBtn2  x 버튼
					closeBtn2.addEventListener("click", function() {		 //x 버튼를 누르면 이동 
						location.href = '${contextPath}/mypage/mybook';
					});
					
			} else {
					
					console.log("신용카드");
					
	/* 				// 1. 동의서 띄우고
					let area_3 = document.getElementById("area_3");
					let reqestBtn = document.getElementById("reqestBtn"); //예약신청
					reqestBtn.addEventListener("click", function(){
						area_3.style.display = "flex"; //약관동의창띄우기
						
					});
					
					/* 1-1. 동의 버튼 누르면 약관 닫히고
					let agreeBtn = document.querySelector("#agreeBtn"); //약관동의버튼
					agreeBtn.addEventListener("click", function(rno)
						area_3.style.display = "none"; // 동의 누르면 닫힘 */
						
					/* 아임프트 띄우기 */
					 importBtn(rno);
			}
	}
	
	// bookinsert_Roombooking DB에 저장
	function bookInsert(){
		
		$.ajax({
			url : "${contextPath}/mybook/insert",
			data : { booking_no : $(".booking_no").val() //숙소번호 
					, start : $(".r_start").val()
					, end : $(".r_end").val()
					, head : $("#r_r_head").val()
					},
			type : "post",
			success : function(data){
				if( data > 0 ){
					reservation = true;
					console.log("bookInsert : "+reservation);
					alert("예약 성공하였습니다.");
				}else{
					console.log(" bookinsert_Roombooking DB에 저장 실패 : " + data);
				}
			},
			 error: function(e){
					console.log(e);
	   		}
			
		});
		
	}
	
 	function importBtn(rno){
 				  
			      var IMP = window.IMP; // 생략가능
			      IMP.init('아임포트키');
			      var money = $('#r_pay').val();
			      var userName = $('.user_name').val();
			      var phone = $('.phone').val();
			      var booking_no = $(".booking_no").val();
			      var r_address = $(".r_address").val();
			      var start = $(".r_start").val();
			      var end = $(".r_end").val();
			      var r_pay = $(".r_pay").val();
			      var head = $("#r_r_head").val();
			      
			      IMP.request_pay({
					      pg: 'inicis', // version 1.1.0부터 지원.
					      pay_method: 'card',
					      merchant_uid: 'merchant_' + new Date().getTime(), //p_no
					      /*
					      merchant_uid에 경우
					      https://docs.iamport.kr/implementation/payment
					   	   참고하기. 아직 못함.
					      */
					      name: '숙소 예약하기 ', //결제창에서 보여질 이름
					      amount: money, //가격
					      buyer_name: userName, //구매자이름
					      buyer_phone: phone,
					      buyer_addr: '',
					      buyer_postcode: '',
					     // m_redirect_url: 'http://localhost:8800/UnI_JeJu/booking/Rq?rno=' +rno // 숙소마다 요청 창이 다름
			   			  }, 
					      function (rsp) {
					      console.log(rsp);
					      if (rsp.success) {
							      var msg = '결제가 완료되었습니다.';
							      msg += '고유ID : ' + rsp.imp_uid;
							      msg += '상점 거래ID : ' + rsp.merchant_uid;
							      msg += '결제 금액 : ' + rsp.paid_amount;
							      msg += '카드 승인번호 : ' + rsp.apply_num;
							      msg += 'PG사명 : ' + rsp.pg_provider;
							      msg += '결제상태 : ' + rsp.status;
							      let purchaseVo = {
							    		  userName : userName, //게스트 이름
							    		  phone : phone, //게스트 번호
							    		  money : money, // 금액
							    		  rno : rno, // 숙소 넘버
							    		  r_address : r_address, //숙소 주소
							    		  r_start : start, //체크인
							    		  r_end : end, //체크아웃
							    		  r_pay : r_pay, // 총금액
							    		  r_r_head : head, //인원수
							    		  apply_num : rsp.apply_num, //승인번호
							    		  pg_name : rsp.pg_provider//pg사 pg_provider
							    		  //카드번호
							    		  //결제상태
							     		 }
							      
							     /*  bookInsert(); */
							     
							      
						    	  $.ajax({
						              url  : "${contextPath}/PayBook/insert", // 결제 성공하면 mybookInsert창으로 요청
						              type : "POST", 
						              data : {
						            	  	booking_no : $(".booking_no").val() //숙소번호 
											, start : $(".r_start").val()
											, end : $(".r_end").val()
											, head : $("#r_r_head").val(),
						            	  	  p_no : rsp.apply_num, // 승인번호
							                  money : rsp.paid_amount, //결제금액
							                  pg_name : rsp.pg_provider, //PG사 이름
							                  p_status : rsp.status //결제상태paid
						              		  },
						           success : function(data){
									            	  if(data != null){
									            		  
									            		  console.log("DB에 저장하기 ")
									            		 location.href="${contextPath}/mypage/mybook";
									            	  }else{
									            		  alert("DB에 저장하지 못했습니다.");
									            		  return false;
									            	  }
									              },
						             error: function(e){
												console.log(e);
						              		}
								 	});
							      
							      window.close();
							      
					        } else {
					            var msg = '결제에 실패하였습니다. ';
					            msg += ' error : ' + rsp.error_msg;
					        }
					        	alert(msg);
			   			  }
			   			);
 	}

	   /*비동의*/
		function deagree(rno){
		   alert("동의하지 않으셨습니다.");
			area_3.style.display = "none";
	    };

</script>





<script>			
			/* 3.  아임포트가 끝나면 mybook에 insert 됨
			function agreeBtn(){
			document.forms.bookingRq.action ="${contextPath}/mybook/insert";
			document.forms.bookingRq.submit();
			}
		*/
</script>		




<script>
			
			/* 2.약관 동의 누르면 -> 아임포트 나오고
//결제 API
      
      function agreeBtn(rno) {
      var IMP = window.IMP; // 생략가능
      IMP.init('imp81373483');
       var money = $('#r_pay').val();
           
      var userName = '${ loginUser.userName }';
      var phone = '${ loginUser.phone }';
      
      IMP.request_pay({
      pg: 'inicis', // version 1.1.0부터 지원.

      pay_method: 'card',
      
      merchant_uid: 'merchant_' + new Date().getTime(),
      /*
      merchant_uid에 경우
      https://docs.iamport.kr/implementation/payment
   	   참고하기. 아직 못함.
      
      name: '숙소 예약하기 ',
      //결제창에서 보여질 이름
      amount: money,
      //가격
     
      buyer_name: userName,
      buyer_phone: phone,
      buyer_addr: '',
      buyer_postcode: '',
      m_redirect_url: 'http://localhost:8800/UnI_JeJu/booking/Rq?rno=' +rno // 숙소마다 요청 창이 다름
   }, 
      function (rsp) {
      console.log(rsp);
      if (rsp.success) {
      var msg = '결제가 완료되었습니다.';
      msg += '고유ID : ' + rsp.imp_uid;
      msg += '상점 거래ID : ' + rsp.merchant_uid;
      msg += '결제 금액 : ' + rsp.paid_amount;
      msg += '카드 승인번호 : ' + rsp.apply_num;
      */
   /* 
      $.ajax({
              type: "POST", 
              url: "${contextPath}/mybook/insert", //충전 금액값을 보낼 url 설정
              data: {
                  "amount" : money/110,
                  "pay_method" : pay_method
              },  
       });
    
         
          window.close();
     */
     /*
        } else {
            var msg = '결제에 실패하였습니다. ';
            msg += ' error : ' + rsp.error_msg;
        }
        alert(msg);
        
       
    });
   };			
   */
			
</script>



<script>
		/* 2. 계좌번호 창이 뜸
		closeBtn.addEventListener("click", function() {
			let account = document.getElementById("account");
			account.style.display = "flex";
			
		});
		*/
</script>
		
<script>		
	/*비동의
	function deagree(rno){
        location.href = '${contextPath}/booking/Rq?rno='+rno;
    };
    */
</script>

<script>


	/* 계좌이체 vs 신용거래 
	document.querySelector("#cash").addEventListener('click', function() => {
		credit.checked=true;
	});
	*/


	/* 3. 계좌번호 창을 끄면 마이페이지 예약확인창으로 
	let closeBtn2 = document.querySelector(".close-area2")
	closeBtn2.addEventListener("click", function() {
		document.forms.bookingRq.action ="${contextPath}/mybook/insert";
		document.forms.bookingRq.submit();
		
	});
	*/
	
</script>	
<script>
/* 	
	let area_3 = document.getElementById("area_3");
	let button2 = document.getElementById("reqestBtn"); //예약신청
	button2.addEventListener("click", function(){
		area_3.style.display = "flex"; //약관동의창띄우기
		
	});
	 */
</script>
<script>	
		/* 1.동의 버튼 누르면 약관 닫히고
		let closeBtn = document.querySelector("#agreeBtn"); //약관동의버튼
		closeBtn.addEventListener("click", function() {
			area_3.style.display = "none"; // 동의 누르면 닫힘
			
		});
		*/
</script>		
</body>
</html>