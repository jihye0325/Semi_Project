package admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.model.service.MemberManagementService;
import admin.model.vo.Member;

/**
 * Servlet implementation class updateMemberAuthorityServlet
 */
@WebServlet("/admin/update")
public class updateMemberAuthorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMemberAuthorityServlet() {
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
		
		int authority = Integer.parseInt(request.getParameter("authority"));
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		
		//System.out.println("authority : " + authority);
		//System.out.println("user_no : " + user_no);
		
		int result = new MemberManagementService().updateMemberAuthority(authority, user_no);
		
		//System.out.println("result : " + result);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "권한 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/memberdetail?user_no="+user_no);
		}else {
			request.getSession().setAttribute("msg", "권한 수정이 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			
		}
	}

}
