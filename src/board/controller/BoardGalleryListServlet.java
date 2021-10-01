package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.B_Search;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardGalleryListServlet
 */
@WebServlet("/board/gallery/list")
public class BoardGalleryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardGalleryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchValue = request.getParameter("searchValue");
		B_Search s = new B_Search();
		s.setSearchValue(searchValue);
		
		List<Board> boardList = new BoardService().selectGalleryList(s);
		
		/* imgList 확인
		 * for(Board b : boardList) { System.out.println(b.getImgList().get(0)); }
		 */
		
		request.setAttribute("bList", boardList);
		request.getRequestDispatcher("/WEB-INF/views/board/boardGalleryListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
