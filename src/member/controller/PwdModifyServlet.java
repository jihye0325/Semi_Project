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
 * Servlet implementation class PwdModifyServlet
 */
@WebServlet(name = "PwdModifyServlet", urlPatterns = "/pwdModify")
public class PwdModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loginUser") == null) {
			request.setAttribute("msg", "올바르지 않은 요청입니다.");
			request.getRequestDispatcher("WEB-INF/views/mainpage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("WEB-INF/views/member/pwdModify.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Member updateMember = new MemberService().updatePwd(userNo, userPwd, newPwd);
		
		if(updateMember != null) {
			request.setAttribute("result", "success");
			request.getSession().setAttribute("loginUser", updateMember);
		} else {
			request.setAttribute("result", "fail");
		}
		request.getRequestDispatcher("WEB-INF/views/member/pwdModify.jsp").forward(request, response);
	}
}