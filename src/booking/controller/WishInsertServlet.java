package booking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.model.service.RoomService;
import member.model.vo.Member;


/**
 * Servlet implementation class WishInsertServlet
 */
@WebServlet("/booking/wishinsert")
public class WishInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WishInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno = Integer.parseInt(request.getParameter("rno"));
		
		
		int user_no = 0;
		// 로그인시 처리 
		if(request.getSession().getAttribute("loginUser") != null) {
			user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();   
		}else {
			System.out.println("로그인이 필요합니다.");
		}
		
		int result = new RoomService().insertWish(rno, user_no);
		System.out.println("insert : " + result); 
		
		if( result > 0 ) {
			response.sendRedirect(request.getContextPath()+"/booking/list");
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
