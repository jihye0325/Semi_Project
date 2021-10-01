package qna.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;
import member.model.vo.Member;
import qna.model.service.QnaService;
import qna.model.vo.Q_Image;
import qna.model.vo.Qna;

/**
 * Servlet implementation class qnaInsertServlet
 */
@WebServlet("/qna/insert")
public class QnaInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/qna/qnaInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("전송실패");
			//request.setAttribute("msg", "잘못 된 전송입니다.");
			//request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		// 전송 파일 용량 제한
		int maxSize = 1024*1024*10;
		
		// 웹 서버 컨테이너 경로 추출
		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println(root);
		
		// 파일 실제 저장경로
		String savePath = root + "resources\\images\\qna\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize,"UTF-8", new MyFileRenamePolicy());
		
		// Image 테이블에 값 삽입하기 위한 작업
		Q_Image q_image = new Q_Image();
		String fileName = "file_img";
		
		while(true) {
			if(fileName == null) 
				continue;
				
			q_image.setRoute("/resources/images/qna/");
			q_image.setImage_name(multiRequest.getOriginalFileName(fileName));	// 실제 업로드시 파일명
			q_image.setImage_r_name(multiRequest.getFilesystemName(fileName));	// rename된 파일명
			q_image.setFileLevel(0);
			
			break;
		}
		
		// QNA와  IMAGE 테이블에 값 삽입
		int cid = Integer.parseInt(multiRequest.getParameter("category"));
		String title = multiRequest.getParameter("title");
		String content = multiRequest.getParameter("content");
		String open_status = multiRequest.getParameter("open_status");
		
		if(open_status == null)
			open_status = "Y";
		
		System.out.println(open_status);
		
		//  로그인 유저에서 userno 알아오기
		int writer = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Qna q = new Qna(writer, cid, title, content, open_status, q_image);
		System.out.println(q);
		
		// qna 게시판 작성 비지니스 로직을 처리할 서비스 요청
		int result = new QnaService().insertQna(q);
		
		if(result > 0) {
			// 작성후 목록으로 재요청
			response.sendRedirect(request.getContextPath() + "/qna/list");
		} else {
			// 로직 수행 실패 시 저장되었던 파일 삭제
			File failedFile = new File(savePath + q_image.getImage_r_name());
			failedFile.delete();
		
			//request.setAttribute("msg", "1:1 문의게시판  게시글 등록에 실패하였습니다.");
			//request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("1:1 문의게시판  게시글 등록에 실패하였습니다.");
		} 
	}
}





