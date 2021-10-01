package store.controller;

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
import store.model.vo.S_Image;
import store.model.vo.Store;
import store.service.StoreService;

/**
 * Servlet implementation class StoreUpdateServlet
 */
@WebServlet("/store/update")
public class StoreUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreUpdateServlet() {
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
		String savePath = root + "resources\\images\\store\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// Image 테이블 수정을 위한 작업
		String fileName = "file_img";
		String image_r_name = multiRequest.getParameter("image_r_name");
		
		S_Image s_image = new S_Image();
		
		if(multiRequest.getFilesystemName(fileName) != null) {
			// 첨부파일이 있는 경우
			s_image.setRoute("/resources/images/store/");
			s_image.setImage_name(multiRequest.getOriginalFileName(fileName));
			s_image.setImage_r_name(multiRequest.getFilesystemName(fileName));
			s_image.setFileLevel(0);
			
			// 식당정보 작성시 이미지 첨부가 필수-> 수정 전 저장된 파일이 항상 있으므로 db에서 update 처리하기 위해 지워야할 파일명 저장
			if(!image_r_name.equals(""))
				s_image.setS_deleteName(image_r_name);
		} 
		System.out.println("S_deleteName : " + s_image.getS_deleteName());
		System.out.println("s_image: " + s_image);
		// Store 수정할 내용 추출해서 설정
		Store s = new Store();
		int s_no = Integer.parseInt(multiRequest.getParameter("s_no"));
		s.setS_no(s_no);
		s.setS_name(multiRequest.getParameter("title"));
		s.setS_tel(multiRequest.getParameter("tel"));
		s.setS_address(multiRequest.getParameter("address"));
		s.setS_time(multiRequest.getParameter("time"));
		s.setMenu(multiRequest.getParameter("menu"));
		s.setS_image(s_image);
		
		System.out.println("수정할 : " + s);
		int result = new StoreService().updateStore(s);
		
		if(result > 0) {
			// 성공시 덮어 쓴 사진 서버상에서 삭제
			if(s_image.getS_deleteName() != null) {
				File deleteFile = new File(savePath + s_image.getS_deleteName());
				deleteFile.delete();
			}
			response.sendRedirect(request.getContextPath() + "/store/detail?s_no=" + s_no);
		} else {
			// 실패하면 수정하면서 저장한 사진 삭제
			File failedFile =new File(savePath + s_image.getImage_r_name());
			failedFile.delete();
			// request.setAttribute("msg", "식당정보 수정에 실패했습니다.");
			// request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("식당정보 수정에 실패했습니다.");
		}
	}

}






