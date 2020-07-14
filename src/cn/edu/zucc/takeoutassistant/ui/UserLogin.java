package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.ShopManager;
import cn.edu.zucc.takeoutassistant.control.UserManager;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RegisterFrm1
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
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
//		request.setCharacterEncoding("utf-8");	//设置请求的字符集
//		response.setContentType("text/html;charset=utf-8");		//设置文本类型
//		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		if(username==null||password==null){
//			request.getRequestDispatcher("user_login.jsp");
//		}else{
//			System.out.println(username+"\t"+password);
//			UserManager um = new UserManager();
//			try {
//				um.login(username, password);
//				request.getRequestDispatcher("user_basicinfo.jsp").forward(request, response);
//				
//			} catch (BaseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				request.getRequestDispatcher("user_login.jsp").forward(request,response);
////				request.setAttribute("hint", e.getMessage());
//				
//			}
//			
//		}
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		UserManager um = new UserManager();
		BeanUser user = new BeanUser();
		try {
			user = (BeanUser) um.login(request.getParameter("name"), request.getParameter("pwd"));
			session.setAttribute("cur_user", user);
			request.getRequestDispatcher("user_basicinfo.jsp").forward(request,response);
		} catch (BaseException e) {
			e.printStackTrace();
			request.setAttribute("hint", e.getMessage());
			request.getRequestDispatcher("user_login.jsp").forward(request,response);
		}
	}

}
