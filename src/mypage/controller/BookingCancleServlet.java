package mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypage.model.service.MypageService;

/**
 * Servlet implementation class BookingCancleServlet
 */
@WebServlet("/mypage/bookingcancle")
public class BookingCancleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingCancleServlet() {
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
		int booking_no = Integer.parseInt(request.getParameter("booking_no"));
		int result = new MypageService().cancleBooking(booking_no);

		if (result > 0) {
			request.getSession().setAttribute("msg", "숙소 예약을 취소했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/mybook");
		} else {
			request.getSession().setAttribute("msg", "숙소 예약 취소에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/mybook");
		}
	}

}
