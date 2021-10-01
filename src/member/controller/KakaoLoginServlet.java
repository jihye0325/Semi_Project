package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class KakaoLoginServlet
 */
@WebServlet("/login/kakaoLogin")
public class KakaoLoginServlet extends HttpServlet {
	private final static String TAG = "kakaoLoginServlet : ";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("kEmail");
		
		System.out.println(id);
		
		String name = request.getParameter("name");
		String[] names = name.split("\"");
		
		Member m = new MemberService().loginMember(id);
		
		if(m == null) {
			// 회원가입 필요
			Member km = new Member();
			km.setUserId(id);
			km.setUserPwd(name);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) {
//		// http://localhost:8800/UnI_Jeju/user?cmd=join
//		String cmd = request.getParameter("cmd");
//		System.out.println(TAG+"router : " +cmd);
//		Action action = router(cmd);
//		action.execute(request, response);
//	}
//	
//	public Action router(String cmd) {
//		if(cmd.equals("callback")) {
//			return new KakaocallbackAction();
//		}
//		return null;
	}
}