package com.LiangZhenJi.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LiangZhenJi.www.dao.AppraiseDao;
import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * Servlet implementation class ScanGoodsInformationVeiw
 */
@WebServlet("/ScanGoodsInformationVeiw")
public class ScanGoodsInformationVeiw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScanGoodsInformationVeiw() {
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
        String goodsName=null;
        //有多个jsp或servlet跳转到该控制器，故要判断一下
        if(request.getParameter("goodsName")!=null) {
			goodsName=(String)request.getParameter("goodsName");
		}else {
			goodsName=(String)request.getAttribute("goodsName");
		}
        GoodsDao goodsDao=new GoodsDao();
        AppraiseDao appraiseDao=new AppraiseDao();
		Goods goods=goodsDao.Find(goodsName);
		request.setAttribute("goods", goods);//获取商品详情
		List<Object[]> appraiseList=appraiseDao.watch(goodsName);//获取商品评论
		request.setAttribute("appraiseList", appraiseList);
		String userKind=null;
		if(request.getParameter("userKind")!=null) {
			userKind=(String)request.getParameter("userKind");
		}else {
			userKind=(String)request.getAttribute("userKind");
		}
		request.setAttribute("userKind", userKind);//记录用户类型
		RequestDispatcher rd=request.getRequestDispatcher("goodsInformation.jsp");//跳转到浏览商品详情的界面
		rd.forward(request, response);
	}

}
