package member.controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet(name = "MemberModifyServlet", urlPatterns = "/memberModify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberModifyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		Member tempUser = new Member(1, "user1", "user1", "홍길동", "010-0000-0000", "M", "길동",
//				"07340, 영등포구 국제금융로7길 20, 18동");
		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		if (loginUser != null) {
			request.getRequestDispatcher("/WEB-INF/views/mypage/modifyMember.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("msg", "로그인이 필요합니다.");
			response.sendRedirect(request.getContextPath());
		}
		// 로그인 상태일 때 modifyForm
		// 비로그인 시 에러페이지
	}

	/*
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userPwd = request.getParameter("userPwd");
		String phone = request.getParameter("tel");
		String nickname = request.getParameter("nickName");
		String[] addressArr = request.getParameterValues("address");
		String address = String.join(", ", addressArr);

		Member loginUser = (Member) request.getSession().getAttribute("loginUser");
		int userNo = loginUser.getUserNo();

		Member m = new Member(userNo, userPwd, phone, nickname, address);

		Member updateMember = new MemberService().updateMember(m);

		if (updateMember != null) {
			request.getSession().setAttribute("loginUser", updateMember);
			request.getSession().setAttribute("msg", "회원정보 수정이 완료됐습니다.");
			response.sendRedirect(request.getContextPath() + "/memberModify");
		} else {
			request.setAttribute("msg", "회원정보 수정에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/mainpage.jsp").forward(request, response);
		}
	}
}