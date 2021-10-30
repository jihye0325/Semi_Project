package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
 @WebFilter("/*")
public class LoginCheckFilter implements Filter {
	private List<String> permitList;
	private List<String> resourceList;
	
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hreq = (HttpServletRequest) request;
		String uri = hreq.getRequestURI();
		
		if (!permitList.contains(uri)) {
			boolean isResourceFile = false;
			for (String str : resourceList) {
				if (uri.contains(str)) {
					isResourceFile = true;
					break;
				}
			}
			if (!isResourceFile) {
				Member loginUser = (Member) hreq.getSession().getAttribute("loginUser");
				if (loginUser == null) {
					hreq.getSession().setAttribute("msg", "로그인이 필요한 서비스입니다.");
					hreq.getRequestDispatcher("/WEB-INF/views/member/loginpage.jsp").forward(request, response);
					return;
				}
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		permitList = new ArrayList<>();
		permitList.add("/UnI_JeJu/");
		permitList.add("/UnI_JeJu/index.jsp");
		permitList.add("/UnI_JeJu/home");
		permitList.add("/UnI_JeJu/login");
		permitList.add("/UnI_JeJu/idCheck");
		permitList.add("/UnI_JeJu/memberJoin");
		permitList.add("/UnI_JeJu/findId");
		permitList.add("/UnI_JeJu/findIdAfter");
		permitList.add("/UnI_JeJu/findPwd");
		permitList.add("/UnI_JeJu/findPwdAfter");
		permitList.add("/UnI_JeJu/store/list");
		permitList.add("/UnI_JeJu/board/accompany/list");
		permitList.add("/UnI_JeJu/board/review/list");
		permitList.add("/UnI_JeJu/notice/list");
		permitList.add("/UnI_JeJu/gallery/list");
		
		resourceList = new ArrayList<>();
		resourceList.add("/resources/");
	}
}