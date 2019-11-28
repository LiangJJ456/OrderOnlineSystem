package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.CheckGoods;
import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class CheckGoodsDao {
	/**
	 * 查看待审核的商品
	 * @return
	 */
public  List<Goods> getCheckList() {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	List<Goods> goodsList=new ArrayList<Goods>();
	String sql = "SELECT goodsname,price,goodsphoto,goodskind,shop FROM check_goods WHERE checked=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	Goods goods=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, "未审核");
		rs = prestmt.executeQuery();
		while(rs.next()) {
			String goodsName = rs.getString("goodsname");
			int price = rs.getInt("price");
			String goodsPhoto = rs.getString("goodsphoto");
			String goodsKind = rs.getString("goodskind");
			String shop = rs.getString("shop");
			goods=new Goods();
			goods.setGoodsName(goodsName);
			goods.setPrice(price);
			goods.setGoodsPhoto(goodsPhoto);
			goods.setGoodsKind(goodsKind);
			goods.setShop(shop);
			goodsList.add(goods);
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
	return goodsList;
}
/**
 * 获得已审核的商品信息
 * @param goodsname
 * @return
 */
public  Goods getGoodsInformation(String goodsname) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "SELECT goodsname,price,goodsphoto,goodskind,shop FROM check_goods WHERE goodsname=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	Goods goods=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, goodsname);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			String goodsName = rs.getString("goodsname");
			int price = rs.getInt("price");
			String goodsPhoto = rs.getString("goodsphoto");
			String goodsKind = rs.getString("goodskind");
			String shop = rs.getString("shop");
			goods=new Goods();
			goods.setGoodsName(goodsName);
			goods.setPrice(price);
			goods.setGoodsPhoto(goodsPhoto);
			goods.setGoodsKind(goodsKind);
			goods.setShop(shop);
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
	return goods;
}
/**
 * 审核通过
 * @param goodsName
 * @return
 */
public  int checked(String goodsName) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "update check_goods set checked='审核通过' WHERE goodsname=? ";
	PreparedStatement prestmt=null;
	int result=0;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, goodsName);
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
 * 店家发送商品信息给管理员审核
 * @param checkGoods
 * @return 1
 */
public  int add(CheckGoods checkGoods)  {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "insert into check_goods values(null,?,?,?,?,?,'未审核')";
	PreparedStatement prestmt=null;
	int result=0;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1,checkGoods.getGoodsName());
		prestmt.setInt(2, checkGoods.getPrice());
		prestmt.setString(3, checkGoods.getGoodsPhoto());
		prestmt.setString(4, checkGoods.getGoodsKind());
		prestmt.setString(5,checkGoods.getShop());
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
}
