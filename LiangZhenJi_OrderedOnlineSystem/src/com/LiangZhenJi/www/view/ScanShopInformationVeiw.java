package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class ScanShopInformationVeiw
 */
@WebServlet("/ScanShopInformationVeiw")
public class ScanShopInformationVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanShopInformationVeiw() {
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
        List<Goods> goodsList=null;
        String shopName=null;
        GoodsDao goodsDao=new GoodsDao();
        if(request.getParameter("goodsName")!=null) {
        	 String goodsName=(String)request.getParameter("goodsName");
        	 shopName=goodsDao.goodsNameFindshop(goodsName);
        	 goodsList=goodsDao.shopNameFindGoods(shopName);
        }else {
        	shopName=(String)request.getParameter("shopName");
        	goodsList=goodsDao.shopNameFindGoods(shopName);
        }
        //获取店家的商品
        request.setAttribute("goodsList", goodsList);
        //保存用户类型
        String userKind=request.getParameter("userKind");
        request.setAttribute("userKind", userKind);
        //保存店铺名字
        request.setAttribute("shopName", shopName);
        //跳转到店铺页面
        RequestDispatcher rd=request.getRequestDispatcher("ShopInformation.jsp");
		rd.forward(request, response);
        
	}

}
