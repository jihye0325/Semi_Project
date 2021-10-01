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
 * Servlet implementation class StoreReplyUpdateServlet
 */
@WebServlet("/store/updateReply")
public class StoreReplyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreReplyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		String content = request.getParameter("content");
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		// ** 로그인 시 userno **
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		// int writer = 1;
		
		S_Reply r = new S_Reply();
		r.setS_no(s_no);
		r.setComment_info(content);
		r.setUser_no(writer);
		r.setComment1_no(c_no);
				
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().create();
		new Gson().toJson(r, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int s_no = Integer.parseInt(request.getParameter("s_no"));
		String content = request.getParameter("content");
		int score = Integer.parseInt(request.getParameter("score"));
		int c_no = Integer.parseInt(request.getParameter("c_no"));
		// ** 로그인 시 userno **
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		// int writer = 1;
		
		S_Reply r = new S_Reply();
		r.setS_no(s_no);
		r.setComment_info(content);
		r.setUser_no(writer);
		r.setScore(score);
		r.setComment1_no(c_no);
		
		List<S_Reply> s_replyList = new StoreService().updateReply(r);
		
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yy.MM.dd HH:mm:ss").create();
		gson.toJson(s_replyList, response.getWriter());
	}

}
