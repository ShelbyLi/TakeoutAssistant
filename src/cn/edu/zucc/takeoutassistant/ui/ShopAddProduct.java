package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.ProductManager;
import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopAddProduct
 */
@WebServlet("/ShopAddProduct")
public class ShopAddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopAddProduct() {
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
//		request.getRequestDispatcher("shop_productdetails.jsp").forward(request, response)
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		BeanShop cur_shop = new BeanShop();
//		BeanProductCategory productcategory = new BeanProductCategory();
//		ProductcategoryManager pcm = new ProductcategoryManager();

		BeanProduct product = new BeanProduct();
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		product.setShop_id(cur_shop.getShop_id());
		product.setProduct_name(request.getParameter("product_name"));
		product.setProduct_price(Double.parseDouble(request.getParameter("product_price")));
		product.setProduct_discounted_price(Double.parseDouble(request.getParameter("product_discounted_price")));
		product.setProductcategory_id(Integer.parseInt(request.getParameter("productcategory_id")));
		ProductManager pm = new ProductManager();
		try {
//			productcategory = pcm.search(request.getParameter("productcategory_name"));
//			product.setProductcategory_id(productcategory.getProductcategory_id());
			pm.add(product);
			request.getRequestDispatcher("ShopProductdetails").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
