package com.LiangZhenJi.www.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.po.Shop;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * Servlet implementation class ShopPageVeiw
 */
@WebServlet("/ShopPageVeiw")
public class ShopPageVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopPageVeiw() {
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
			//获取第n页的没排序店家信息，每页有5个店家
			Page<Shop> pg=PagingFunctionService.getShopWithPage(pageNum,5);
			session.setAttribute("page",pg);//建立一个店家页面的session
		}
		if("noSortByLike".equals(request.getParameter("sortKind"))) {
			//获取第n页的模糊搜索的商家信息，每页有5个店家
			Page<Shop> pg=PagingFunctionService.getLikeShopWithPage(pageNum,5, msg);
			session.setAttribute("page",pg);//建立一个店家页面的session
		}
		if("wellReputed".equals(request.getParameter("sortKind"))) {
			//获取第n页的好评度排序店家信息，每页有5个店家
			Page<Shop> pg=PagingFunctionService.getShopWithPageByReputed(pageNum, 5);
			session.setAttribute("page",pg);//建立一个店家页面的session
		}
		if("sales".equals(request.getParameter("sortKind"))) {
			//获取第n页的销量排序店家信息，每页有5个店家
			Page<Shop> pg=PagingFunctionService.getShopWithPageBySales(pageNum, 5);
			session.setAttribute("page",pg);//建立一个店家页面的session
		}
		//
		//跳转回用户界面时判断要展示的为商家还是商品
  		String selectKind="shop";
  		request.setAttribute("selectKind", selectKind);
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
