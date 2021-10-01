package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MessageDetailServlet
 */
@WebServlet("/sendmessagedetail")
public class MySendMessageDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MySendMessageDetailServlet() {
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

		int msgNo = Integer.parseInt(request.getParameter("msgNo"));
		String sender = request.getParameter("sender");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		

		request.setAttribute("msgNo", msgNo);
		request.setAttribute("sender", sender);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.getRequestDispatcher("/WEB-INF/views/mypage/sendmessageDetail.jsp").forward(request, response);
	}

}
