package com.LiangZhenJi.www.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.LiangZhenJi.www.dao.UserDao;
import com.LiangZhenJi.www.po.User;

/**
 * Servlet implementation class ScanInformationVeiw
 */
@WebServlet("/ScanInformationVeiw")
public class ScanInformationVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanInformationVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		User user=new User();
		UserDao userDao=new UserDao();
		user=userDao.find((String)session.getAttribute("userName"));
		request.setAttribute("password", user.getPassword());
		request.setAttribute("sex", user.getSex());
		request.setAttribute("personPhoto", user.getPersonPhoto());
		request.setAttribute("address", user.getAddress());
		request.setAttribute("payPassword", user.getPayPassword());
		if(request.getAttribute("information")!=null){
			String information="修改成功";
		       request.setAttribute("information", information);
		}
		RequestDispatcher rd=request.getRequestDispatcher("ScanInformation.jsp");
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
