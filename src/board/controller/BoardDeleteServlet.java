package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
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
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		int bid = Integer.parseInt(request.getParameter("bid"));
		int result = 0;
		String bMenu = null;
		
		if(bid < 20) {
			bMenu = "accompany";
		} else if(bid >= 20) {
			bMenu = "review";
		}
		
		// 이미지가 있는 게시물인 경우 이미지도 삭제
		if(request.getParameter("changeName") == null) {
			result = new BoardService().deleteBoard(bno);
		} else {
			String root = request.getSession().getServletContext().getRealPath("/");
			String savePath = root + "resources\\uploadFiles\\";
			String[] changeNames = request.getParameterValues("changeName");						
			
			result = new BoardService().deleteFiles(bno, changeNames);
			
			if(result > 0) {
				for(int i = 0; i < changeNames.length; i++) {
					File deleteFile = new File(savePath + changeNames[i]);
					deleteFile.delete();
				}
			}
		}
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시물이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/board/" + bMenu+ "/list");
		} else {
			response.sendRedirect(request.getContextPath() + "/board/" + bMenu+ "/list");
		}
	}

}
