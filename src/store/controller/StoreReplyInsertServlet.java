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
 * Servlet implementation class StoreReplyInsertServlet
 */
@WebServlet("/store/insertReply")
public class StoreReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = request.getParameter("content");
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(content, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		String content = request.getParameter("content");
		int score = Integer.parseInt(request.getParameter("score"));
		// ** 로그인 시 userno **
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		// int writer = 1;
		
		S_Reply r = new S_Reply();
		r.setS_no(s_no);
		r.setComment_info(content);
		r.setUser_no(writer);
		r.setScore(score);
		
		List<S_Reply> s_replyList = new StoreService().insertReply(r);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(s_replyList, response.getWriter());
	}

}
