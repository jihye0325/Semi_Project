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

/**
 * Servlet implementation class BoardReviewUpdateServlet
 */
@WebServlet("/board/review/update")
public class BoardReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReviewUpdateServlet() {
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
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.getRequestDispatcher("/WEB-INF/views/board/boardReviewListView.jsp").forward(request, response);
		}
		
		int maxSize = 1024*1024*20;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
															 new MyFileRenamePolicy());
		
		List<Attachment> imgList = new ArrayList<>();
		String[] fileNames = {"contentImg1", "contentImg2", "contentImg3"};
		
		String[] changeNames = null;
		if(multiRequest.getParameter("changeName") != null) {
			changeNames = multiRequest.getParameterValues("changeName");
		}
		
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
			if(changeNames != null) {
				at.setDeletedName(changeNames[i]);
			}
			imgList.add(at);
		}
		
		int bno = Integer.parseInt(multiRequest.getParameter("bno"));
		int bid = Integer.parseInt(multiRequest.getParameter("btype"));
		String bTitle = multiRequest.getParameter("title");
		String bContent = multiRequest.getParameter("content");
		String[] tags = multiRequest.getParameterValues("tag");
		String btag = tags[0];
		for(int i = 1; i < tags.length; i++) {
			btag += "," + tags[i];
		}
		
		Board b= new Board(bno, bTitle, bid, bContent, imgList, btag);
		
		int result = new BoardService().updateReview(b);
		
		if(result > 0) {
			// 덮어쓰기 된 기존의 사진 삭제
			for(Attachment at : imgList) {
				if(at.getDeletedName() != null) {
					File deleteFile = new File(savePath + at.getDeletedName());
					deleteFile.delete();
				}
			}
			request.getSession().setAttribute("msg", "게시글 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/board/review/detail?bno=" + bno);
		} else {
			// 실패시 새로 저장한 사진 삭제
			for(Attachment at : imgList) {
				File failedFile = new File(savePath + at.getReName());
				failedFile.delete();
			}
			response.sendRedirect(request.getContextPath() + "/board/review/list");
		}
	}

}
