package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.B_Report;
import member.model.vo.Member;

/**
 * Servlet implementation class ReportInsertServlet
 */
@WebServlet("/board/insertReport")
public class ReportInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportInsertServlet() {
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
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int bno = Integer.parseInt(request.getParameter("bno"));
		int reportId = Integer.parseInt(request.getParameter("reportId"));
		
		B_Report bRe = new B_Report(bno, userNo, reportId);
		
		int result = new BoardService().insertReport(bRe);
		
		// System.out.println(result);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(result);
	}

}
