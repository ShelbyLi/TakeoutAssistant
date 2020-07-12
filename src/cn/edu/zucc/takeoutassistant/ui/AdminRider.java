package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.RiderManager;
import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class AdminRider
 */
@WebServlet("/AdminRider")
public class AdminRider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRider() {
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
		
		RiderManager rm = new RiderManager();
		List<BeanRider> riders = new ArrayList<BeanRider>();
		try {
			String keyWord = request.getParameter("keyWord");
			if (keyWord != null) {
////				// 模糊查找
				riders = rm.fuzzySearch(keyWord);
			} else {
				riders = rm.loadAll();
			}
			request.setAttribute("riders", riders);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("admin_rider.jsp").forward(request, response);
	
	}

}
