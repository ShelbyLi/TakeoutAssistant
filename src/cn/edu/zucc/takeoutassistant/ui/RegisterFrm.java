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
 * Servlet implementation class RegisterFrm1
 */
@WebServlet("/RegisterFrm")
public class RegisterFrm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterFrm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		request.setCharacterEncoding("utf-8");
			//设置相应的文本类型
		response.setContentType("text/html;charset=utf-8");
		
		//数据库工具类
//			MysqlTool to=new MysqlTool();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");			
		if(username==null||password==null){
			response.sendRedirect("index.html");
		}else{
			System.out.println(username+"\t"+password);
//			BeanUser user = new BeanUser();
//			user.setUser_name(username);
//			user.setUser_pwd(password);
//			user.setUser_phone_number("123456");
//			user.setUser_is_vip(0);
//			UserManager um = new UserManager();
			UserManager um = new UserManager();
			BeanUser user = new BeanUser();
			user.setUser_name(username);
			user.setUser_pwd(password);
			user.setUser_phone_number("12345678901");
			user.setRegistration_time(new java.util.Date());
			user.setUser_is_vip(BeanUser.isnotVip);
			try {
				um.register(user);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
