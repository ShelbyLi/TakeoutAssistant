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

import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserBuy
 */
@WebServlet("/UserBuy")
public class UserBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBuy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		BeanUser cur_user = (BeanUser) session.getAttribute("cur_user");
		BeanShop cur_entered_shop = (BeanShop) session.getAttribute("cur_entered_shop");
		
		
		OrderManager om = new OrderManager();
		try {
			//更新地址id
			int addr_id = Integer.parseInt(request.getParameter("addr_id"));
			om.updateAddrID(cur_user.getUser_id(), cur_entered_shop.getShop_id(), addr_id);
			
			// 如果使用优惠券 则更新优惠券id
			if (request.getParameter("coupon_id") != null) {
				int coupon_id = Integer.parseInt(request.getParameter("coupon_id"));
				om.updateCouponId(cur_user.getUser_id(), cur_entered_shop.getShop_id(), coupon_id);
			}
			
			// 更新需要送达的时间
			System.out.println(request.getParameter("order_request_delivery_time"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			String datetime = request.getParameter("order_request_delivery_time");
			String[] token = datetime.split("T");
			om.updateRequestDeliveryTime(cur_user.getUser_id(), cur_entered_shop.getShop_id(), 
					sdf.parse(token[0]+" "+token[1]));
			
//			// 更新订单状态(下单)
//			om.ordered(cur_user.getUser_id(), cur_entered_shop.getShop_id());
		} catch (BaseException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("UserCheckOut").forward(request, response);
	}

}
