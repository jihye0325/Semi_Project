package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/member/loginpage.jsp");
		view.forward(request, response);
		*/
		request.getRequestDispatcher("/WEB-INF/views/member/loginpage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 로그인 시 필요한 정보들(userId, userPwd) 호출
		String userId =request.getParameter("userId"); 
		String userPwd = request.getParameter("userPwd");
		
		String kEmail = (String) request.getSession().getAttribute("kEmail");
		System.out.println(kEmail);
		
		// 2.  
		Member loginUser = new MemberService().loginMember(userId, userPwd);
		System.out.println("로그인 완료 : "+loginUser);
		
		// 3. loginUser 유무에 
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("msg", "로그인에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}
}