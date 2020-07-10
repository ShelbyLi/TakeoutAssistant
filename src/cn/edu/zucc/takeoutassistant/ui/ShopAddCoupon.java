package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.CouponManager;
import cn.edu.zucc.takeoutassistant.control.FullreductionSchemeManager;
import cn.edu.zucc.takeoutassistant.model.BeanCoupon;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopAddCoupon
 */
@WebServlet("/ShopAddCoupon")
public class ShopAddCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopAddCoupon() {
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
		BeanShop cur_shop = new BeanShop();
		BeanCoupon coupon = new BeanCoupon();
		CouponManager cm = new CouponManager();
		
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		coupon.setShop_id(cur_shop.getShop_id());
		coupon.setCoupon_amount(Double.parseDouble(request.getParameter("coupon_amount")));
		coupon.setCoupon_ordered_number_requirement(Integer.parseInt(request.getParameter("coupon_ordered_number_requirement")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			coupon.setCoupon_start_time(sdf.parse(request.getParameter("coupon_start_time")));
			coupon.setCoupon_end_time(sdf.parse(request.getParameter("coupon_end_time")));
			System.out.println(request.getParameter("coupon_start_time"));
			System.out.println(request.getParameter("coupon_end_time"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		
		try {
			cm.add(coupon);
			request.getRequestDispatcher("ShopCoupon").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
