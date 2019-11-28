package com.LiangZhenJi.www.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.dao.AppraiseDao;
import com.LiangZhenJi.www.po.Appraise;

/**
 * Servlet implementation class AppraiseVeiw
 */
@WebServlet("/AppraiseVeiw")
public class AppraiseVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppraiseVeiw() {
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
        HttpSession session=request.getSession();
    	String userName=(String)session.getAttribute("userName");
    	String goodsName=(String)request.getParameter("checkName");
		if("查看评论".equals((String)request.getParameter("Appraise"))) {
			AppraiseDao appraiseDao =new AppraiseDao();
			Appraise appraise=appraiseDao.getUserAppraise(userName, goodsName);
			request.setAttribute("appraise", appraise);
			RequestDispatcher rd=request.getRequestDispatcher("WatchAppraise.jsp");//跳转到查看自己评论的界面
			rd.forward(request, response);
		}else {
			request.setAttribute("goodsName",goodsName );
			RequestDispatcher rd=request.getRequestDispatcher("Appraise.jsp");//跳转到评论的界面
			rd.forward(request, response);
		}
	}

}
