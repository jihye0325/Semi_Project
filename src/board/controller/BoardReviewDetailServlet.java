package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardReviewDetailServlet
 */
@WebServlet("/board/review/detail")
public class BoardReviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno = Integer.parseInt(request.getParameter("bno"));
		BoardService bs = new BoardService();
		
		Cookie[] cookies = request.getCookies();
		String bcount = "";
		
		// 쿠키 값이 있는 경우 bcount에 쌓기
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if(c.getName().contentEquals("bcount")) {
					bcount = c.getValue();
				}
			}
		}
		// 처음 읽는 게시글인 경우
		if(bcount.indexOf("|" + bno + "|") == -1) {
			Cookie newBcount = new Cookie("bcount", bcount + "|" + bno + "|");
			
			response.addCookie(newBcount);
			
			// 조회수 증가 로직
			int result = bs.increaseCount(bno);
		}
		
		Board b = bs.selectBoard(bno);

		if(b != null) {
			request.setAttribute("board", b);
			request.getRequestDispatcher("/WEB-INF/views/board/boardReviewDetailView.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/board/review/list");
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
