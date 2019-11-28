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

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class GoodsManageVeiw
 */
@WebServlet("/GoodsManageVeiw")
public class GoodsManageVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsManageVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ShopDao shopDao=new ShopDao();
		GoodsDao goodsDao=new GoodsDao();
		String shopName=shopDao.sellerFindShopName((String)session.getAttribute("userName"));
		List<Goods> shopGoodsList=goodsDao.shopNameFindGoods(shopName);
		request.setAttribute("shopGoodsList", shopGoodsList);//找到店铺的商品
		RequestDispatcher rd=request.getRequestDispatcher("DeleteGoods.jsp");//跳转到删除商品的界面
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
