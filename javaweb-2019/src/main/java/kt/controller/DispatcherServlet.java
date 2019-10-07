package kt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispatcherServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		String servletPath = req.getServletPath();
		
		try {
			String pageControllerPath = null;
			
			if ("/hostList.do".equals(servletPath)) {
				pageControllerPath = "/hostList";
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(pageControllerPath);
			rd.include(req, resp);
			
			String viewUrl = (String) req.getAttribute("viewUrl");
			
			if (viewUrl.startsWith("redirect:")) {
				resp.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				rd = req.getRequestDispatcher(viewUrl);
				rd.include(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
