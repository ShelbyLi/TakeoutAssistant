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

import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopAddProductPrepare
 */
@WebServlet("/ShopAddProductPrepare")
public class ShopAddProductPrepare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopAddProductPrepare() {
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
		
		ProductcategoryManager pcm = new ProductcategoryManager();
		List<BeanProductCategory> productcategorys = new ArrayList<BeanProductCategory>();
		BeanShop cur_shop = new BeanShop();
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		try {
			productcategorys = pcm.loadAll(cur_shop.getShop_id());
			request.setAttribute("productcategorys", productcategorys);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("shop_add_product.jsp").forward(request, response);
	}

}
