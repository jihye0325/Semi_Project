package board.controller;

import java.io.File;
import java.io.IOException;
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
 * Servlet implementation class BoardReviewInsertServlet
 */
@WebServlet("/board/review/insert")
public class BoardReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardReviewInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 게시글 분류  20 : 식당, 30 : 숙소, 40 : 기타
		
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.getRequestDispatcher("/WEB-INF/views/board/boardReviewListView.jsp").forward(request, response);
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
		int bid = Integer.parseInt(multiRequest.getParameter("btype"));
		String bTitle = multiRequest.getParameter("title");
		String bContent = multiRequest.getParameter("content");
		String[] tags = multiRequest.getParameterValues("tag");
		String btag = tags[0];
		for(int i = 1; i < tags.length; i++) {
			btag += "," + tags[i];
		}
		
//		System.out.println(btag);
//		String[] newTags = btag.split(",");
//		System.out.println(Arrays.toString(newTags));
		
		Board b = new Board(bid, userNo, bTitle, bContent,  imgList, btag);
		
		int result = new BoardService().insertReview(b);
		
		if(result > 0) {
			request.getSession().setAttribute("msg", "게시글이 등록되었습니다.");
			response.sendRedirect(request.getContextPath() + "/board/review/list");
		} else {
			for(int i = 0; i < imgList.size(); i++) {
				File failedFile = new File(savePath + imgList.get(i).getReName());
				failedFile.delete();
			}
			// 임의로 게시판 목록 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/board/review/list");
		}
	}

}
