package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.Shop;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class ShopDao {
	/**
	 * 开店铺
	 * @param shop
	 * @return
	 */
	public  int add(Shop shop) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into shop values(null,?,?,'0','0',?)";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, shop.getShopName());
			prestmt.setString(2, shop.getShopPhoto());
			prestmt.setInt(3, shop.getSeller());//注意要先获得用户的id
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
	 * 当商家确定订单后，店铺的销量就会增加
	 * @param goodsName
	 * @return 1（增加成功）
	 */
	public  int addSales(String goodsName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT sales FROM shop WHERE shopname=(SELECT shop FROM goods WHERE goodsname=?)";//先找到店铺对应的销量
		String sql2="update shop set sales=? where shopname=(SELECT shop FROM goods WHERE goodsname=?)";//更新店铺的销售量
		PreparedStatement prestmt=null;
		PreparedStatement prestmt2=null;
		ResultSet rs=null;
		int sales=-1;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				sales = rs.getInt("sales");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sales!=-1) {
			sales=sales+1;
			try {
				prestmt2 = con.prepareStatement(sql2);
				prestmt2.setInt(1, sales);
				prestmt2.setString(2,goodsName);
				result = prestmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
			if(prestmt2!=null) {
				prestmt2.close();
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
		return result;
	}
	/**
	 * 用户评论后店铺的好评度会刷新
	 * @param goodsName
	 * @param userName
	 * @return
	 */
	public   int addReput (String goodsName,String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT well_reputed FROM shop WHERE shopname=(SELECT shop FROM goods WHERE goodsname=?)";//先找到店铺对应的好评度
		String sql2="update shop set well_reputed=? where shopname=(SELECT shop FROM goods WHERE goodsname=?)";//更新店铺的好评度
		PreparedStatement prestmt=null;
		PreparedStatement prestmt2=null;
		ResultSet rs=null;
		int wellReputed=0;
		AppraiseDao appraiseDao=new AppraiseDao();
		int grade=appraiseDao.getGrade(userName, goodsName);//获取用户评论后的分数
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				wellReputed = rs.getInt("well_reputed");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wellReputed!=0) {
			wellReputed=wellReputed+grade;
			try {
				prestmt2 = con.prepareStatement(sql2);
				prestmt2.setInt(1,wellReputed);
				prestmt2.setString(2,goodsName);
				result = prestmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
			if(prestmt2!=null) {
				prestmt2.close();
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
		return result;
		
	}
	/**
	 * 查看店铺信息
	 * @return
	 */
	public  List<Shop> findAll(){
		Connection con =DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT shopname,shopphoto,well_reputed,sales,sellerid FROM shop ";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Shop shop =null;
		List<Shop> shopList= new ArrayList<Shop>();
		try {
			prestmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String shopName=rs.getString("shopname");
				String shopPhoto=rs.getString("shopPhoto");
				int wellReputed=rs.getInt("well_reputed");
				 int sales = rs.getInt("sales");
				 int sellerId = rs.getInt("sellerId");
				 shop=new Shop();
				shop.setShopName(shopName);
				shop.setShopPhoto(shopPhoto);
				shop.setWellReputed(wellReputed);
				shop.setSales(sales);
				shop.setSeller(sellerId);
				shopList.add(shop);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return shopList;
	}
	/**
	 * 模糊搜索店铺
	 * @param msg
	 * @return
	 */
	public  List<Shop> likeFind(String msg){
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT shopname,shopphoto,well_reputed,sales,sellerid FROM shop where shopname LIKE ?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Shop shop =null;
		List<Shop> shopList= new ArrayList<Shop>();
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, "%"+ msg+"%");
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String shopName=rs.getString("shopname");
				String shopPhoto=rs.getString("shopPhoto");
				int wellReputed=rs.getInt("well_reputed");
				int sales = rs.getInt("sales");
				int sellerId = rs.getInt("sellerId");
				shop=new Shop();
				shop.setShopName(shopName);
				shop.setShopPhoto(shopPhoto);
				shop.setWellReputed(wellReputed);
				shop.setSales(sales);
				shop.setSeller(sellerId);
				shopList.add(shop);
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
		return shopList;
	}
	/**
	 * 通过店主名找到店铺名
	 * @param seller
	 * @return
	 */
	public  String sellerFindShopName(String seller){
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT shopname FROM shop WHERE sellerid=? ";
		UserDao userDao=new UserDao();
		int sellerId=userDao.userNameFindId(seller);//获得店主的ID
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		String shopName =null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1, sellerId);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				shopName=rs.getString("shopname");
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
		return shopName;
	}

}
