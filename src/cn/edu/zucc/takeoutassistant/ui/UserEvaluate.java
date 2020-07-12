package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.EvaluateManager;
import cn.edu.zucc.takeoutassistant.control.RiderDeliverOrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanEvaluate;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanRiderDeliverOrder;
import cn.edu.zucc.takeoutassistant.model.BeanUser;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class UserEvaluate
 */
@WebServlet("/UserEvaluate")
public class UserEvaluate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int order_id;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEvaluate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		order_id = Integer.parseInt(request.getParameter("order_id"));
		System.out.println("**"+order_id);
		request.getRequestDispatcher("user_evaluate.jsp").forward(request, response);
//		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");	//设置请求的字符集
		response.setContentType("text/html;charset=utf-8");		//设置文本类型
		
		
		BeanUser cur_user = new BeanUser();
		cur_user = (BeanUser) session.getAttribute("cur_user");
		
		BeanEvaluate evaluation = new BeanEvaluate();
		evaluation.setUser_id(cur_user.getUser_id());
		evaluation.setOrder_id(order_id);
		evaluation.setEvaluate_content(request.getParameter("evaluation"));
		evaluation.setScore(Integer.parseInt(request.getParameter("evaluate_score")));
		System.out.println(order_id);
		
		int user_rate;
		if ("差评".equals(request.getParameter("deliver_user_rate")))
			user_rate = BeanRiderDeliverOrder.BADREVIEW;
		else
			user_rate = BeanRiderDeliverOrder.GOODREVIEW;
		
		EvaluateManager em = new EvaluateManager();
		RiderDeliverOrderManager rdom = new RiderDeliverOrderManager();
		try {
			em.update(evaluation);
			rdom.userRate(order_id, user_rate);
			request.getRequestDispatcher("UserOrder").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		
	}

}
