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

import com.LiangZhenJi.www.dao.PersonOrderDao;
import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * Servlet implementation class DealShopCarVeiw
 */
@WebServlet("/DealShopCarVeiw")
public class DealShopCarVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealShopCarVeiw() {
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
        String userName=(String)session.getAttribute("userName");
        String userKind=null;
        @SuppressWarnings("unchecked")
		List<Goods> goodsList=(List<Goods>)session.getAttribute("shopCar");
        Iterator<Goods> it=goodsList.iterator();
        PersonOrderDao personOrderDao=new PersonOrderDao();
        while(it.hasNext()) {
        	personOrderDao.add(userName, it.next().getGoodsName());//加入订单
        }
        goodsList.removeAll(goodsList);
        session.setAttribute("shopCar", goodsList);
      //查询用户类型
        if(request.getParameter("userKindByParameter")!=null) {
        	userKind=request.getParameter("userKindByParameter");
        }else {
        	userKind=request.getParameter("userKindByAttribute");
        }
      //跳转回用户界面时判断要展示的为商家还是商品
  		String selectKind="goods";
  		request.setAttribute("selectKind", selectKind);
  	//获取第1页的没排序商品信息，每页有5个商品
		Page<Goods> pg=PagingFunctionService.getGoodsWithPage(1,5);
		session.setAttribute("page",pg);//建立一个商品页面的session
		//跳转回用户界面时判断按什么排序展示,便于下一页商品或店家按什么顺序展示
  		String sortKind="noSort";//没排序
  		request.setAttribute("sortKind", sortKind);
  		//返回购买成功的提示
  		request.setAttribute("buyInformation", "购买成功");
  		//判断要跳转的页面
		if("user".equals(userKind)){
  			RequestDispatcher rd=request.getRequestDispatcher("User.jsp");
			rd.forward(request, response);
  		}
  		if("businessman".equals(userKind)){
  			RequestDispatcher rd=request.getRequestDispatcher("Businessman.jsp");
			rd.forward(request, response);
  		}
  		if("manager".equals(userKind)){
  			RequestDispatcher rd=request.getRequestDispatcher("Manager.jsp");
			rd.forward(request, response);
  		}
	}

}
