package store.controller;

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
import store.model.vo.S_Search;
import store.service.StoreService;

/**
 * Servlet implementation class StoreListServlet
 */
@WebServlet("/store/list")
public class StoreListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징처리
		int page = 1;
		int user_no = 0;
		// 로그인시 처리 
		if(request.getSession().getAttribute("loginUser") != null) {
			user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();	
		}
		System.out.println(request.getSession().getAttribute("loginUser"));
		
		// 다른 페이지에서 전환할 때 전달받은 페이지 값으로 page에 적용
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		// 검색 값 
		String searchValue = request.getParameter("searchValue");
		if(searchValue == null || searchValue.equals("undefined")) {
			searchValue = "";
		}
		System.out.println("searchValue :" + searchValue);
		
		// 지역 값
		String area = request.getParameter("area");
		if(area == null || area.equals("undefined")) {
			area = "";
		}
		System.out.println("area :" + area);
		
		// 정렬 값
		String array = request.getParameter("array");
		
		if(array == null || array.equals("undefined")) {
			array = "";
		}
		System.out.println("array :" + array);
		
		// 요청 페이지 값을 매개변수로 넘김, (조회된 게시글 리스트 + 페이징 처리에 대한 객체 값)을 Map에 담아 리턴함
		Map<String, Object> map = new StoreService().selectList(page, new S_Search(searchValue), area, array, user_no);
		System.out.println(map.get("storeList"));
		
		// 응답 페이지 구성 시 사용할 데이터
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("storeList", map.get("storeList"));
		request.setAttribute("wishList", map.get("wishList"));
		
		request.getRequestDispatcher("/WEB-INF/views/store/storeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
