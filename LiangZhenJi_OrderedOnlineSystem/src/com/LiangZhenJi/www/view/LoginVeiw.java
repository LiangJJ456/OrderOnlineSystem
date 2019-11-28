package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.service.LoginService;
import com.LiangZhenJi.www.service.PagingFunctionService;

/**
 * 登录验证
 * Servlet implementation class LoginVeiw
 */
@WebServlet("/LoginVeiw")
public class LoginVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVeiw() {
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
		String userName=request.getParameter("userName");
		List<Goods> shopCar=new ArrayList<>();
		HttpSession session=request.getSession();
		session.setAttribute("shopCar", shopCar);//建立一个购物车的session
		session.setAttribute("userName",userName);//建立一个用户的session
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
  		String sortKind="noSort";
  		request.setAttribute("sortKind", sortKind);
		if(LoginService.check(userName,(String)request.getParameter("password"))==1) {
			//验证用户名还有密码是否正确
			if(LoginService.select(userName, request.getParameter("password"),request.getParameter("userKind"))==1) {
			//验证用户选择的用户类别是否正确
				if("普通用户".equals(request.getParameter("userKind"))) {
					RequestDispatcher rd=request.getRequestDispatcher("User.jsp");
					rd.forward(request, response);
				}
				if("商家".equals(request.getParameter("userKind"))) {
					RequestDispatcher rd=request.getRequestDispatcher("Businessman.jsp");
					rd.forward(request, response);
				}
				if("网站管理员".equals(request.getParameter("userKind"))) {
					RequestDispatcher rd=request.getRequestDispatcher("Manager.jsp");
					rd.forward(request, response);
				}
			}
			else {
				//转向用户类型失败的页面
				error="你的选择的用户类型有误，请重新选择";
				request.setAttribute("error", error);
				RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			
		}
		else {
			//用户名或密码错误的页面
			error="密码错误，请重新输入";
			request.setAttribute("error", error);
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
	}

}
