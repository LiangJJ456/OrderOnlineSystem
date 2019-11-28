package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.CheckGoodsDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class ScanCheckGoodsVeiw
 */
@WebServlet("/ScanCheckGoodsVeiw")
public class ScanCheckGoodsVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanCheckGoodsVeiw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CheckGoodsDao checkGoodsDao =new CheckGoodsDao();
		List<Goods> checkGoodsList=checkGoodsDao.getCheckList();
		request.setAttribute("checkGoodsList", checkGoodsList);
        RequestDispatcher rd=request.getRequestDispatcher("CheckGoods.jsp");//跳转到审核商品的界面
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
