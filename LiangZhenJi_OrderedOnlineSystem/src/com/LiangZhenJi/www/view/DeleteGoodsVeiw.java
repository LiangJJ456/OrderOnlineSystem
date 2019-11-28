package com.LiangZhenJi.www.view;

import java.io.File;
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
 * Servlet implementation class DeleteGoodsVeiw
 */
@WebServlet("/DeleteGoodsVeiw")
public class DeleteGoodsVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteGoodsVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获得未删除前的商品名字
        String goodsName=(String)request.getParameter("oldGoodsName");
        GoodsDao goodsDao=new GoodsDao();
        ShopDao shopDao=new ShopDao();
        String oldPhoto=goodsDao.Find(goodsName).getGoodsPhoto();//获得未删除前的文件路径
        String oldPath= getServletContext().getRealPath("");
        File oldFile=new File(oldPath+oldPhoto);
        oldFile.delete();//删除服务器中的商品图片
        goodsDao.delete(goodsName);
        String DeleteInformation="删除成功";
        request.setAttribute("DeleteInformation", DeleteInformation);
        HttpSession session=request.getSession();
		String shopName=shopDao.sellerFindShopName((String)session.getAttribute("userName"));
		List<Goods> shopGoodsList=goodsDao.shopNameFindGoods(shopName);
		request.setAttribute("shopGoodsList", shopGoodsList);//找到店铺的商品
		RequestDispatcher rd=request.getRequestDispatcher("DeleteGoods.jsp");//跳转到删除商品的界面
		rd.forward(request, response);
	}

}
