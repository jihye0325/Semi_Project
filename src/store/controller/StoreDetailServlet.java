package store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import store.model.vo.Store;
import store.service.StoreService;

/**
 * Servlet implementation class StoreDetailServlet
 */
@WebServlet("/store/detail")
public class StoreDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		//int user_no = 1;
		// ** 로그인시 처리 **
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		StoreService ss =new StoreService();	
		
		// 쿠키 이용해서 조회수 무한 증가 방지
		Cookie[] cookies = request.getCookies();
		
		String bcnt = "";
		
		if(cookies != null && cookies.length > 0) {
			// 쿠키 값의 배열을 반복하면서 탐색
			for(Cookie c : cookies) {
				// 읽은 게시물 정보를 저장한 쿠키의 이름 bcnt가 있는지 확인
				if(c.getName().equals("bcnt")) {
					bcnt = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글
		if(bcnt.indexOf("|"+s_no+"|") == -1) {
			Cookie newBcnt = new Cookie("bcnt", bcnt + "|" + s_no + "|");
			
			response.addCookie(newBcnt);
			
			// 조회수 증가 로직
			int result = ss.increaseCount(s_no);
			
			if(result > 0) {
				// System.out.println("조회수 증가 성공");
			} else {
				// System.out.println("조회수 증가 실패");
			}
		} else {
			// System.out.println("다시 조회해서 조회수 증가 안 함");
		}
		
		// 게시글 조회
		Store store = ss.selectStore(s_no, user_no);
		
		System.out.println(store);
		
		if(store != null) {
			request.setAttribute("store", store);
			request.getRequestDispatcher("/WEB-INF/views/store/storeDetailView.jsp").forward(request, response);
		} else {
			// request.setAttribute("msg", "식당 상세보기에 실패하였습니다.");
			// request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("식당 상세보기 실패!");
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
