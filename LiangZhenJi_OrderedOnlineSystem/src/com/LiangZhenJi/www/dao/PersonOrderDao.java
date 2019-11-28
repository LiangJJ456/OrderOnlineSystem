package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.util.DatabaseConnect;
/**
 * 记录订单
 * @author l
 *
 */
public class PersonOrderDao {
	/**
	 * 客户购买商品，用户订单表就会记录订单信息，但订单没确认
	 * 店家须确认订单后才会刷新商品的销量和店铺的销量(不在这个类中)
	 */
	public int add(String userName,String goodsName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into person_order values(null,?,?,'未确认订单')";
		PreparedStatement prestmt=null;
		UserDao userDao=new UserDao();
		int userId=userDao.userNameFindId(userName);
		GoodsDao goodsDao=new GoodsDao();
		int goodsId=goodsDao.goodsNameFindId(goodsName);
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId );//注意要先获得用户的id
			prestmt.setInt(2,goodsId);//注意要先获得商品的id
			result = prestmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 确定订单
	 * @param userName
	 * @param goodsName
	 * @return
	 */
	public  int ensure(String userName,String goodsName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "update person_order set ensure_order='确认订单' WHERE userid=? and goodsid=?";
		PreparedStatement prestmt=null;
		UserDao userDao=new UserDao();
		int userId=userDao.userNameFindId(userName);
		GoodsDao goodsDao=new GoodsDao();
		int goodsId=goodsDao.goodsNameFindId(goodsName);
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId );//注意要先获得用户的id
			prestmt.setInt(2,goodsId);//注意要先获得商品的id
			result = prestmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 用户查看自己的订单
	 * @param userName
	 * @return
	 */
	public  List<Goods> userGetOrder(String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT goodsname,price,goodsphoto,shop FROM goods WHERE id=ANY(SELECT goodsid FROM person_order WHERE userid=?)";
		PreparedStatement prestmt=null;
		UserDao userDao=new UserDao();
		int userId=userDao.userNameFindId(userName);
		Goods goods=new Goods();
		List<Goods> orderList=new ArrayList<Goods>();
		ResultSet rs=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId );//注意要先获得用户的id
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String goodsName = rs.getString("goodsname");
				int price = rs.getInt("price");
				String goodsPhoto = rs.getString("goodsphoto");
				String shop = rs.getString("shop");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
				goods.setGoodsPhoto(goodsPhoto);
				goods.setShop(shop);
				orderList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	/**
	 * 商家获得要处理的订单
	 * @param seller
	 * @return
	 */
	public  List<Object[]> sellerGetOrder(String seller) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT userid,goodsid FROM person_order WHERE goodsid=Any(SELECT id FROM goods WHERE shop=?) AND ensure_order='未确认订单'";
		PreparedStatement prestmt=null;
		ShopDao shopDao=new ShopDao();
		String shopName=shopDao.sellerFindShopName(seller);
		Goods goods=null;
		Object[] obj=null;
		List<Object[]> orderList=new ArrayList<>();
		ResultSet rs=null;
		GoodsDao goodsDao=new GoodsDao();
		UserDao userDao=new UserDao();
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,shopName );
			rs = prestmt.executeQuery();
			while(rs.next()) {
				goods=goodsDao.idFindGoodsName(rs.getInt("goodsid"));//获得商品信息
				obj=new Object[3];//注意要new一个新的对象
				obj[0]=goods.getGoodsName();
				obj[1]=goods.getGoodsPhoto();
				obj[2]=userDao.idFindUserName(rs.getInt("userid"));//获得购买商品的用户
				orderList.add(obj);//获得店家要处理的订单
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

}
