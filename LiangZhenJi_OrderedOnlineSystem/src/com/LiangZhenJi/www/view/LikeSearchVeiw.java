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
import com.LiangZhenJi.www.po.Shop;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * Servlet implementation class LikeSearchVeiw
 */
@WebServlet("/LikeSearchVeiw")
public class LikeSearchVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeSearchVeiw() {
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
        String msg=request.getParameter("search");
        HttpSession session=request.getSession();
        if("goods".equals(request.getParameter("kind"))) {
        	//获取商品页面信息
    		int pageNum=1;
    		//获取第一页的模糊搜索的商品信息，每页有5个商品
    		Page<Goods> pg=PagingFunctionService.getLikeGoodsWithPage(pageNum, 5, msg);
    		session.setAttribute("page",pg);//建立一个商品页面的session
    		//跳转回用户界面时判断要展示的为商家还是商品
      		String selectKind="goods";//商品
      		request.setAttribute("selectKind", selectKind);
        }else {
        	//获取店家页面信息
    		int pageNum=1;
    		//获取第一页的模糊搜索的店家信息，每页有5个店家
    		Page<Shop> pg=PagingFunctionService.getLikeShopWithPage(pageNum, 5, msg);
    		session.setAttribute("page",pg);//建立一个店家页面的session
    		//跳转回用户界面时判断要展示的为商家还是商品
      		String selectKind="shop";//店家
      		request.setAttribute("selectKind", selectKind);
        }
      //跳转回用户界面时判断按什么排序展示,便于下一页商品或店家按什么顺序展示
  		String sortKind="noSortByLike";//没顺序，但是是模糊搜索的
  		request.setAttribute("sortKind", sortKind);
  		//记录下用户搜索的内容
  		request.setAttribute("searchInformation", msg);
        //页面跳转
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
