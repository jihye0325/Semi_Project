package mypage.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import member.model.vo.Member;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class MyMessageServlet
 */
@WebServlet("/mypage/mymessage")
public class MyMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		Member loginUser = (Member) request.getSession().getAttribute("loginUser");

		if (loginUser != null) {
			int userNo = loginUser.getUserNo();
			// 요청 페이지 값을 매개변수로 넘기고 조회 된 게시글 리스트 + 페이징 터리에 대한 객체 값 Map 타입에 담아 린턴
			Map<String, Object> map = new MypageService().selectMessageList(page, userNo);
			
			System.out.println(map.get("R_pi"));
			request.setAttribute("r_pi", map.get("R_pi"));
			request.setAttribute("messageReceiveList", map.get("messageReceiveList"));
			request.setAttribute("messageSendList", map.get("messageSendList"));
			request.getRequestDispatcher("/WEB-INF/views/mypage/myMessage.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("msg", "로그인이 필요합니다.");
			response.sendRedirect(request.getContextPath());
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
