package qna.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class qnaDetailServlet
 */
@WebServlet("/qna/detail")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		QnaService qs =new QnaService();	
		
		// 쿠키 이용해서 조회수 무한 증가 방지
		Cookie[] cookies = request.getCookies();
		
		String bcnt = "";
		
		if(cookies != null && cookies.length > 0) {
			// 쿠키 값의 배열을 반복하면서 탐색
			for(Cookie c : cookies) {
				// 읽은 게시물 정보를 저장한 쿠키의 이름 bcnt가 있는지 확인
				if(c.getName().equals("bcnt")) {
					bcnt = c.getValue();
				}
			}
		}
		
		// 처음 읽는 게시글
		if(bcnt.indexOf("|"+q_no+"|") == -1) {
			Cookie newBcnt = new Cookie("bcnt", bcnt + "|" + q_no + "|");
			
			response.addCookie(newBcnt);
			
			// 조회수 증가 로직
			int result = qs.increaseCount(q_no);
			
			if(result > 0) {
				// System.out.println("조회수 증가 성공");
			} else {
				// System.out.println("조회수 증가 실패");
			}
		} else {
			// System.out.println("다시 조회해서 조회수 증가 안 함");
		}
		
		// 게시글 조회
		Qna qna = qs.selectQna(q_no);
		
		System.out.println(request.getSession().getAttribute("loginUser"));
		System.out.println(qna);
		
		if(qna != null) {
			request.setAttribute("qna", qna);
			request.getRequestDispatcher("/WEB-INF/views/qna/qnaDetailView.jsp").forward(request, response);
		} else {
			// request.setAttribute("msg", "게시글 상세보기에 실패하였습니다.");
			// request.getRequestDispatcher("WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("게시판 상세보기 실패!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
	
	
}
