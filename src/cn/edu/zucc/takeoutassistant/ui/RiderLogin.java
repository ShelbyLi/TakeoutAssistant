package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.RiderManager;
import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RiderLogin
 */
@WebServlet("/RiderLogin")
public class RiderLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderLogin() {
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
		
		RiderManager rm = new RiderManager();
		BeanRider rider = new BeanRider();
		try {
			rider = (BeanRider) rm.login(request.getParameter("rider_name"), request.getParameter("rider_pwd"));
			session.setAttribute("cur_rider", rider);
			request.getRequestDispatcher("rider_basicinfo.jsp").forward(request,response);
		} catch (BaseException e) {
			e.printStackTrace();
			request.setAttribute("hint", e.getMessage());
			request.getRequestDispatcher("rider_login.jsp").forward(request,response);
		}
	}

}
