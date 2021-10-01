package qna.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Q_Search;

/**
 * Servlet implementation class MyQnaListServlet
 */
@WebServlet("/qna/myQnalist")
public class MyQnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징처리
		int page = 1;
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		String id = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		// 다른 페이지에서 전환할 때 전달받은 페이지 값으로 page에 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
				
		// 요청 페이지 값을 매개변수로 넘김, (조회된 게시글 리스트 + 페이징 처리에 대한 객체 값)을 Map에 담아 리턴함
		Map<String, Object> map = new QnaService().selectMyList(page, user_no, id);
		
		// 응답 페이지 구성 시 사용할 데이터
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("qnaList", map.get("qnaList"));
		
		request.getRequestDispatcher("/WEB-INF/views/qna/qnaMyListView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
