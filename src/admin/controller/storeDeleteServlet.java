package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import admin.model.service.storeAllService;


/**
 * Servlet implementation class memberDeleteServlet
 */
@WebServlet("/admin/deleteStore")
public class storeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public storeDeleteServlet() {
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
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		
		int result = new storeAllService().deleteStore(s_no);
		
		if(result>0) {
			request.getSession().setAttribute("msg", "삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/storeAllList");
		}else {
			request.getSession().setAttribute("msg", "회원 삭제 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
