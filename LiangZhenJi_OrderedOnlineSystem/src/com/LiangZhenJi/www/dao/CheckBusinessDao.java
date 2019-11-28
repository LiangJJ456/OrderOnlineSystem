package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.CheckBusiness;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class CheckBusinessDao {
	/**
	 * 查看待审核的商家和店铺
	 * @return
	 */
public  List<CheckBusiness> getCheckList() {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	List<CheckBusiness> checkBusinessList=new ArrayList<CheckBusiness>();
	String sql = "SELECT seller,shopname,shopphoto FROM check_business WHERE checked=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	CheckBusiness checkBusiness=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, "未审核");
		rs = prestmt.executeQuery();
		while(rs.next()) {
			String seller = rs.getString("seller");
			String shopName = rs.getString("shopname");
			String shopPhoto = rs.getString("shopphoto");
			checkBusiness = new CheckBusiness();
			checkBusiness.setSeller(seller);
			checkBusiness.setShopName(shopName);
			checkBusiness.setShopPhoto(shopPhoto);
			checkBusinessList.add(checkBusiness);
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
	return checkBusinessList;
}
/**
 * 获得已审核的店家店铺信息
 * @param seller
 * @return
 */
public  CheckBusiness getBusinessInformation(String seller) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "SELECT seller,shopname,shopphoto FROM check_business WHERE seller=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	CheckBusiness checkBusiness=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, seller);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			String shopName= rs.getString("shopname");
			String shopPhoto= rs.getString("shopphoto");
			checkBusiness=new CheckBusiness();
			checkBusiness.setSeller(seller);
			checkBusiness.setShopName(shopName);
			checkBusiness.setShopPhoto(shopPhoto);
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
	return checkBusiness;
}
/**
 * 审核通过
 * @param seller
 * @return
 */
public  int checked(String seller) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "update check_business set checked='审核通过' WHERE seller=? ";
	PreparedStatement prestmt=null;
	int result=0;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, seller);
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
 * 用户发送注册商家和店铺信息给管理员审核
 * @param checkBusiness
 * @return 1
 */
public  int add(CheckBusiness checkBusiness)  {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql = "insert into check_business values(null,?,?,?,'未审核')";
	PreparedStatement prestmt=null;
	int result=0;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, checkBusiness.getSeller());
		prestmt.setString(2,checkBusiness.getShopName());
		prestmt.setString(3,checkBusiness.getShopPhoto());
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
