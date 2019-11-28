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
 * Servlet implementation class ShopSalesVeiw
 */
@WebServlet("/ShopSalesVeiw")
public class ShopSalesVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopSalesVeiw() {
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
        HttpSession session=request.getSession();
      //获取按销量排序的店家页面信息
      		int pageNum=1;
      		//获取第一页的商家信息，每页有5个商家
      		Page<Shop> pg=PagingFunctionService.getShopWithPageBySales(pageNum, 5);
      		session.setAttribute("page",pg);//建立一个商品页面的session
      		//跳转回用户界面时判断要展示的为商家还是商品
      		String selectKind="shop";
      		request.setAttribute("selectKind", selectKind);
      		//跳转回用户界面时判断按什么排序展示,便于下一页商品或店家按什么顺序展示
      		String sortKind="sales";
      		request.setAttribute("sortKind", sortKind);
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

}
