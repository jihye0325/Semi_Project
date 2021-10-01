package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.bookingManagementService;
import admin.model.vo.Room;

/**
 * Servlet implementation class bookingDetailServlrt
 */
@WebServlet("/admin/roomDetail")
public class bookingDetailServlrt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookingDetailServlrt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int r_no = Integer.parseInt(request.getParameter("r_no"));
		System.out.println(r_no+"r_no");
		Room room = new bookingManagementService().getSalseInfo(r_no);
		System.out.println("room"+room);
		String page = "";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		if(room != null) {
		request.setAttribute("room", room);
			page="/WEB-INF/views/admin/roomSalseView.jsp";
		}else {
			page="/WEB-INF/views/admin/roomNoSalseView.jsp";
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
