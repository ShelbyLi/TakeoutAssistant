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
 * Servlet implementation class ShopBasicinfoSubmitUpdate
 */
@WebServlet("/ShopBasicinfoSubmitUpdate")
public class ShopBasicinfoSubmitUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopBasicinfoSubmitUpdate() {
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
		request.setCharacterEncoding("utf-8");	//����������ַ���
		response.setContentType("text/html;charset=utf-8");		//�����ı�����
		
		BeanShop shop = new BeanShop();
		BeanShop cur_shop = new BeanShop();
		
//		System.out.println("hrhrhr " + cur_shop.getShop_id());
//		System.out.println(cur_shop.getShop_name());
//		System.out.println(cur_shop.getShop_level());
		
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		shop.setShop_id(cur_shop.getShop_id());
		shop.setShop_name(request.getParameter("shop_name"));
		shop.setShop_level(Integer.parseInt(request.getParameter("shop_level")));
		
		
//		System.out.println("hrhrhr " + shop.getShop_id());
//		System.out.println(shop.getShop_name());
//		System.out.println(shop.getShop_level());
		ShopManager sm = new ShopManager();
		try {
			sm.updateInfo(shop);
			session.setAttribute("cur_shop", shop);
			request.getRequestDispatcher("shop_basicinfo.jsp").forward(request, response);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}