package board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.service.BoardService;
import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class BoardAccompanyInsertServlet
 */
@WebServlet("/board/accompany/insert")
public class BoardAccompanyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAccompanyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardAccompanyInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 분류	0 : 공란, 10 : 동행구함
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.getRequestDispatcher("/WEB-INF/views/board/boardAccompanyListView.jsp").forward(request, response);
		}
		
		int maxSize = 1024*1024*10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
														new MyFileRenamePolicy());
		
		// 1. image 테이블에 insert 필요
		List<Attachment> imgList = new ArrayList<>();
		String[] fileNames = {"contentImg1", "contentImg2", "contentImg3"};
		for(int i = 0; i < fileNames.length; i++) {
			if(multiRequest.getFilesystemName(fileNames[i]) == null) continue;
			
			Attachment at = new Attachment();
			at.setRoute("/resources/uploadFiles/");
			at.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));
			at.setReName(multiRequest.getFilesystemName(fileNames[i]));
			if(i == 0) {
				at.setImgLevel(0);
			} else {
				at.setImgLevel(1);
			}
			imgList.add(at);
		}
		// 2. Board 테이블과 Board_Image 테이블에 insert 필요
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int bid = 0;
		if(multiRequest.getParameter("chk") != null)
			bid = Integer.parseInt(multiRequest.getParameter("chk"));
		String bTitle = multiRequest.getParameter("title");
		String positions = multiRequest.getParameter("positions");
		// System.out.println(positions);
		// datetime-local : 2021-09-15T13:09
		// date : 2021-09-15
		String date = "";
		if(multiRequest.getParameter("date") != null) {
			date = multiRequest.getParameter("date").replace("T", " ");			
		}
		// System.out.println(date);

		String bContent = multiRequest.getParameter("content");
		// int bwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// board 객체에서 만나는 날짜 Date로 입력받는 것 해결
		Board b = new Board(bid, userNo, bTitle, date, bContent, imgList, positions);
		
		int result = new BoardService().insertBoard(b);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/board/accompany/list");
		} else {
			for(int i = 0; i < imgList.size(); i++) {
				File failedFile = new File(savePath + imgList.get(i).getReName());
				failedFile.delete();
			}
			// 임의로 게시판 목록 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/board/accompany/list");
		}
	}

}
