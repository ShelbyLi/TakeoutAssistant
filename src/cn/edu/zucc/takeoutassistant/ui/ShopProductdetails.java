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

import cn.edu.zucc.takeoutassistant.control.ProductManager;
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopProductdetails
 */
@WebServlet("/ShopProductdetails")
public class ShopProductdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopProductdetails() {
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
		
		ProductManager pm = new ProductManager();
		List<BeanProduct> products = new ArrayList<BeanProduct>();
		BeanShop cur_shop = new BeanShop();
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		
		try {
			String keyWord = request.getParameter("keyWord");
			if (keyWord != null) {
				// 模糊查找
				products = pm.fuzzySearch(keyWord, cur_shop.getShop_id());
			} else {
				products = pm.loadAll(cur_shop.getShop_id());
			}
			request.setAttribute("products", products);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("shop_productdetails.jsp").forward(request, response);
	}

}
