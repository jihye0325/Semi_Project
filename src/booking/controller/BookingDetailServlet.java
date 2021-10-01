package booking.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.model.service.RoomService;
import booking.model.vo.Room;
import member.model.vo.Member;

/**
 * Servlet implementation class BookingDetailServlet
 */
@WebServlet("/booking/detail")
public class BookingDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rno=Integer.parseInt(request.getParameter("rno"));
		int user_no = 0;
		// 로그인시 처리 
		if(request.getSession().getAttribute("loginUser") != null) {
			user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();   
		}else {
			System.out.println("로그인이 필요합니다.");
		}
		
		RoomService rs = new RoomService();
		
		//게시글을 좋아요 누른 유저7의 디테일화면에는 돌하르방이 검은색으로 보임
		Map<String, Object> map = rs.selectRoom(rno,user_no);// 게시글 번호 + userno를 매개변수로 줌 - wishList를 표시하기 위해
		
		
		request.setAttribute("selectroom", map.get("selectroom"));
		request.setAttribute("sroom", map.get("sroom"));
		request.setAttribute("wishList", map.get("wishList"));
		request.getRequestDispatcher("/WEB-INF/views/booking/bookingDetail.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
