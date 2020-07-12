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

import cn.edu.zucc.takeoutassistant.control.RiderDeliverOrderManager;
import cn.edu.zucc.takeoutassistant.model.BeanOrderForm;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.model.BeanRiderDeliverOrder;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RiderToReceive
 */
@WebServlet("/RiderToDeliver")
public class RiderToDeliver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderToDeliver() {
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
		
		RiderDeliverOrderManager rdom = new RiderDeliverOrderManager();
		List<BeanRiderDeliverOrder> riderdeliverorders = new ArrayList<BeanRiderDeliverOrder>(); 
		BeanRider cur_rider = new BeanRider();
		cur_rider = (BeanRider) session.getAttribute("cur_rider");
//		System.out.print(cur_rider.getRider_id());
		try {
			riderdeliverorders = rdom.loadAll(cur_rider.getRider_id(), BeanOrderForm.delivering);
			request.setAttribute("riderdeliverorders", riderdeliverorders);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("rider_to_deliver.jsp").forward(request, response);
	}

}
