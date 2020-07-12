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

import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.control.ProductManager;
import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserEnterShop
 */
@WebServlet("/UserEnterShop")
public class UserEnterShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEnterShop() {
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
		
		ProductcategoryManager pcm = new ProductcategoryManager();
		List<BeanProductCategory> productcategorys = new ArrayList<BeanProductCategory>();
		
		try {
			ShopManager sm = new ShopManager();
			BeanShop shop = new BeanShop();
			int shop_id = Integer.parseInt(request.getParameter("shop_id"));
			shop = sm.getDetails(shop_id);
//			request.setAttribute("shop", shop);  // 设置当前shop参数
			session.setAttribute("cur_entered_shop", shop);
			
			String keyWord = request.getParameter("keyWord");
			if (keyWord != null) {
//				// 模糊查找
				productcategorys = pcm.fuzzySearch(keyWord, shop_id);
			} else {
				productcategorys = pcm.loadAll(shop_id);
			}
			request.setAttribute("productcategorys", productcategorys);
			
			
			// 建立该用户在这家店的购物车(订单 状态为ordering)
			OrderManager om = new OrderManager();
			BeanOrderForm order = new BeanOrderForm();
			order.setUser_id(cur_user.getUser_id());
			order.setOrder_status(BeanOrderForm.ordering);
			order.setShop_id(shop_id);
			om.add(order);
			
			// 更新用户购物车
//			OrderManager om = new OrderManager();
//			om.updateShopId(cur_user.getUser_id(), shop_id);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("user_entered_shop2.jsp").forward(request, response);
	}

}
