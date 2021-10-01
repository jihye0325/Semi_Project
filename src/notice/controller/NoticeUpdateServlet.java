package notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		int nno = Integer.parseInt(request.getParameter("nno"));
		String nTitle = request.getParameter("title");
		String nContent = request.getParameter("editordata");

		Notice n = new Notice();
		n.setNno(nno);
		n.setnTitle(nTitle);
		n.setnContent(nContent);
		// System.out.println(n);
		int result = new NoticeService().updateNotice(n);
		// System.out.println(result);
		if(result > 0) {
			request.getSession().setAttribute("msg", "공지사항 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath() + "/notice/detail?nno=" + nno);
		} else {
			response.sendRedirect(request.getContextPath() + "/home");
		}
	}

}
