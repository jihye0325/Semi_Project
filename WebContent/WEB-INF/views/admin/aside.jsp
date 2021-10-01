<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.submenu_outer {
	    margin-top: 100px;
	}
	
	.sub_menu {
		width: 190px;
		list-style: none;
	}
	
	.sub_menu li {
	    line-height: 80px;
	    font-size: 24px;
	    font-weight: bolder;
	    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
	    text-align: center;
	}
	
	.sub_menu a {
	    text-decoration: none;
	    color: black;
	    padding: 10px;
	    border-radius: 20px;
	}
	
	.sub_menu a:hover {
	    background-color: rgba(254, 229, 207, 1);
	}
	
		
	.sub_title {
		width: 190px;
		list-style: none;
	}
	
	.sub_title li {
	    line-height: 80px;
	    font-size: 40px;
	    font-weight: bolder;
	    text-align: center;
	}
	
	
	

</style>
</head>
<body>
	<aside class="submenu_outer">
		<ul class="sub_title">
			<li>MENU</li>
		</ul>
	
		<ul class="sub_menu">
			<li><a href="${contextPath }/admin/memberManagement">회원관리</a></li>
			<li><a href="${contextPath }/admin/postManagement">게시물 관리</a></li>
			<li><a href="${contextPath }/admin/reportManagement">신고관리</a></li>
		</ul>
	</aside>
</body>
</html>