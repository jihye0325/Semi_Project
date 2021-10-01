package store.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import member.model.vo.Member;
import store.model.vo.S_Reply;
import store.service.StoreService;

/**
 * Servlet implementation class StoreReplyDeleteServlet
 */
@WebServlet("/store/deleteReply")
public class StoreReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreReplyDeleteServlet() {
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
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		System.out.println(s_no);
		System.out.println(c_no);
		int user_no = ((Member)(request.getSession().getAttribute("loginUser"))).getUserNo();
		
		List<S_Reply> s_replyList = new StoreService().deleteReply(s_no, c_no);
		System.out.println("s_replyList :" + s_replyList);
		// GSON 라이브러리 lib에 추가 후 replyList 응답
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(s_replyList, response.getWriter());
	}

}
