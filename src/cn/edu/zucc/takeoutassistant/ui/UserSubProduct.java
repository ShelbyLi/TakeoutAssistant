package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.OrderDetailManager;
import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanOrderDetail;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserSubProduct
 */
@WebServlet("/UserSubProduct")
public class UserSubProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSubProduct() {
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
		
		BeanUser cur_user = new BeanUser();
		cur_user = (BeanUser) session.getAttribute("cur_user");
		BeanShop cur_entered_shop = new BeanShop();
		cur_entered_shop = (BeanShop) session.getAttribute("cur_entered_shop");
		
		// 改orderdetails
		OrderDetailManager odm = new OrderDetailManager();
		BeanOrderDetail orderdetail = new BeanOrderDetail();
		orderdetail.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		try {
			OrderManager om = new OrderManager();
			int order_id = om.getCartId(cur_user.getUser_id(), cur_entered_shop.getShop_id());
			orderdetail.setOrder_id(order_id);
			
//			odm.plusOne(orderdetail);
			odm.minusOne(orderdetail);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("UserEnterShop?shop_id="+cur_entered_shop.getShop_id()).forward(request, response);
		
	}

}
