package mypage.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.StartDocument;

import com.oreilly.servlet.MultipartRequest;

import board.model.vo.Attachment;
import booking.model.service.RoomService;
import booking.model.vo.R_Attachment;
import booking.model.vo.Room;
import common.MyFileRenamePolicy;
import member.model.vo.Member;
import mypage.model.service.MypageService;

/**
 * Servlet implementation class MyRoomInsert
 */
@WebServlet("/mypage/roominsert")
public class MyRoomInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyRoomInsert() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/mypage/myRoomInsert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int maxSize = 1024 * 1024 * 10;
		String root = request.getSession().getServletContext().getRealPath("/");
		String savePath = root + "resources\\uploadFiles\\";

		MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
				new MyFileRenamePolicy());

		List<R_Attachment> photoList = new ArrayList<>();
		String[] fileNames = { "thumbnail", "contentImg1", "contentImg2", "contentImg3" };
		for (int i = 0; i < fileNames.length; i++) {
			// contentImg의 경우 optional이므로 파일이 첨부되지 않았다면 아래 코드를 수행하지 않도록
			if (multiRequest.getFilesystemName(fileNames[i]) == null)
				continue;

			R_Attachment at = new R_Attachment();
			at.setRoute("/resources/uploadFiles/");
			at.setImage_name(multiRequest.getOriginalFileName(fileNames[i]));
			at.setImage_r_name(multiRequest.getFilesystemName(fileNames[i]));
			if (i == 0) {


				at.setImage_level(0); // thummbnail인 경우 file_level -> 0
			} else {
				at.setImage_level(1); // contentImg 인 경우 file_level -> 1

			}
			photoList.add(at);
		}

		int userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();
		
		String title = multiRequest.getParameter("title");
		String address = multiRequest.getParameter("address");
		String tel = multiRequest.getParameter("tel");
		String content = multiRequest.getParameter("content");
		int pay = Integer.parseInt(multiRequest.getParameter("pay"));
		int people_num = Integer.parseInt(multiRequest.getParameter("max_people"));

		String startDate = multiRequest.getParameter("start");
		String endDate = multiRequest.getParameter("end");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null, end = null;
		try {
			start = df.parse(startDate);
			end = df.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Room room = new Room(title, tel, content, pay, address, userNo, start, end, photoList, people_num);


		int result = new MypageService().insertRoom(room, startDate, endDate);

		if (result > 0) {
			request.getSession().setAttribute("msg", "숙소 등록에 성공했습니다.");
			response.sendRedirect(request.getContextPath() + "/mypage/myroom");
		} else {
			for (int i = 0; i < photoList.size(); i++) {
				File failedFile = new File(savePath + photoList.get(i).getImage_r_name());
				failedFile.delete();
			}
			request.getSession().setAttribute("msg", "숙소 등록에 실패했습니다.");
			response.sendRedirect(request.getContextPath()+"/mypage/myroom");
		}
	}

}
