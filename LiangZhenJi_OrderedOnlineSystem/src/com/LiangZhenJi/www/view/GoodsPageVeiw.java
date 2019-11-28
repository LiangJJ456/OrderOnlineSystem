package com.LiangZhenJi.www.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * Servlet implementation class GoodsPageVeiw
 */
@WebServlet("/GoodsPageVeiw")
public class GoodsPageVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsPageVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		String msg=request.getParameter("msg");
		//
		if("noSort".equals(request.getParameter("sortKind"))) {
			//获取第n页的没排序商品信息，每页有5个商品
			Page<Goods> pg=PagingFunctionService.getGoodsWithPage(pageNum,5);
			session.setAttribute("page",pg);//建立一个商品页面的session
		}
		if("noSortByLike".equals(request.getParameter("sortKind"))) {
			//获取第n页的模糊搜索的商品信息，每页有5个商品
			Page<Goods> pg=PagingFunctionService.getLikeGoodsWithPage(pageNum,5, msg);
			session.setAttribute("page",pg);//建立一个商品页面的session
		}
		if("wellReputed".equals(request.getParameter("sortKind"))) {
			//获取第n页的好评度排序商品信息，每页有5个商品
			Page<Goods> pg=PagingFunctionService.getGoodsWithPageByReputed(pageNum, 5);
			session.setAttribute("page",pg);//建立一个商品页面的session
		}
		if("sales".equals(request.getParameter("sortKind"))) {
			//获取第n页的销量排序商品信息，每页有5个商品
			Page<Goods> pg=PagingFunctionService.getGoodsWithPageBySales(pageNum, 5);
			session.setAttribute("page",pg);//建立一个商品页面的session
		}
		if("price".equals(request.getParameter("sortKind"))) {
			//获取第n页的价格排序商品信息，每页有5个商品
			Page<Goods> pg=PagingFunctionService.getGoodsWithPageByPrice(pageNum, 5);
			session.setAttribute("page",pg);//建立一个商品页面的session
		}
		//
		//跳转回用户界面时判断要展示的为商家还是商品
  		String selectkind="goods";
  		request.setAttribute("selectKind", selectkind);
  		//
		if("user".equals((String)request.getParameter("userKind"))){
  			RequestDispatcher rd=request.getRequestDispatcher("User.jsp");
			rd.forward(request, response);
  		}
  		if("businessman".equals((String)request.getParameter("userKind"))){
  			RequestDispatcher rd=request.getRequestDispatcher("Businessman.jsp");
			rd.forward(request, response);
  		}
  		if("manager".equals((String)request.getParameter("userKind"))){
  			RequestDispatcher rd=request.getRequestDispatcher("Manager.jsp");
			rd.forward(request, response);
  		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
