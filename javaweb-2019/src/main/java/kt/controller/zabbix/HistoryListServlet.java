package kt.controller.zabbix;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kt.dao.HistoryDAO;
import kt.vo.Host;
import kt.vo.Item;

/**
 * Servlet implementation class HistoryListServlet
 */
@WebServlet("/zabbix/history/list")
public class HistoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HistoryDAO historyDAO = new HistoryDAO();
		
		Item item = new Item();
		item.setItemid(Long.parseLong(request.getParameter("itemid")));
		item.setValueType(Integer.parseInt(request.getParameter("valuetype")));
		
		Host host = new Host();
		host.setHostid(Long.parseLong(request.getParameter("hostid")));
		
		request.setAttribute("listHistory", historyDAO.selectHistoryListByItem(item));
		request.setAttribute("item", item);
		request.setAttribute("host", host);
		request.setAttribute("viewUrl", "/zabbix/listHistory.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
