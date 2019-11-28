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
import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.po.Goods;


/**
 * Servlet implementation class CheckGoodsVeiw
 */
@WebServlet("/CheckGoodsVeiw")
public class CheckGoodsVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckGoodsVeiw() {
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
        CheckGoodsDao checkGoodsDao=new CheckGoodsDao();
        GoodsDao goodsDao=new GoodsDao();
        Goods goods=checkGoodsDao.getGoodsInformation(request.getParameter("checkName"));
        checkGoodsDao.checked(request.getParameter("checkName"));//审核通过
        goodsDao.add(goods);//把审核商品放到商品表
        String checkInformation="审核成功";
        request.setAttribute("checkInformation", checkInformation);
        List<Goods> checkGoodsList=checkGoodsDao.getCheckList();
		request.setAttribute("checkGoodsList", checkGoodsList);
        RequestDispatcher rd=request.getRequestDispatcher("CheckGoods.jsp");//跳转到审核商品的界面
		rd.forward(request, response);
	}

}
