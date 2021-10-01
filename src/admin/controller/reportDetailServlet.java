package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.reportManagementService;
import admin.model.vo.Report;

/**
 * Servlet implementation class reportDetailServlet
 */
@WebServlet("/admin/reportDetail")
public class reportDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reportDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int report_no = Integer.parseInt(request.getParameter("report_no"));
		
		Report report = new reportManagementService().selectReportDetail(report_no);
		
		String page = "";
		if(report != null) {
			request.setAttribute("report", report);
			page="/WEB-INF/views/admin/reportDetailView.jsp";
		}else {
			request.setAttribute("msg", "에러.");
			page="/WEB-INF/views/common/errorpage.jsp";
			

		}
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
