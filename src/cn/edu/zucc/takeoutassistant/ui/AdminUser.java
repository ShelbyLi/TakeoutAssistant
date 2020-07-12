package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.AdminManager;
import cn.edu.zucc.takeoutassistant.control.ProductcategoryManager;
import cn.edu.zucc.takeoutassistant.control.UserManager;
import cn.edu.zucc.takeoutassistant.model.BeanProductCategory;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class AdminUser
 */
@WebServlet("/AdminUser")
public class AdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUser() {
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
//		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//����������ַ���
		response.setContentType("text/html;charset=utf-8");		//�����ı�����
		
		UserManager um = new UserManager();
		List<BeanUser> users = new ArrayList<BeanUser>();
		
//		BeanShop cur_shop = new BeanShop();
//		cur_shop = (BeanShop) session.getAttribute("cur_shop");
////		System.out.println(cur_shop);
		try {
			String keyWord = request.getParameter("keyWord");
			if (keyWord != null) {
//				// ģ������
				users = um.fuzzySearch(keyWord);
			} else {
				users = um.loadAll();
			}
			request.setAttribute("users", users);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);
	
	}

}
