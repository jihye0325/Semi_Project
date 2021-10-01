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
 * Servlet implementation class FindIdServlet
 */
@WebServlet("/findId")
public class FindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/member/findId.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		
		Member findId = new MemberService().searchId(userName, phone);
		
		if(findId != null) {
			request.setAttribute("msg", "입력하신 정보와 일치하는 아이디입니다.");
			// response.sendRedirect(request.getContextPath()+"findPwdAfter");
			request.getRequestDispatcher("WEB-INF/views/member/findIdAfter.jsp").forward(request, response);
		} else {
			request.setAttribute("msg", "고객님의 정보와 일치하는 아이디 입니다.");
			request.getRequestDispatcher("WEB-INF/views/member/findIdAfter.jsp").forward(request, response);
			// response.sendRedirect(request.getContextPath() + "findPWdAfter");
		}
	}
}
