package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.OrderManager;
import cn.edu.zucc.takeoutassistant.control.UserHoldCouponsManager;
import cn.edu.zucc.takeoutassistant.model.BeanShop;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserCheckedOrder
 */
@WebServlet("/UserCheckedOrder")
public class UserCheckedOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCheckedOrder() {
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
		request.setCharacterEncoding("utf-8");	//����������ַ���
		response.setContentType("text/html;charset=utf-8");		//�����ı�����
		
		BeanUser cur_user = (BeanUser) session.getAttribute("cur_user");
		BeanShop cur_entered_shop = (BeanShop) session.getAttribute("cur_entered_shop");
		
		// ���¶���״̬ �µ�ʱ��(�µ�) ���û�ʹ���Ż�ȯ ����һ���Ż�ȯ����
		OrderManager om = new OrderManager();
		try {
			om.ordered(cur_user.getUser_id(), cur_entered_shop.getShop_id());
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		
		// �����û�������
		UserHoldCouponsManager uhcm = new UserHoldCouponsManager();
		try {
			uhcm.update(cur_user.getUser_id(), cur_entered_shop.getShop_id());
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("UserOrder").forward(request, response);
	}

}
