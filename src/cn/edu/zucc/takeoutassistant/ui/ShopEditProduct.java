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
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopEditProduct
 */
@WebServlet("/ShopEditProduct")
public class ShopEditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int product_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopEditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		product_id = Integer.parseInt(request.getParameter("product_id"));
		System.out.println(product_id);
		
		ProductManager pm = new ProductManager();
		BeanProduct product = new BeanProduct();
		try {
			product = pm.searchDetailInfo(product_id);
			request.setAttribute("product", product);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("shop_edit_product.jsp").forward(request, response);
//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		ProductManager pm = new ProductManager();
		BeanProduct product = new BeanProduct();
		
		product.setProduct_id(product_id);
		product.setProduct_name(request.getParameter("product_name"));
		product.setProduct_price(Double.parseDouble(request.getParameter("product_price")));
		product.setProduct_discounted_price(Double.parseDouble(request.getParameter("product_discounted_price")));
		product.setProductcategory_id(Integer.parseInt(request.getParameter("productcategory_id")));
		
//		BeanProductCategory productcategory = new BeanProductCategory();
//		ProductcategoryManager pcm = new ProductcategoryManager();
		try {
//			productcategory = pcm.search(request.getParameter("productcategory_name"));
//			product.setProductcategory_id(productcategory.getProductcategory_id());
			pm.update(product);
			request.getRequestDispatcher("ShopProductdetails").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
