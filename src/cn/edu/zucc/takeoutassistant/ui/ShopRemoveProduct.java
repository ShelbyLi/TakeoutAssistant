package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.ProductManager;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopRemoveProduct
 */
@WebServlet("/ShopRemoveProduct")
public class ShopRemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopRemoveProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		ProductManager pm = new ProductManager();
//		System.out.println("*" + request.getParameter("product_id"));
		int id = Integer.parseInt(request.getParameter("product_id"));
		try {
//			System.out.println(id);
			pm.delete(id);
			request.getRequestDispatcher("ShopProductdetails").forward(request, response);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
