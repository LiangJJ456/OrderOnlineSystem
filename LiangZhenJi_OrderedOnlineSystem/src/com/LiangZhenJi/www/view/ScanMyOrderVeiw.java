package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.dao.AppraiseDao;
import com.LiangZhenJi.www.dao.PersonOrderDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class ScanMyOrderVeiw
 */
@WebServlet("/ScanMyOrderVeiw")
public class ScanMyOrderVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanMyOrderVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	HttpSession session=request.getSession();
	String userName=(String)session.getAttribute("userName");
	PersonOrderDao personOrderDao=new PersonOrderDao();
	AppraiseDao appraiseDao=new AppraiseDao();
	List<Goods> orderList=personOrderDao.userGetOrder(userName);
	request.setAttribute("orderList", orderList);//获得订单
	//检查商品是否已经评论
	Iterator<Goods> it=orderList.iterator();
	Goods goods =null;
	List<Integer> checkAppraiseList=new ArrayList<>();
	while(it.hasNext()) {
		goods=it.next();
		if(appraiseDao.getUserAppraise(userName, goods.getGoodsName())!=null) {
			checkAppraiseList.add(1);//商品已评论
		}
		else {
			checkAppraiseList.add(0);//商品未评论
		}
	}
	if(request.getAttribute("AppraiseInformation")!=null) {
		request.setAttribute("AppraiseInformation", "评论成功");
	}
	request.setAttribute("checkAppraiseList", checkAppraiseList);
	RequestDispatcher rd=request.getRequestDispatcher("MyOrder.jsp");//跳转到用户订单的界面
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
