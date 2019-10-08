package kt.controller.zabbix;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kt.dao.ItemDAO;
import kt.vo.Host;

/**
 * Servlet implementation class ItemListServlet
 */
@WebServlet("/zabbix/item/list")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDAO itemDAO = new ItemDAO();
		
		Host host = new Host();
		host.setHostid(Long.parseLong(request.getParameter("hostid")));
		
		request.setAttribute("listItem", itemDAO.selectItemListByHost(host));
		request.setAttribute("host", host);
		request.setAttribute("viewUrl", "/zabbix/listItem.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
