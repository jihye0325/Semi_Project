package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/mypage/leaveMember")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 멤버 테이블에서 회원 상태(status) 변경 Y-> N
		HttpSession session = request.getSession();

		int userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();

		int result = new MemberService().deleteMember(userNo);

		if (result > 0) {
			session.removeAttribute("loginUser");
			response.sendRedirect(request.getContextPath());
		} else {
			response.setContentType("text/html); charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('회원 탈퇴에 실패했습니다.'); location.href='${ contextPath }/mypage/leaveMemberView';</script>");
			out.flush();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
