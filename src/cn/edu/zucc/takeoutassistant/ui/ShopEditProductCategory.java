package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.ProductManager;
import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.model.BeanProduct;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopEditProductCategory
 */
@WebServlet("/ShopEditProductCategory")
public class ShopEditProductCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int productcategory_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopEditProductCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		productcategory_id = Integer.parseInt(request.getParameter("productcategory_id"));
		System.out.println(productcategory_id);
//		
		ProductcategoryManager pcm = new ProductcategoryManager();
		BeanProductCategory productcategory = new BeanProductCategory();
		try {
			productcategory = pcm.searchDetailInfo(productcategory_id);
			request.setAttribute("productcategory", productcategory);
		} catch (BaseException e) {
			e.printStackTrace();
		}
//		
		request.getRequestDispatcher("shop_edit_productcategory.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		ProductcategoryManager pcm = new ProductcategoryManager();
		BeanProductCategory productcategory = new BeanProductCategory();
		
		productcategory.setProductcategory_id(productcategory_id);
		productcategory.setProductcategory_name(request.getParameter("productcategory_name"));
		try {
			pcm.update(productcategory);
			request.getRequestDispatcher("ShopProductCategory").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
