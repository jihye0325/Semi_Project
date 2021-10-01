package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.MemberManagementService;
import admin.model.vo.Member;

/**
 * Servlet implementation class updateAuthorityServlet
 */
@WebServlet("/admin/updateAuthority")
public class updateAuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public updateAuthorityServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		System.out.println("1" + user_no);
		Member member = new MemberManagementService().updateAuthority(user_no);

		String page = "";
		if (member != null) {
			request.setAttribute("member", member);
			page = "/WEB-INF/views/admin/updateAuthorityView.jsp";

		} else {
			request.setAttribute("msg", "수정 페이지 이동 실패");
			page = "/WEB-INF/views/common/errorpage.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

}
