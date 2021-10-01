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
 * Servlet implementation class NoticeDetailServlet Servlet implementation class
 * updateAuthorityServlet
 */
@WebServlet("/admin/memberdetail")
public class memberDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public memberDetailServlet() {

		super();
		// TODO Auto-generated constructor stub
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_no = Integer.parseInt(request.getParameter("user_no"));

		Member member = new MemberManagementService().selectMember(user_no);

		String page = "";
		if(member != null) {
			request.setAttribute("member", member);
			page="/WEB-INF/views/admin/memberDetailview.jsp";
		}else {
			request.setAttribute("msg", "에러.");
			page="/WEB-INF/views/common/errorpage.jsp";
			

		}
		request.getRequestDispatcher(page).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}