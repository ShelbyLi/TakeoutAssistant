package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.control.RiderDeliverOrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanRiderDeliverOrder;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RiderDeliveryRecieve
 */
@WebServlet("/RiderDeliveryRecieve")
public class RiderDeliveryRecieve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderDeliveryRecieve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		BeanRider cur_rider = new BeanRider();
		cur_rider = (BeanRider) session.getAttribute("cur_rider");
		System.out.println(cur_rider);
		System.out.println(request.getParameter("order_id"));
		
		RiderDeliverOrderManager rdom = new RiderDeliverOrderManager();
		OrderManager om = new OrderManager();
		try {
			om.recieved(Integer.parseInt(request.getParameter("order_id")));
			
			BeanRiderDeliverOrder riderdeliverorder = new BeanRiderDeliverOrder();
			riderdeliverorder.setOrder_id(Integer.parseInt(request.getParameter("order_id")));
			riderdeliverorder.setRider_id(cur_rider.getRider_id());
			rdom.add(riderdeliverorder);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("RiderToReceive").forward(request, response);
	}

}
