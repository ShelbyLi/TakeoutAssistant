package cn.edu.zucc.takeoutassistant.ui;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zucc.takeoutassistant.control.RiderManager;
import cn.edu.zucc.takeoutassistant.model.BeanRider;
import cn.edu.zucc.takeoutassistant.util.BaseException;

/**
 * Servlet implementation class RiderEditBasicInfo
 */
@WebServlet("/RiderEditBasicInfo")
public class RiderEditBasicInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RiderEditBasicInfo() {
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
		
		BeanRider rider = new BeanRider();
		BeanRider cur_rider = new BeanRider();
		
		cur_rider = (BeanRider) session.getAttribute("cur_rider");
		rider.setRider_id(cur_rider.getRider_id());
		rider.setRider_name(request.getParameter("rider_name"));
		
		RiderManager rm = new RiderManager();
		try {
			rm.updateInfo(rider);
			session.setAttribute("cur_rider", rider);
			request.getRequestDispatcher("rider_basicinfo.jsp").forward(request, response);
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

}
