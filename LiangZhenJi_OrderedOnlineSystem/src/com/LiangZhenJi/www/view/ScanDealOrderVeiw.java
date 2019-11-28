package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.dao.PersonOrderDao;

/**
 * Servlet implementation class ScanDealOrderVeiw
 */
@WebServlet("/ScanDealOrderVeiw")
public class ScanDealOrderVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanDealOrderVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String seller=(String)session.getAttribute("userName");
		PersonOrderDao personOrderDao=new PersonOrderDao();
		List<Object[]> dealOrderList=personOrderDao.sellerGetOrder(seller);
		request.setAttribute("dealOrderList", dealOrderList);
		if(request.getAttribute("dealOrderInformation")!=null) {
			request.setAttribute("dealOrderInformation", "已处理订单");
		}
		 RequestDispatcher rd=request.getRequestDispatcher("DealOrder.jsp");//跳转到处理订单的界面
			rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
