package qna.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;

/**
 * Servlet implementation class qnaDeleteServlet
 */
@WebServlet("/qna/delete")
public class QnaDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDeleteServlet() {
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
		int q_no = Integer.parseInt(request.getParameter("q_no"));
		String image_r_name = request.getParameter("image_r_name");
		
		int result = new QnaService().deleteQna(q_no);
		
		if(result > 0) {
			// 서버에 저장된 사진 삭제
			String root = request.getSession().getServletContext().getRealPath("/");
			File failedFile = new File(root + "\\resources\\images\\qna\\" + image_r_name);
			failedFile.delete();
			
			request.getSession().setAttribute("msg", "게시글이 삭제되었습니다");
			response.sendRedirect(request.getContextPath() + "/qna/list");
		} else {
			request.setAttribute("msg", "게시글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("게시글삭제실패");
		}
		
		
		
		
	}

}
