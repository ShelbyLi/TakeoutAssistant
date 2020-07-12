package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
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
 * Servlet implementation class UserAddAddr
 */
@WebServlet("/UserAddAddr")
public class UserAddAddr extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAddAddr() {
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
		
		BeanUser cur_user = new BeanUser();
		BeanDeliveryAddr addr = new BeanDeliveryAddr();
		DeliveryAddrManager dam = new DeliveryAddrManager();

		cur_user = (BeanUser) session.getAttribute("cur_user");
		addr.setUser_id(cur_user.getUser_id());
		addr.setAddr_province(request.getParameter("addr_province"));
		addr.setAddr_city(request.getParameter("addr_city"));
		addr.setAddr_district(request.getParameter("addr_district"));
		addr.setAddr_detailed_addr(request.getParameter("addr_detailed_addr"));
		addr.setAddr_contact_person(request.getParameter("addr_contact_person"));
		addr.setAddr_contact_phone(request.getParameter("addr_contact_phone"));
		try {
			dam.add(addr);
			request.getRequestDispatcher("UserAddr").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
