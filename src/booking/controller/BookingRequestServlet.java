package booking.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * Servlet implementation class BookingRequestServlet
 */
@WebServlet("/booking/Rq")
public class BookingRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingRequestServlet() {
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
		int rno=Integer.parseInt(request.getParameter("rno"));
		int userno = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();
		//int userno = 5; // 로그인한 회원
		
		String r_start = request.getParameter("r_start");
		String r_end = request.getParameter("r_end");
		int r_r_head =Integer.parseInt(request.getParameter("head")); // select에서 들어온 값이 string으로 들어오네
		int r_pay = Integer.parseInt(request.getParameter("r_pay"));
		int sum = r_r_head * r_pay;
	
		
	//	System.out.println( rno + ", "+userno+", "+r_start+", "+r_end+", "+ r_r_head +", "+r_pay+", " +sum);
						
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date start = null, end=null;
		try {
			start = sdf.parse(r_start);
			end = sdf.parse(r_end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 날짜 차이 계산
		long calDate = end.getTime() - start.getTime();
		long calDateDays = calDate / (24 * 60 * 60 * 1000);
		calDateDays = Math.abs(calDateDays);
	//	System.out.println(calDateDays);
		
		// Room r = new Room(rno,userno,start,end,r_r_head,r_pay,sum); // Room에 저것들을 다 저장하고, 변경된 인원수를 저장, 금액, 날짜
		// System.out.println("room: "+r);
		
		
		Map<String, Object> map =new RoomService().selectRoom(rno,userno);
		// bookingDetail.jsp 에서 받은 parameter를 여기에서 받아서 select된 룸을 update
	//	Room r = new RoomService().selectRoom(rno);
	//	int result =  new RoomService().updateRoom(r,r_start,r_end);
	//	r = new RoomService().updateRoom(r,r_start,r_end);
		
				
	//	System.out.println(result);
	//	request.setAttribute("Room", map.get(key));
		request.setAttribute("selectroom", map.get("selectroom"));
		request.setAttribute("sroom", map.get("sroom"));
		request.setAttribute("r_start", start); //DAte객체
		request.setAttribute("r_end", end);
		request.setAttribute("calDateDays", calDateDays);
		request.setAttribute("r_r_head", r_r_head);
	//	request.setAttribute("wishList", map.get("wishList"));
		
		
		request.getRequestDispatcher("/WEB-INF/views/booking/bookingRequest.jsp").forward(request, response);
		
		
		
	}

}
