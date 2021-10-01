package store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import store.service.StoreService;

/**
 * Servlet implementation class StoreWishDeleteServlet
 */
@WebServlet("/store/deleteWish")
public class StoreWishDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreWishDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		// int user_no = 1;
		// ** 로그인시 처리 **
		int user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		int result = new StoreService().deleteWish(s_no, user_no);
		System.out.println("삭제: " +result);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/store/list");
		} else {
			// request.setAttribute("msg", "위시리스트 삭제하기 실패하였습니다.");
			// request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("위시리스트 삭제하기 실패하였습니다.");
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
