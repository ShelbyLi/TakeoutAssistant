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

import cn.edu.zucc.takeoutassistant.control.UserHoldCouponsManager;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.model.BeanUserHoldCoupons;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserCoupon
 */
@WebServlet("/UserCoupon")
public class UserCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCoupon() {
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
		
		UserHoldCouponsManager uhcm = new UserHoldCouponsManager();
		List<BeanUserHoldCoupons> user_hold_coupons = new ArrayList<BeanUserHoldCoupons>();
		BeanUser cur_user = new BeanUser();
		cur_user = (BeanUser) session.getAttribute("cur_user");
		try {
//			System.out.println(cur_user.getUser_id());
			user_hold_coupons = uhcm.loadAll(cur_user.getUser_id());
			request.setAttribute("user_hold_coupons", user_hold_coupons);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("user_coupon.jsp").forward(request, response);
	}

}
