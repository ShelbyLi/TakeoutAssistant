package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.FullreductionSchemeManager;
import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class ShopAddFullReduction
 */
@WebServlet("/ShopAddFullReduction")
public class ShopAddFullReduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopAddFullReduction() {
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
		BeanShop cur_shop = new BeanShop();
		BeanFullReductionScheme fullreduction = new BeanFullReductionScheme();
		FullreductionSchemeManager frm = new FullreductionSchemeManager();
//		System.out.println("hr");
		
		cur_shop = (BeanShop) session.getAttribute("cur_shop");
		fullreduction.setShop_id(cur_shop.getShop_id());
		fullreduction.setFullreduction_amount(Double.parseDouble(request.getParameter("fullreduction_amount")));
		fullreduction.setFullreduction_discounted_price(Double.parseDouble(request.getParameter("fullreduction_discounted_price")));
//		if ("是".equals(request.getParameter("fullreduction_can_superimposed_with_coupons"))) {
//			fullreduction.setFullreduction_can_superimposed_with_coupons(BeanFullReductionScheme.can_superimosed_with_coupons);
//		} else {
//			fullreduction.setFullreduction_can_superimposed_with_coupons(BeanFullReductionScheme.cannot_superimosed_with_coupons);
//		}
		fullreduction.setFullreduction_can_superimposed_with_coupons(Integer.parseInt(request.getParameter("fullreduction_can_superimposed_with_coupons")));
		try {
			frm.add(fullreduction);
			request.getRequestDispatcher("ShopFullReduction").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
