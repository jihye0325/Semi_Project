package mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.model.vo.Room;
import member.model.vo.Member;
import mypage.model.service.MypageService;
import store.model.vo.Store;

/**
 * Servlet implementation class WishlistServlet
 */
@WebServlet("/mypage/wishlist")
public class WishlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WishlistServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userNo = ((Member) request.getSession().getAttribute("loginUser")).getUserNo();
		List<Room> roomList = new MypageService().selectRoomWishList(userNo);
		List<Store> storeList = new MypageService().selectStoreWishList(userNo);
		request.setAttribute("roomList", roomList);
		request.setAttribute("storeList", storeList);
		System.out.println(storeList);
		request.getRequestDispatcher("/WEB-INF/views/mypage/wishlist.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
