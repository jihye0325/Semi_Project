package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name="MemberJoinServlet", urlPatterns="/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/member/memberJoin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 회원가입 시 필요한 정보들(userId, userPwd, userName, phone, gender, nickName) 호출
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String nickName = request.getParameter("nickName");
		String[] addressArr = request.getParameterValues("address");
		
		String address = "";
		if(addressArr != null)
			address = String.join(", ", addressArr);
		
		// 2. 호출 정보 기반 Member 객체 생성
		Member m = new Member(userId, userPwd, userName, phone, gender, nickName, address);
		
		// 3. 전달값 service > insertMember 생성
		int result = new MemberService().insertMember(m);
		
		// 4. 전달값이 존재하면 가입완료 메세지, 샌딩
		//	  전달값이 존재하지 않다면 에러메세지, 에러페이지 포워딩
		if(result > 0) {
			request.getSession().setAttribute("msg", "회원가입이 완료됐습니다!");
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("msg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/member/memberJoin.jsp").forward(request, response);
		}
	}
}