package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.RiderManager;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RiderRegister
 */
@WebServlet("/RiderRegister")
public class RiderRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderRegister() {
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
		
		if (!request.getParameter("rider_pwd").equals(request.getParameter("rider_pwd_check"))) {
			request.setAttribute("hint", "两次密码输入不一致!");
			request.getRequestDispatcher("rider_register.jsp").forward(request,response);
		} else {
			BeanRider rider = new BeanRider();
			rider.setRider_name(request.getParameter("rider_name"));
			rider.setRider_pwd(request.getParameter("rider_pwd"));
			
			RiderManager rm = new RiderManager();
			try {
				rm.register(rider);
				request.getRequestDispatcher("rider_login.jsp").forward(request,response);
			} catch (BaseException e) {
				e.printStackTrace();
				request.getRequestDispatcher("rider_register.jsp").forward(request,response);
			}
		}
	}

}
