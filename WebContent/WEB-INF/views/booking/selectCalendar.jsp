<%@ page language="java" contentType="text/html; charset=UTF-8" ageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar" %>
<%
//getInstance는 Calendar의 객체만을 생성한다.
Calendar cal = Calendar.getInstance();
//현재년도와 현재 달을 받아온 것이다. +1은 0~11월달까지 주어지기 때문에 +1을 해준것이다.
int nowYear = cal.get(Calendar.YEAR);
int nowMonth = cal.get(Calendar.MONTH)+1;
//year년과 month을 값을 받아 온것이다.(이전달, 다음달을 클릭하였을때 받아오는 값)
String fyear = request.getParameter("year");
String fmonth = request.getParameter("month");
//현재년도와 현재달을 year과 month로 선언해주었다.
int year = nowYear;
int month = nowMonth;
//넘어온 값이 널값이 아니면 해당하는 fyear값은 year의 값인것이다.
if(fyear != null){
	year = Integer.parseInt(fyear);
}
//넘어온값이 널값이 아니면 해당하는 fmonth값은 month의 값인것이다.
if(fmonth != null){
	month = Integer.parseInt(fmonth);
}
//넘어온값을 새로이 cal객체생성한 곳에 입력이 된다.(년,월,일)초기화
cal.set(year,month-1,1);
//입력되어진 년과 달을 값을 다시 year,month로 선언해주었따.
year = cal.get(Calendar.YEAR);
month = cal.get(Calendar.MONTH)+1;
//cal.getActualMaximum는 그달의 마지막일을 출력한것이다.
int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//현재, 즉 오늘날짜를 말한것이다.
int week = cal.get(Calendar.DAY_OF_WEEK);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>달력만들기</title>
</head>
<body>
<a href = "calendar.jsp?year=<%=year %>&month=<%=month-1 %>">이전달</a>&nbsp;
<b><%=year %>년&nbsp;&nbsp;<%=month %>월</b>
<a href="calender.jsp?year=<%=year %>&month=<%=month + 1 %>">&nbsp;다음달</a>

<table border="1">
<tr>
<td style=color:red;>일</td>
<td>월</td>
<td>화</td>
<td>수</td>
<td>목</td>
<td>금</td>
<td style=color:blue;>토</td>
</tr>
<tr>
<%
	for(int i=1 ; i<week; i++){
%>
		<td height="20">&nbsp;</td>
<%
	}
%>
<%
	for(int j =1 ; j<=endDay ; j++){
		week++;
		if(week % 7 == 2) {
%>
</tr>
	<tr>
		<% } %>
	   <%if(week % 7 == 2){ %>
			<td style="color:red;"><%=j %></td>
			<%}else if(week % 7 == 1){ %>
			<td style="color:blue";><%=j %></td>
			<%}else{ %>
			<td style="color:black";><%=j %></td>
		<% }
	   }%>
	   
	   </tr>
</table>
</body>
</html>