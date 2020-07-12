package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.DeliveryAddrManager;
import cn.edu.zucc.takeoutassistant.control.OrderDetailManager;
import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanDeliveryAddr;
import cn.edu.zucc.takeoutassistant.model.BeanOrderDetail;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserOrderDetail
 */
@WebServlet("/UserOrderDetail")
public class UserOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserOrderDetail() {
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
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		System.out.print(request.getParameter("order_id"));
		try {
			// 加载订单详情
			OrderDetailManager odm = new OrderDetailManager();
			List<BeanOrderDetail> orderdetails = new ArrayList<BeanOrderDetail>();
			orderdetails = odm.loadAllByUser(Integer.parseInt(request.getParameter("order_id")));
			// 查找地址
			DeliveryAddrManager dam = new DeliveryAddrManager();
			BeanDeliveryAddr addr = new BeanDeliveryAddr();
			addr = dam.search(Integer.parseInt(request.getParameter("order_addr_id")));
			
			request.setAttribute("orderdetails", orderdetails);
			request.setAttribute("addr", addr);
			request.setAttribute("order_id", request.getParameter("order_id"));  // 在评价提交时使用
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("user_order_details.jsp").forward(request, response);
	}

}
