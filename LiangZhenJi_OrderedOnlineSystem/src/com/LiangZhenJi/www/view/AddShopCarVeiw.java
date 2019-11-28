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
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class AddShopCarVeiw
 */
@WebServlet("/AddShopCarVeiw")
public class AddShopCarVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShopCarVeiw() {
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
        GoodsDao goodsDao=new GoodsDao();
        Goods goods=goodsDao.Find(goodsName);//获取商品信息
        HttpSession session=request.getSession();
        @SuppressWarnings("unchecked")
		List<Goods> goodsList=(List<Goods>)session.getAttribute("shopCar");//获得购物车的session
        goodsList.add(goods);//加入购物车
        request.setAttribute("userKind", request.getParameter("userKind"));
        String shopCarInformation="已加入购物车";
        request.setAttribute("shopCarInformation", shopCarInformation);//向用户反馈信息
        request.setAttribute("goodsName", goodsName);//记录商品名
        RequestDispatcher rd=request.getRequestDispatcher("ScanGoodsInformationVeiw");//跳转到浏览商品详情的veiw
		rd.forward(request, response);
	}

}
