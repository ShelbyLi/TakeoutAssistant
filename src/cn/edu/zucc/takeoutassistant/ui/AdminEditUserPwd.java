package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zucc.takeoutassistant.control.UserManager;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class AdminEditUserPwd
 */
@WebServlet("/AdminEditUserPwd")
public class AdminEditUserPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEditUserPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		UserManager um = new UserManager();
		int id = Integer.parseInt(request.getParameter("user_id"));
		try {
			System.out.println(id);
			System.out.println(request.getParameter("user_old_pwd"));
			um.changePwd(id, request.getParameter("user_old_pwd"), "123456");
//			request.getRequestDispatcher("AdminUser").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("AdminUser").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
