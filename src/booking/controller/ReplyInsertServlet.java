package booking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import booking.model.service.RoomService;
import booking.model.vo.R_comment1;
import member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/booking/insertReply")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
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
		//1. 넘어온 값을 뽑아온다.
		int rno = Integer.parseInt(request.getParameter("rno"));
		String content = request.getParameter("content");
		int score = Integer.parseInt(request.getParameter("score"));
		//	System.out.println("좋아요: "+ score);
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		//int writer = 3;
		
		//private int refBid;//해당댓글이 어떤 게시글에 참조되고 있는지
		
		R_comment1 r = new R_comment1();
		r.setR_no(rno);
		r.setComment_info(content);
		r.setUser_no(writer);
		r.setScore(score);
		System.out.println("R_comment1 : "+r);
		
		List<R_comment1> replayList = new RoomService().insertReply(r);
		System.out.println("[22]replayList : " + replayList);
		response.setContentType("application/json; charset=utf-8"); 
		//객체 + 사용하고자하는 사용  스트림을 담아줌
		//new Gson().toJson(replayList, response.getWriter());
		Gson gson= new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(replayList, response.getWriter());
		
	}

}
