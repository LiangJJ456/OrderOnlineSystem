package com.LiangZhenJi.www.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.dao.PersonOrderDao;
import com.LiangZhenJi.www.dao.ShopDao;

/**
 * Servlet implementation class DealOrderVeiw
 */
@WebServlet("/DealOrderVeiw")
public class DealOrderVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealOrderVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");  //防止乱码！！！！！！！！
        response.setCharacterEncoding("utf-8");
		String goodsName=request.getParameter("goodsName");
		String userName=request.getParameter("customer");
		PersonOrderDao personOrderDao =new PersonOrderDao();
		personOrderDao.ensure(userName, goodsName);//确认订单
		//增加销量
		GoodsDao goodsDao=new GoodsDao();
		ShopDao shopDao=new ShopDao();
		goodsDao.addSales(goodsName);
		shopDao.addSales(goodsName);
		request.setAttribute("dealOrderInformation", "已处理订单");
		RequestDispatcher rd=request.getRequestDispatcher("ScanDealOrderVeiw");//跳转到设置处理订单的界面
		rd.forward(request, response);
	}

}
