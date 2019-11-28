package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.CheckLoginDao;
import com.LiangZhenJi.www.dao.UserDao;
import com.LiangZhenJi.www.dao.UserRoleDao;
import com.LiangZhenJi.www.po.User;

/**
 * Servlet implementation class CheckLoginVeiw
 */
@WebServlet("/CheckLoginVeiw")
public class CheckLoginVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLoginVeiw() {
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
        CheckLoginDao checkLoginDao=new CheckLoginDao();
        UserDao userDao=new UserDao();
        User user=checkLoginDao.getUserInformation(request.getParameter("checkName"));
        checkLoginDao.checked(request.getParameter("checkName"));//审核通过
        userDao.add(user);//把审核用户放到用户表
        UserRoleDao.addUser(user.getUserName());//注册为普通用户
        String checkInformation="审核成功";
        request.setAttribute("checkInformation", checkInformation);
        List<User> checkUserList=checkLoginDao.getUnCheckList();//获得审核注册表
		request.setAttribute("checkUserList", checkUserList);
        RequestDispatcher rd=request.getRequestDispatcher("CheckLogin.jsp");//跳转到审核注册的界面
		rd.forward(request, response);
	}

}
