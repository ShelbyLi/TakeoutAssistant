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

import cn.edu.zucc.takeoutassistant.control.DeliveryAddrManager;
import cn.edu.zucc.takeoutassistant.control.FullreductionSchemeManager;
import cn.edu.zucc.takeoutassistant.model.BeanDeliveryAddr;
import cn.edu.zucc.takeoutassistant.model.BeanFullReductionScheme;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserAddr
 */
@WebServlet("/UserAddr")
public class UserAddr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddr() {
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
		
		DeliveryAddrManager dam = new DeliveryAddrManager();
		List<BeanDeliveryAddr> addrs = new ArrayList<BeanDeliveryAddr>();
		BeanUser cur_user = new BeanUser();
		cur_user = (BeanUser) session.getAttribute("cur_user");
		try {
//			String keyWord = request.getParameter("keyWord");
//			if (keyWord != null) {
//				addrs = dam.fuzzySearch(keyWord, cur_user.getUser_id());
//			} else {
				addrs = dam.loadAll(cur_user.getUser_id());
//			}
			
			
			request.setAttribute("addrs", addrs);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("user_addr.jsp").forward(request, response);
	
	}

}
