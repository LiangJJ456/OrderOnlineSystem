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
import com.LiangZhenJi.www.po.CheckBusiness;

/**
 * Servlet implementation class ScanCheckBusinessVeiw
 */
@WebServlet("/ScanCheckBusinessVeiw")
public class ScanCheckBusinessVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanCheckBusinessVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CheckBusinessDao checkBusinessDao=new CheckBusinessDao();
		List<CheckBusiness> checkBusiness=checkBusinessDao.getCheckList();//获得审核商家店铺表
		request.setAttribute("checkBusiness", checkBusiness);
		RequestDispatcher rd=request.getRequestDispatcher("CheckBusiness.jsp");//跳转到审核商家店铺的界面
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
