package booking.controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.model.service.RoomService;
import booking.model.vo.Booking;
import booking.model.vo.Room;
import member.model.vo.Member;

/**
 * Servlet implementation class mybookInsertServlet
 */
@WebServlet("/mybook/insert")
public class mybookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mybookInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 예약 요청들어오면 예약요청 리스트에 insert해주고
				int rno = Integer.parseInt(request.getParameter("booking_no"));
				int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
				String r_start = request.getParameter("start");
				String r_end = request.getParameter("end");
				int r_r_head = Integer.parseInt(request.getParameter("head"));
				
		//		System.out.println("예약insert : " + rno + userNo+userName+r_start+r_end+r_r_head);
				/*
				 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); Date start = null,
				 * end = null; try { start = df.parse(r_start); end = df.parse(r_end); } catch
				 * (ParseException e) { e.printStackTrace(); }
				 */
				System.out.println("예약insert : "+rno + " , " +userNo + "r_start:" +r_start + " , " + r_end + " , ");
				
			//	Room r = new Room(rno,userNo,start,end,r_r_head);
			//	int result = new RoomService().insertBook(r,r_start,r_end); // user1와 방번호를 주고 예약확인쪽에 insert
				Booking b = new Booking(rno,r_start,r_end,r_r_head,userNo);
				int result = new RoomService().bookingInsert(b);

				System.out.println(result);
				
				response.getWriter().print(result);
				
				/*
				 * if( result > 0) { request.getSession().setAttribute("msg", "숙소 등록에 성공했습니다.");
				 * response.sendRedirect(request.getContextPath() + "/mypage/mybook"); }else {
				 * request.getSession().setAttribute("msg", "숙소 등록에 실패했습니다.");
				 * response.sendRedirect(request.getContextPath()+"/mypage/mybook"); }
				 */
	}

}
