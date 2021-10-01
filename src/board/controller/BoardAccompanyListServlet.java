package board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.B_Search;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/accompany/list")
public class BoardAccompanyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAccompanyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int[] bid = {0, 10};
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색어
		String searchValue = request.getParameter("searchValue");
		B_Search s = new B_Search();
		s.setSearchValue(searchValue);
		Map<String, Object> map = new BoardService().selectList(page, bid, s);
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("boardList", map.get("boardList"));
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardAccompanyListView.jsp").forward(request, response);
		/*
		 * List<Board> boardList;
		 * 
		 * boardList = new BoardService().selectList(bid);
		 * 
		 * request.setAttribute("boardList", boardList); request.getRequestDispatcher(
		 * "/WEB-INF/views/board/boardAccompanyListView.jsp").forward(request,
		 * response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
