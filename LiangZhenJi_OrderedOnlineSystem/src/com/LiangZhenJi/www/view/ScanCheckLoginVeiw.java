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
import com.LiangZhenJi.www.po.User;

/**
 * Servlet implementation class ScanCheckSignVeiw
 */
@WebServlet("/ScanCheckLoginVeiw")
public class ScanCheckLoginVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanCheckLoginVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CheckLoginDao checkLoginDao=new CheckLoginDao();
		List<User> checkUserList=checkLoginDao.getUnCheckList();//获得审核注册表
		request.setAttribute("checkUserList", checkUserList);
		RequestDispatcher rd=request.getRequestDispatcher("CheckLogin.jsp");//跳转到审核注册的界面
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
