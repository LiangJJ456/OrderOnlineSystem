package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.CheckBusinessDao;
import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.dao.UserDao;
import com.LiangZhenJi.www.dao.UserRoleDao;
import com.LiangZhenJi.www.po.CheckBusiness;
import com.LiangZhenJi.www.po.Shop;

/**
 * Servlet implementation class CheckBusinessVeiw
 */
@WebServlet("/CheckBusinessVeiw")
public class CheckBusinessVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBusinessVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");  //防止乱码！！！！！！！！
        response.setCharacterEncoding("utf-8"); 
		UserRoleDao.addSeller(request.getParameter("checkName"));//注册商家
		CheckBusinessDao checkBusinessDao=new CheckBusinessDao();
		 CheckBusiness cb=checkBusinessDao.getBusinessInformation(request.getParameter("checkName"));//获得已审核商家店铺信息
	        checkBusinessDao.checked(request.getParameter("checkName"));//审核通过
	        Shop shop=new Shop();
	        shop.setShopName(cb.getShopName());
	        shop.setShopPhoto(cb.getShopPhoto());
	        UserDao userDao=new UserDao();
	        int sellerId=userDao.userNameFindId(cb.getSeller());//找到店主的id
	        shop.setSeller(sellerId);
	        ShopDao shopDao=new ShopDao();
	        shopDao.add(shop);//把已审核店铺信息放到店铺表
	        String checkInformation="审核成功";
	        request.setAttribute("checkInformation", checkInformation);
	        List<CheckBusiness> checkBusiness=checkBusinessDao.getCheckList();//获得审核商家店铺表
			request.setAttribute("checkBusiness", checkBusiness);
	        RequestDispatcher rd=request.getRequestDispatcher("CheckBusiness.jsp");//跳转到审核注册的界面
			rd.forward(request, response);
		
	}

}
