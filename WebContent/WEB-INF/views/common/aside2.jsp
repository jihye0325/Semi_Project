<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>banner</title>
    <link href="<%=request.getContextPath()%>/resources/css/aside/aside2.css" rel="stylesheet"></link>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    </head>
<body>
    <div class="aside2">
        <a href="https://www.youtube.com/user/Jejudeaf1/featured" target="_blank">
            <img class="banner" src="<%=request.getContextPath()%>/resources/images/adbanner.png">
        </a>

        <div class="qna">
            <a href="${pageContext.servletContext.contextPath}/qna/list">
                <img class="chat" src="<%=request.getContextPath()%>/resources/images/chat.png">
            </a>
        </div>
    </div>
</body>
</html>