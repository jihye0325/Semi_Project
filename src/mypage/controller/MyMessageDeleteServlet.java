package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;

/**
 * Servlet implementation class MyMessageDeleteServlet
 */
@WebServlet("/mypage/msgdelete")
public class MyMessageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyMessageDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int msgNo = Integer.parseInt(request.getParameter("msgNo"));

		System.out.println(msgNo);
		int result = new MypageService().deleteMSG(msgNo);
		if (result > 0) {
			request.getSession().setAttribute("msg", "쪽지를 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/mymessage");
		} else {
			request.getSession().setAttribute("msg", "쪽지 삭제에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/mymessage");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
