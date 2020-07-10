package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.OrderDetailManager;
import cn.edu.zucc.takeoutassistant.model.BeanOrderDetail;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopOrderDetail
 */
@WebServlet("/ShopOrderDetail")
public class ShopOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopOrderDetail() {
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
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		OrderDetailManager odm = new OrderDetailManager();
		List<BeanOrderDetail> orderdetails = new ArrayList<BeanOrderDetail>();
//		for (BeanOrderForm item: orders) {
//			System.out.println(item.getOrder_actual_amount());
//			System.out.println(item.getOrder_original_amount());
//			System.out.println(item.getOrder_time());
//		}
//		BeanShop cur_shop = new BeanShop();
//		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		try {
			System.out.println(request.getParameter("order_id"));
			orderdetails = odm.loadAll(Integer.parseInt(request.getParameter("order_id")));
			request.setAttribute("orderdetails", orderdetails);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("shop_order_details.jsp").forward(request, response);
	}

}
