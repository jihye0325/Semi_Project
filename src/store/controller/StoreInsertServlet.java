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
 * Servlet implementation class StoreInsertServlet
 */
@WebServlet("/store/insert")
public class StoreInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/store/storeInsertView.jsp").forward(request, response);
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
		// System.out.println(root);
		
		// 파일 실제 저장경로
		String savePath = root + "resources\\images\\store\\";
		
		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		// Image 테이블에 값 삽입하기 위한 작업
		S_Image s_image = new S_Image();
		String fileName = "file_img";
		
		while(true) { 
			if(fileName == null)
				continue;
			
			s_image.setRoute("/resources/images/store/");
			s_image.setImage_name(multiRequest.getOriginalFileName(fileName));
			s_image.setImage_r_name(multiRequest.getFilesystemName(fileName));
			s_image.setFileLevel(0);
			
			break;		
		}
		
		// Store와 Image 테이블에 값 삽입
		int s_no = 0;
		String title = multiRequest.getParameter("title");
		String tel = multiRequest.getParameter("tel");
		String address =multiRequest.getParameter("address");
		String time = multiRequest.getParameter("time");
		String menu = multiRequest.getParameter("menu");
		
		Store s = new Store(s_no, title, tel, address, time, menu, s_image);
		// System.out.println("insert store: " + s);
		// System.out.println("insert :" + s.getS_no());
		
		int result = new StoreService().insertStore(s);
		
		if(result > 0) {
			// 작성후 목록 (작성한 글 확인할 수 있게 최신순으로 정렬한 식당리스트 페이지로)
			response.sendRedirect(request.getContextPath() + "/store/list?array=date");
			// ** 작성한 글로  -> 방금 등록한 식당정보의 s_no를 알아오는 방법?? **
			// response.sendRedirect(request.getContextPath() + "/store/detail?s_no=" + s.getS_no());
			
		} else {
			// 로직 수행 실패 시 저장했던 파일 삭제
			File failedFile = new File(savePath + s_image.getImage_r_name());
			failedFile.delete();
			
			//request.setAttribute("msg", "식당정보 등록에 실패하였습니다.");
			//request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
			System.out.println("식당정보 등록에 실패하였습니다.");
		}
	}

}
