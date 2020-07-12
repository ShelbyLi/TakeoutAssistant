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

import cn.edu.zucc.takeoutassistant.control.CouponManager;
import cn.edu.zucc.takeoutassistant.control.DeliveryAddrManager;
import cn.edu.zucc.takeoutassistant.control.FullreductionSchemeManager;
import cn.edu.zucc.takeoutassistant.control.OrderDetailManager;
import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanCoupon;
import cn.edu.zucc.takeoutassistant.model.BeanDeliveryAddr;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanOrderDetail;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserCart
 */
@WebServlet("/UserCart")
public class UserCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCart() {
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
		BeanShop cur_entered_shop = (BeanShop) session.getAttribute("cur_entered_shop");
		
		// 加载订单
		OrderDetailManager odm = new OrderDetailManager();
		List<BeanOrderDetail> orderdetails = new ArrayList<BeanOrderDetail>();
		try {
			orderdetails = odm.loadCart(cur_user.getUser_id(), cur_entered_shop.getShop_id());
			request.setAttribute("orderdetails", orderdetails);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		// 加载地址
		DeliveryAddrManager dam = new DeliveryAddrManager();
		List<BeanDeliveryAddr> addrs = new ArrayList<BeanDeliveryAddr>();
		cur_user = (BeanUser) session.getAttribute("cur_user");
		try {
			addrs = dam.loadAll(cur_user.getUser_id());
			request.setAttribute("addrs", addrs);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		// 加载满减方案
		OrderManager om = new OrderManager();
		double original_amount = 0;
		try {
			original_amount = om.searchOriginalAmount(cur_user.getUser_id(), cur_entered_shop.getShop_id());
			System.out.println(original_amount);
		} catch (BaseException e1) {
			e1.printStackTrace();
		}
		
		FullreductionSchemeManager frm = new FullreductionSchemeManager();
		List<BeanFullReductionScheme> fullreductions1 = new ArrayList<BeanFullReductionScheme>();
		BeanFullReductionScheme fullreduction = new BeanFullReductionScheme();
		try {
			fullreductions1 = frm.loadAll(cur_entered_shop.getShop_id());
			for (BeanFullReductionScheme item: fullreductions1) {
				if (item.getFullreduction_amount() <= original_amount && fullreduction.getFullreduction_amount() < item.getFullreduction_amount()) {
					fullreduction = item;  // 找出符合满减方案的最大金额
				}
			}
			om.updateFullreductionID(cur_user.getUser_id(), cur_entered_shop.getShop_id(), fullreduction.getFullreduction_id());
			// 计算出满减方案后直接更新数据库
			request.setAttribute("fullreduction", fullreduction);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		
		// 能和优惠券叠加使用 则加载
		if (fullreduction.getFullreduction_can_superimposed_with_coupons() == BeanFullReductionScheme.can_superimosed_with_coupons) {
			request.setAttribute("hint", "还能使用优惠券! 如果没有可以集单送券哦");
			
			CouponManager cm = new CouponManager();
			List<BeanCoupon> coupons = new ArrayList<BeanCoupon>();
			try {
				coupons = cm.loadAllAvailable(cur_user.getUser_id(), cur_entered_shop.getShop_id());
				request.setAttribute("coupons", coupons);
			} catch (BaseException e) {
				e.printStackTrace();
			}
		} else {
			request.setAttribute("hint", "该满减方案不能使用优惠券了");
		}
		
		request.getRequestDispatcher("user_cart.jsp").forward(request, response);
		
	}

}
