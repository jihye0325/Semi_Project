package booking.controller;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class BookingListServlet
 */
@WebServlet("/booking/list")
public class BookingListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
	
		int user_no = 0;
		// 로그인시 처리 
		if(request.getSession().getAttribute("loginUser") != null) {
			user_no = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();   
		}
		
		if (request.getParameter("page") != null ) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String area = request.getParameter("area");
		//userno = Integer.parseInt(request.getParameter("userno"));
		
		Map<String, Object> map = new RoomService().selectRoomImage(page ,user_no,area ); // 1번 
		
		request.setAttribute("pi", map.get("pi"));
		request.setAttribute("roomimage", map.get("roomimage"));
		request.setAttribute("wishList", map.get("wishList"));
		
		
		// 결과값을 다 확인했으니 이제 booking.jsp에 적용하자
		request.getRequestDispatcher("/WEB-INF/views/booking/booking.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
