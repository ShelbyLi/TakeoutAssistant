package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class UserEditBasicInfo
 */
@WebServlet("/UserEditBasicInfo")
public class UserEditBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditBasicInfo() {
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
		
		BeanUser user = new BeanUser();
		BeanUser cur_user = new BeanUser();
		
		cur_user = (BeanUser) session.getAttribute("cur_user");
		user.setUser_id(cur_user.getUser_id());
		user.setUser_name(request.getParameter("user_name"));
		if ("女".equals(request.getParameter("user_gender"))) 
			user.setUser_gender(BeanUser.FEMALE);
		else
			user.setUser_gender(BeanUser.MALE);
		user.setUser_phone_number(request.getParameter("user_phone_number"));
		user.setUser_mail(request.getParameter("user_mail"));
		user.setUser_city(request.getParameter("user_city"));
		if ("是".equals(request.getParameter("user_is_vip"))) 
			user.setUser_is_vip(BeanUser.ISVIP);
		else
			user.setUser_is_vip(BeanUser.ISNOTVIP);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setUser_vip_ddl(sdf.parse(request.getParameter("user_vip_ddl")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		UserManager um = new UserManager();
		try {
			um.updateInfo(user);
			session.setAttribute("cur_user", user);
			request.getRequestDispatcher("user_basicinfo.jsp").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
