package kt.controller.zabbix;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import kt.common.JsonMapper;
import kt.dao.HistoryDAO;
import kt.vo.Graph;
import kt.vo.Item;

/**
 * Servlet implementation class HistoryJsonListServlet
 */
@WebServlet("/zabbix/history/json/list")
public class HistoryJsonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryJsonListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HistoryDAO historyDAO = new HistoryDAO();
		
		Item item = new Item();
		item.setItemid(Long.parseLong(request.getParameter("itemid")));
		item.setValueType(Integer.parseInt(request.getParameter("valuetype")));
		
		JsonMapper<Graph> mapper = new JsonMapper<>();
		try {
			JSONArray jsonArray = mapper.getJSONArray(historyDAO.selectJsonHistoryListByItem(item));
			out.print(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
