package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class CancelOrderVeiw
 */
@WebServlet("/CancelOrderVeiw")
public class CancelOrderVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelOrderVeiw() {
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
        int index=-1;//记录商品在购物车列单的索引
        HttpSession session=request.getSession();
        @SuppressWarnings("unchecked")
		List<Goods> goodsList=(List<Goods>)session.getAttribute("shopCar");
        Iterator<Goods> it=goodsList.iterator();
        while(it.hasNext()) {
        	Goods goods=it.next();
        	if(goodsName.equals(goods.getGoodsName())) {
        		index=index+1;
        		break;
        	}else {
        		index++;
        	}
        }
        goodsList.remove(index);//删除要取消订单的商品
        session.setAttribute("shopCar", goodsList);
        //记录用户类型
        if(request.getParameter("userKindByParameter")!=null) {
        	request.setAttribute("userKind",request.getParameter("userKindByParameter"));
        }else {
        	request.setAttribute("userKind",request.getParameter("userKindByAttribute"));
        }
        RequestDispatcher rd=request.getRequestDispatcher("ShopCar.jsp");//跳转到购物车的界面
		rd.forward(request, response);
	}

}
