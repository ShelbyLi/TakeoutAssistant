package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.RiderManager;
import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class AdminRemoveRider
 */
@WebServlet("/AdminRemoveRider")
public class AdminRemoveRider extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRemoveRider() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		RiderManager rm = new RiderManager();
		int id = Integer.parseInt(request.getParameter("rider_id"));
		try {
			rm.logout(id);
			request.getRequestDispatcher("AdminRider").forward(request, response);
		} catch (BaseException e) {
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
