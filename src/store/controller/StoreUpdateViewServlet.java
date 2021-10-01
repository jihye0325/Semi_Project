package store.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import store.model.vo.Store;
import store.service.StoreService;

/**
 * Servlet implementation class StoreUpdateViewServlet
 */
@WebServlet("/store/updateView")
public class StoreUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreUpdateViewServlet() {
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
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		int user_no = 1;
		// ** 로그인시 관리자 확인**
		//user_no = Integer.parseInt(request.getParameter("user_no"));
		
		Store s = new StoreService().selectStore(s_no, user_no);
		System.out.println("update : " + s);
		if(s != null) {
			request.setAttribute("s", s);
			request.getRequestDispatcher("/WEB-INF/views/store/storeUpdateView.jsp").forward(request, response);
		} else {
			// request.setAttribute("msg", "수정할 식당정보를 불러오는데 실패했습니다.");
			// request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
		}
	}

}
