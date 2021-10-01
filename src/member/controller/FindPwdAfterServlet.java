package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class FindPwdAfter
 */
@WebServlet(name="FindPwdAfterServlet", urlPatterns="/findPwdAfter")
public class FindPwdAfterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindPwdAfterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String AuthenticationKey = (String) request.getSession().getAttribute("AuthenticationKey");
		String AuthenticationUser = request.getParameter("AuthenticationUser");
		if(!AuthenticationKey.equals(AuthenticationUser)) {
			System.out.println("인증번호 불일치!");
			request.setAttribute("msg", "인증번호가 일치하지 않습니다.");
			request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
			return;
		}
		*/
		/*
		String AuthenticationKey = (String) request.getSession().getAttribute("AuthenticationKey");
		String newPwd = request.getParameter("newPwd");
		String userId = request.getParameter("userId");
		String AuthKey = (String) request.getSession().getAttribute("authKey");
		
		if(AuthKey.equals(AuthenticationKey)) {
			int updateMember = new MemberService().searchPwd(userId, newPwd);
			
			if(updateMember > 0) {
				System.out.println("성공!");
			} else {
				System.out.println("실패!");
			}
			request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
		}
		*/
		request.getRequestDispatcher("WEB-INF/views/member/findPwdAfter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String AuthenticationKey = (String) request.getSession().getAttribute("AuthenticationKey");
		String AuthenticationUser = request.getParameter("AuthenticationUser");
		if(!AuthenticationKey.equals(AuthenticationUser)) {
			System.out.println("인증번호 불일치!");
			request.setAttribute("msg", "인증번호가 일치하지 않습니다.");
			request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
			return;
		}
		
		String newPwd = request.getParameter("newPwd");
		String userId = request.getParameter("userId");
		String AuthKey = (String) request.getSession().getAttribute("authKey");
		
		if(AuthKey.equals(AuthenticationKey)) {
			int updateMember = new MemberService().searchPwd(userId, newPwd);
			if(updateMember > 0) {
				request.setAttribute("result", "성공!");
				request.getSession().setAttribute("authKey", updateMember);
			} else {
				request.setAttribute("result", "실패!");
			}
			request.getRequestDispatcher("WEB-INF/views/member/findPwd.jsp").forward(request, response);
		}
		*/
		
		
		String AuthenticationKey = (String) request.getSession().getAttribute("AuthenticationKey");
		String newPwd = request.getParameter("newPwd");
		String userId = request.getParameter("userId");
		// String AuthKey = (String) request.getSession().getAttribute("authKey");
		String AuthKey = request.getParameter("authKey");
		
		System.out.println("AuthenticationKey : " + AuthenticationKey);
		System.out.println("AuthKey : " + AuthKey);
		System.out.println("userId : " + userId);
		System.out.println("newPwd : " + newPwd);
		
		if(AuthenticationKey.equals(AuthKey)) {
			int updateMember = new MemberService().searchPwd(userId, newPwd);
			
			if(updateMember > 0) {
				System.out.println("성공!");
			} else {
				System.out.println("실패!");
			}
			request.getRequestDispatcher("WEB-INF/views/member/loginpage.jsp").forward(request, response);
		}
	}
}