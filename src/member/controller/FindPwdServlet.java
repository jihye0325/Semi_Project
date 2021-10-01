package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mail.Gmail;
import member.model.service.MemberService;
import member.model.vo.Member;
import wrapper.EncryptWrapper;

/**
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/findPwd")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// int userNo = Integer.parseInt(request.getParameter("userNo"));
		String userName = request.getParameter("userName");
		String userId = request.getParameter("userId");
		
		System.out.println("servlet userName : " + userName);
		System.out.println("servlet userId : " + userId);
		
		/*
		Member m = new MemberService().searchPwd(userNo, userName, userId);
		if(m == null || m.getUserId().equals(userId)) {
			request.setAttribute("msg", "일치하는 회원 정보가 없습니다.");
			request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
			return;
		}
		*/
		Member m = new Member();
		
		String host = "http://localhost:8800/UnI_Jeju";
		String from = "unijmaster@gmail.com";
		String to = userId;
		String subject = "비밀번호를 찾기위한 이메일 인증입니다.";
		String content = "클릭해주세요" + "<a href='" + host + "/pwdModify"
				+ /* new EncryptWrapper(request).getSha512(to) + */ "'>이메일 인증하기</a>";
		
		Properties p = new Properties();
		// p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", 587);
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		// p.put("mail.smtp.debug", "true");
		// p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		/*
		Session session = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("unijmaster@gamil.com", "wpwneh1!");
			}
		});
		*/
		
		// 인증번호 생성
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for(int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch(rIndex) {
			case 0:
				// a-z
				temp.append((char)(int)(rnd.nextInt(26) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char)(int)(rnd.nextInt(26) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		
		String AuthenticationKey = temp.toString();
		System.out.println(AuthenticationKey);
		
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
			msg.setText("인증번호 : " + temp);
			
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
		HttpSession saveKey = request.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
		// 비밀번호 변경 시 바꿀 조건에 들어가는 id
		request.setAttribute("userId", userId);
		// request.getRequestDispatcher("WEB-INF/views/member/findPwdAfter.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/findPwdAfter?userId=" + userId);
	}
}