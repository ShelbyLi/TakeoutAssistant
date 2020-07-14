package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.UserManager;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		if (!request.getParameter("user_pwd").equals(request.getParameter("user_pwd_check"))) {
			request.setAttribute("hint", "两次密码输入不一致! ");
			request.getRequestDispatcher("shop_register.jsp").forward(request,response);
		} else {
			BeanUser user = new BeanUser();
			user.setUser_name(request.getParameter("user_name"));
//			if ("女".equals(request.getParameter("user_gender")))
//				user.setUser_gender(BeanUser.FEMALE);
//			else
//				user.setUser_gender(BeanUser.MALE);
			user.setUser_gender(Integer.parseInt(request.getParameter("user_gender")));
			user.setUser_pwd(request.getParameter("user_pwd"));
			user.setUser_phone_number(request.getParameter("user_phone_number"));
			user.setUser_mail(request.getParameter("user_mail"));
			user.setUser_city(request.getParameter("user_city"));
			user.setUser_is_vip(BeanUser.ISNOTVIP);
			
			UserManager um = new UserManager();
			try {
				um.register(user);
				request.getRequestDispatcher("user_login.jsp").forward(request,response);
			} catch (BaseException e) {
				e.printStackTrace();
				request.setAttribute("hint", e.getMessage());
				request.getRequestDispatcher("user_register.jsp").forward(request,response);
			}
		}
	}

}
