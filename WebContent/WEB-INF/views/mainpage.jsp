<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UnI_Jeju</title>
    <style>
    .headerlogo {
    	width: 150px;
	}
    .outer {
    	width: 1000px;
    	margin: auto;
    	margin-top: 50px;
    	position: relative;
	}

	.join {
	    margin-left: 740px;
	    margin-bottom: 25px;
	}
	
	button {
	    border: 0;
	    border-radius: 5px;
	    width: 95px;
	    height: 30px;
	    margin-left: 5px;
	    background: rgb(254,229,207);
	    font-weight: bold;
	    cursor: pointer;
	}
	
	.page_select {
	    text-align: center;
	    
	}
	
	.nav {
	    width: 500px;
	    margin: auto;
	    display: flex;
	    justify-content: space-around;
	    margin-bottom: 20px;
	    font-weight: bold;
	}
	
	.nav >a:link{
	    color: black;
	    text-decoration: none;
	}
	
	.nav >a:visited{
	    color: black;
	    text-decoration: none;
	}
	
	.nav >a:hover{
	    cursor: pointer;
	    background: rgb(255,191,132);
	    border-radius: 5px;
	}
	
	.map_img {
	    position: relative;
	}
	
	.map {
	    position:absolute;
	    cursor: pointer;
	}
	
	.hamduk {
	    top: 121px;
	    left: 550px;
	    width: 100px;
	    height: 30px;
	}
	
	.jeju {
	    top: 139px;
	    left: 433px;
	    width: 80px;
	    height: 30px;
	}
	
	.aewol {
	    top: 175px;
	    left: 349px;
	    width: 65px;
	    height: 30px;
	}
	
	.seongsan {
	    top: 174px;
	    left: 595px;
	    width: 95px;
	    height: 30px;
	}
	
	.hanlim {
	    top: 235px;
	    left: 315px;
	    width: 100px;
	    height: 30px;
	}
	
	.seogwipo {
	    top: 230px;
	    left: 490px;
	    width: 80px;
	    height: 30px;
	}
	
	.pyosan {
	    top: 250px;
	    left: 602px;
	    width: 65px;
	    height: 30px;
	}
	
	.jungmun {
	    top: 285px;
	    left: 433px;
	    width: 65px;
	    height: 30px;
	}
	
	.pin {
	    position: absolute;
	    top:-35px;
	    left: 35%;
	    opacity: 0;
	}
	
	.pin_img {
	    position: absolute;
	    width: 30px;
	    height: 40px;
	}
	
	.map:hover .pin{
	    opacity: 1;
	}
</style>
</head>
<body>
    <div class="header">
        <a href="">
            <img class="headerlogo" src="resources/images/jejulogo.png">
        </a>
    </div>
 	<%@ include file="/WEB-INF/views/common/banner.jsp"%>
    <div class="outer">
    	<!-- 로그인 안하면 회원가입/로그인버튼 -->
    	<!-- 로그인 하면 마이페이지/로그아웃버튼 -->
        <div class="join">
			<c:choose>
			<c:when test="${ empty loginUser }">
				<button type="button" onclick="location.href='${contextPath}/memberJoin'">회원가입</button>
            	<button type="button" onclick="location.href='${contextPath}/login'">로그인</button>
			</c:when>
			<c:when test="${ loginUser.authority == 3 }">
				<button type="button" onclick="location.href='${contextPath}/admin/memberManagement'">관리자페이지</button>
            	<button type="button" onclick="location.href='${contextPath}/logout'">로그아웃</button>
			</c:when>
			<c:otherwise>
			    <button type="button" onclick="location.href='${contextPath}//memberModify'">마이페이지</button>
            	<button type="button" onclick="location.href='${contextPath}/logout'">로그아웃</button>
			</c:otherwise>
			</c:choose>

        </div>

        <div class="page_select">
            <div class="nav">
                <a href ="${pageContext.servletContext.contextPath}/booking/list">숙소예약</a>
                <a href ="${pageContext.servletContext.contextPath}/store/list">식당</a>
                <a href ="${ contextPath }/board/accompany/list">게시판</a>
                <a href ="${ contextPath }/notice/list">공지사항</a>
            </div>
            <div class="map_wrapper">
                <img class="map_img" src="resources/images/jejumap.png">
                
                <div class="hamduk map" onclick="selectArea('함덕')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="jeju map" onclick="selectArea('제주')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="aewol map" onclick="selectArea('애월')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="seongsan map" onclick="selectArea('성산')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="hanlim map" onclick="selectArea('협재')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="seogwipo map" onclick="selectArea('서귀포')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="pyosan map" onclick="selectArea('표선')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
                <div class="jungmun map" onclick="selectArea('중문')">
                    <div class="pin"><img class="pin_img" src="resources/images/pin.png"></div>
                </div>
            </div>
        </div>
    </div>
    <script>
    	function selectArea(area){
    		console.log(area);
    		location.href='${contextPath}/booking/list?area=' + area;
    	}
    </script>
</body>
</html>