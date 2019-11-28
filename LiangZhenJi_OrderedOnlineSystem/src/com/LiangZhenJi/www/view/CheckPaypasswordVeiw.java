package com.LiangZhenJi.www.view;

import java.io.IOException;

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
import com.LiangZhenJi.www.service.CheckPaypasswordService;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * Servlet implementation class CheckPaypasswordVeiw
 */
@WebServlet("/CheckPaypasswordVeiw")
public class CheckPaypasswordVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckPaypasswordVeiw() {
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
        String goodsName=request.getParameter("goodsName");
      //获取商品页面信息
      		int pageNum=1;
      		//获取第一页的商品信息，每页有5个商品
      		Page<Goods> pg=PagingFunctionService.getGoodsWithPage(pageNum, 5);
      		session.setAttribute("page",pg);//建立一个商品页面的session
      		String error=null;
      		//跳转回用户界面时判断要展示的为商家还是商品
        		String selectKind="goods";
        		request.setAttribute("selectKind", selectKind);
        		//跳转回用户界面时判断按什么排序展示,便于下一页商品或店家按什么顺序展示
        		String sortKind="nosort";
        		request.setAttribute("sortKind", sortKind);
        		//
        		String buyInformation="购买成功";
        		request.setAttribute("buyInformation", buyInformation);
        if(CheckPaypasswordService.check(userName,(String)request.getParameter("payPassword"))==1) {
        	//支付密码验证成功后，商品就会加入用户订单中
        	PersonOrderDao personOrderDao=new PersonOrderDao();
        	personOrderDao.add(userName, goodsName);
        	
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
        }else {
        	error="密码错误";
        	request.setAttribute("error", error);
        	RequestDispatcher rd=request.getRequestDispatcher("BuyGoods.jsp");
			rd.forward(request, response);
        }
	}

}
