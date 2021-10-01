package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.statisticsService;
import admin.model.vo.Member;

/**
 * Servlet implementation class siteStatisticsServlet
 */
@WebServlet("/admin/postManagement")
public class siteStatisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public siteStatisticsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int memberListCount = new statisticsService().memberListcount();
		int boardListCount = new statisticsService().boardListCount();
		int noticeListCount = new statisticsService().noticeListCount();
		int storeListCount = new statisticsService().storeListCount();
		int roomListCount = new statisticsService().roomListCount();
		
		
	
		
		request.setAttribute("memberListCount", memberListCount);
		request.setAttribute("boardListCount", boardListCount);
		request.setAttribute("noticeListCount", noticeListCount);
		request.setAttribute("storeListCount", storeListCount);
		request.setAttribute("roomListCount", roomListCount);
		RequestDispatcher view =request.getRequestDispatcher("/WEB-INF/views/admin/siteStatisticsView.jsp"); 
		view.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
