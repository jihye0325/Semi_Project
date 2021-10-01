package member.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import member.model.vo.Member;
import wrapper.EncryptWrapper;

public class SendMail {
	// 테스트 용도
	public void SendMail() {
		
		Properties p = new Properties();
		
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.auth", "true");
		// tls 포트번호 587, ssl 포트번호 465
		p.put("mail.smtp.port", "465");
		
		Authenticator auth = new Gmail();
		Session ses = Session.getDefaultInstance(p, auth);
		MimeMessage msg = new MimeMessage(ses);
		Member m = new Member();
		
		try {
			// 발송 날짜 지정
			msg.setSentDate(new Date());
			// 발송자 메일, 발송자명
			msg.setFrom(new InternetAddress("unijmaster@gmail.com", "UnI_Jeju 관리자"));
			// 수신자 메일 생성
			InternetAddress to = new InternetAddress(m.getUserId());
			// message > setrecipient 수신자 결정
			// Message.RecipientType.TO : m.getUserId();
			// Message.RecipientType.CC : 참조
			// Message.RecipientType.BCC : 숨은 참조
			msg.setRecipient(Message.RecipientType.TO, to);
			
			// 메일 제목 지정
			msg.setSubject("비밀번호를 찾기위한 이메일 인증입니다.");
			// 메일 내용 작성 -- 여기를 잘 모르겠음;
			// msg.setContent("클릭해주세요" + "<a href='" + "findPwd.jsp?code=" + "'>이메일 인증하기</a>", /*여긴 또 뭘까*/);
			msg.setText("<a>이메일 인증하기</a>", "UTF-8");
			
			// 이메일 전송
			Transport.send(msg);
		} catch (AddressException ae) {
			System.out.println(ae.getMessage());
		} catch (MessagingException me) {
			System.out.println(me.getMessage());
		} catch (UnsupportedEncodingException ue) {
			System.out.println(ue.getMessage());
		}
	}
}
