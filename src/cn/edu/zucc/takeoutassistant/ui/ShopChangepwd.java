package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopBasicinfo
 */
@WebServlet("/ShopChangepwd")
public class ShopChangepwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopChangepwd() {
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
		
		if (!request.getParameter("new_pwd").equals(request.getParameter("new_pwd_check"))) {
			request.setAttribute("hint", "两次密码输入不一致!");
			request.getRequestDispatcher("shop_basicinfo.jsp").forward(request,response);
		} else {
			BeanShop shop = new BeanShop();
			ShopManager sm = new ShopManager();
			shop = (BeanShop) session.getAttribute("cur_shop");
			int id = shop.getShop_id();
			try {
				sm.changePwd(id, request.getParameter("old_pwd"), request.getParameter("new_pwd"));
			} catch (BaseException e) {
				request.setAttribute("hint", e.getMessage());
				e.printStackTrace();
			}
			request.getRequestDispatcher("shop_basicinfo.jsp").forward(request,response);
		}
	}

}
