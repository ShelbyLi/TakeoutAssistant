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
import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserToShop
 */
@WebServlet("/UserToShop")
public class UserToShop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserToShop() {
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
		
		ShopManager sm = new ShopManager();
		List<BeanShop> shops = new ArrayList<BeanShop>();
		try {
			String keyWord = request.getParameter("keyWord");
			if (keyWord != null) {
				// 模糊查找
				shops = sm.fuzzySearch(keyWord);
			} else {
				shops = sm.loadAll();
			}
			request.setAttribute("shops", shops);
			
//			// 建立该用户的订单 状态为ordering
//			OrderManager om = new OrderManager();
//			BeanOrderForm order = new BeanOrderForm();
//			order.setUser_id(cur_user.getUser_id());
//			order.setOrder_status(BeanOrderForm.ordering);
//			om.add(order);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("user_to_shop.jsp").forward(request, response);
	}

}
