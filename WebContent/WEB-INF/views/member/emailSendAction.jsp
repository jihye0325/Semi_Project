<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.mail.Transport" %>
<%@ page import="javax.mail.Message" %>
<%@ page import="javax.mail.Address" %>
<%@ page import="javax.mail.internet.InternetAddress" %>
<%@ page import="javax.mail.internet.MimeMessage" %>
<%@ page import="javax.mail.Session" %>
<%@ page import="javax.mail.Authenticator" %>
<%@ page import="javax.mail.*" %>
<%@ page import="java.util.Properties" %>
<%@ page import="member.model.vo.Member" %>
<%@ page import="member.mail.Gmail" %>
<%@ page import="wrapper.EncryptWrapper" %>
<%@ page import="java.io.PrintWriter" %>
<%-- 아 잘 모르겠음
<%
	String host = "http://localhost:8800/UnI_Jeju/";
	String from = "unijmaster@gmail.com";
	String to = Member.getUserId("id");
	String subject = "비밀번호를 찾기위한 이메일 인증입니다.";
	String content = "클릭해주세요" + "<a href='" + host + "findPwd.jsp?code=" + new EncryptWrapper.getSha512(to) + "'>이메일 인증하기</a>";
	
	Properties p = new Properties();
	p.put("mail.smtp.user", from);
	p.put("mail.smtp.host", "smtp.googlemail.com");
	p.put("mail.smtp.port", "465");
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "456");
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback", "false");
	
	try {
		Authenticator auth = new Gmail();
		Session ses = Session.getInstance(p, auth);
		ses.setDebug(true);
		
		MimeMessage msg = new MimeMessage(ses);
		msg.setSubject(subject);
		
		Address fromAddr = new InternetAddress(from);
		msg.setFrom(fromAddr);
		
		Address toAddr = new InternetAddress(to);
		msg.addRecipient(Message.RecipientType.TO, toAddr);
		msg.setContent(content, "text/html;charset=UTF-8");
		
		Transport.send(msg);
	} catch(Exception e) {
		e.printStackTrace();
		
		PrintWriter pw = response.getWriter();
		
		pw.println("<script>");
		pw.println("alert('오류가 발생했습니다.');");
		pw.println("history.back();");
		pw.println("</script>");
		pw.close();
		
		return;
	}
%>
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>