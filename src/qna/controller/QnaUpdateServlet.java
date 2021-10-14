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
import qna.model.service.QnaService;
import qna.model.vo.Q_Image;
import qna.model.vo.Qna;

/**
 * Servlet implementation class qnaUpdateServlet
 */
@WebServlet("/qna/update")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
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
			request.setAttribute("msg", "잘못 된 전송입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			return;
		}
		
		int maxSize = 1024*1024*10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\images\\qna\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize,"UTF-8", new MyFileRenamePolicy());
		
		// Image 테이블 수정을 위한 작업
		String fileName = "file_img";
		String image_r_name = multiRequest.getParameter("image_r_name");
		
		Q_Image q_image = new Q_Image();
		
		if(multiRequest.getFilesystemName(fileName) != null) {
			// 첨부 파일이 있는 경우
			q_image.setRoute("/resources/images/qna/");
			q_image.setImage_name(multiRequest.getOriginalFileName(fileName));	// 실제 업로드시 파일명
			q_image.setImage_r_name(multiRequest.getFilesystemName(fileName));	// rename된 파일명
			q_image.setFileLevel(0);
 
			// 원래 저장된 파일이 있었다면 db에서 update 처리하기 위해 지워야할 파일명 저장
			// 원래 저장된 파일이 없다면 db에서 insert 처리
			if(!image_r_name.equals(""))
				q_image.setQ_deleteName(image_r_name);
		}

		// Qna 수정할 내용 추출해서 설정
		Qna q = new Qna();
		int q_no = Integer.parseInt(multiRequest.getParameter("q_no"));
		q.setQ_no(q_no);
		q.setQ_cid(Integer.parseInt(multiRequest.getParameter("category")));
		q.setQ_title(multiRequest.getParameter("q_title"));
		q.setQ_content(multiRequest.getParameter("q_content"));
		q.setOpen_status(multiRequest.getParameter("open_status"));
		q.setQ_image(q_image);
		
		System.out.println("수정할 : " + q);
		int result = new QnaService().updateQna(q);
		
		if(result > 0) {
			// 성공하면 덮어 쓴 사진 서버상에서 삭제
			if(q_image.getQ_deleteName() != null) {
				File deleteFile = new File(savePath + q_image.getQ_deleteName());
				deleteFile.delete();
			}
			request.getSession().setAttribute("msg", "게시글이 수정되었습니다");
			response.sendRedirect(request.getContextPath() + "/qna/detail?q_no=" + q_no);
		
		} else {
			// 실패하면 수정하면서 저장한 사진 삭제
			File failedFile = new File(savePath + q_image.getImage_r_name());
			failedFile.delete();
			request.setAttribute("msg", "게시글 수정에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("게시글 수정에 실패했습니다.");
		}
	}

}
