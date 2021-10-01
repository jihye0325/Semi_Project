package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.noticeAllService;
import admin.model.service.roomAllService;
import admin.model.service.storeAllService;


/**
 * Servlet implementation class memberDeleteServlet
 */
@WebServlet("/admin/deleteNotice")
public class noticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public noticeDeleteServlet() {
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
		int n_no = Integer.parseInt(request.getParameter("n_no"));
		
		int result = new noticeAllService().deleteNotice(n_no);
		//System.out.println("1 :"+result);
		if(result>0) {
			request.getSession().setAttribute("msg", "삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/noticeAllList");
		}else {
			request.getSession().setAttribute("msg", "회원 삭제 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
