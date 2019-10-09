package kt.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispatcherServlet
 */
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
    public void init(ServletConfig config) throws ServletException {
    	ServletContext sc = config.getServletContext();
    	sc.setAttribute("contextPath", sc.getContextPath());
    }

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String servletPath = req.getServletPath();
		
		try {
			String pageControllerPath = null;
			
			if ("/hostList.do".contentEquals(servletPath)) {
				pageControllerPath = "/hostList";
			} else if ("/zabbix/host/list.do".contentEquals(servletPath)) {
				pageControllerPath = "/zabbix/host/list";
			} else if ("/zabbix/item/list.do".contentEquals(servletPath)) {
				pageControllerPath = "/zabbix/item/list";
			} else if ("/zabbix/history/list.do".contentEquals(servletPath)) {
				pageControllerPath = "/zabbix/history/list";
			} else if ("/zabbix/history/json/list.do".contentEquals(servletPath)) {
				pageControllerPath = "/zabbix/history/json/list";
			}
			
			RequestDispatcher rd = req.getRequestDispatcher(pageControllerPath);
			rd.include(req, resp);
			
			String viewUrl = (String) req.getAttribute("viewUrl");
			
			if (viewUrl == null) {
				return;
			} else if (viewUrl.startsWith("redirect:")) {
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
