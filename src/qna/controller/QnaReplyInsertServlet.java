package qna.controller;

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
import qna.model.service.QnaService;
import qna.model.vo.Q_Reply;

/**
 * Servlet implementation class QnaReplyInsertServlet
 */
@WebServlet("/qna/insertReply")
public class QnaReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaReplyInsertServlet() {
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
		// 넘어온 파라미터 추출
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		String c_content = request.getParameter("q_content");
		// 로그인 시 userno 
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		// int writer = 2;
		Q_Reply r = new Q_Reply();
		r.setQ_no(q_no);
		r.setC_comment(c_content);
		r.setUser_no(writer);
		
		// Q_Reply 객체 전달하여 insert 하고 현재 게시글의 replyList 리턴
		List<Q_Reply> q_replyList = new QnaService().insertReply(r);
		
		// GSON 라이브러리 lib에 추가 후 replyList 응답
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy.MM.dd HH:mm:ss").create();
		gson.toJson(q_replyList, response.getWriter());
	}

}
