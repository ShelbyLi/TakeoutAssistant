package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopRegister
 */
@WebServlet("/ShopRegister")
public class ShopRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopRegister() {
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
		
		if (!request.getParameter("shop_pwd").equals(request.getParameter("shop_pwd_check"))) {
			request.setAttribute("hint", "两次密码输入不一致!");
			request.getRequestDispatcher("shop_register.jsp").forward(request,response);
		} else {
			BeanShop shop = new BeanShop();
			shop.setShop_name(request.getParameter("shop_name"));
			shop.setShop_pwd(request.getParameter("shop_pwd"));
			shop.setShop_level(0);
			
			ShopManager sm = new ShopManager();
			try {
				sm.register(shop);
				request.getRequestDispatcher("shop_login.jsp").forward(request,response);
			} catch (BaseException e) {
				e.printStackTrace();
				request.setAttribute("hint", e.getMessage());
				request.getRequestDispatcher("shop_register.jsp").forward(request,response);
			}
		}
		
	}

}
