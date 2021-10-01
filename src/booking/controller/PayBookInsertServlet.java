package booking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.model.service.RoomService;
import booking.model.vo.Booking;
import booking.model.vo.PayBook;
import member.model.vo.Member;

/**
 * Servlet implementation class PayBookInsertServlet
 */
@WebServlet("/PayBook/insert")
public class PayBookInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayBookInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int p_no = Integer.parseInt(request.getParameter("p_no"));//승인번호
		int money = Integer.parseInt(request.getParameter("money"));
		String pgName = request.getParameter("pg_name");//PG사이름
		String ppaid = request.getParameter("p_status");//결제상태paid
		int rno = Integer.parseInt(request.getParameter("booking_no"));
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		String r_start = request.getParameter("start");
		String r_end = request.getParameter("end");
		int r_r_head = Integer.parseInt(request.getParameter("head"));
		// System.out.println("PayBook서블릿 : "+p_no+ ", " + bk_no+ ", " +money+ ", " +pgName+ ", " +ppaid);
										//48231791, 8, 600, html5_inicis, paid
		Booking b = new Booking(rno,r_start,r_end,r_r_head,userNo);
		PayBook p = new PayBook(p_no, money, pgName, ppaid);
		System.out.println(b);
		System.out.println(p);
		int result = new RoomService().PayBookInsert(p,b);
		

		request.getRequestDispatcher("/WEB-INF/views/mypage/myBook.jsp").forward(request, response);
		
	}

}
