package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.User;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class UserDao {
	/**
	 * 查看个人信息
	 * 
	 * @return user
	 */
	public  User find(String username)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT username,password,sex,personphoto,paypassword,address FROM user WHERE username=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,username);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String personPhoto = rs.getString("personphoto");
				String payPassword = rs.getString("paypassword");
				String address = rs.getString("address");
				user=new User();
				user.setUserName(userName);
				user.setPassword(password);
				user.setSex(sex);
				user.setPersonPhoto(personPhoto);
				user.setPayPassword(payPassword);
				user.setAddress(address);
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
		return user;

	}
	/**
	 * 通过用户名查询id
	 * @param username
	 * @return
	 */
	public  int userNameFindId(String username)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT id FROM user WHERE username=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		int userId=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,username);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				userId = rs.getInt("id");
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
		return userId;

	}
	/**
	 * 通过id查询用户名
	 * @param userId
	 * @return
	 */
	public  String idFindUserName(int userId)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT username FROM user WHERE id=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		String userName=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				userName = rs.getString("username");
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
		return userName;

	}
	
	/**
	 * 查看个人订单信息
	 * @param username
	 * @return
	 */
	public  List<Goods> getPersonOrder(String username)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		List<Goods> goodsList=new ArrayList<Goods>();
		String sql = "SELECT goodsname,price,shop FROM goods WHERE id=ANY(SELECT goodsid FROM person_order WHERE userid=(SELECT id FROM user WHERE username=?))";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Goods goods=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,username);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String goodsName = rs.getString("goodsname");
				int price = rs.getInt("price");
				String shop = rs.getString("shop");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
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
	 * 修改用户信息
	 * @param user
	 * @param username(这个用户名是由登录的用户名传过来的)
	 * @return
	 */
	public  int updateUserInformation(User user,String username) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "update user set username=?,password=?,sex=?,personphoto=?,paypassword=?,address=? WHERE username=? ";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, user.getUserName());
			prestmt.setString(2, user.getPassword());
			prestmt.setString(3, user.getSex());
			prestmt.setString(4, user.getPersonPhoto());
			prestmt.setString(5, user.getPayPassword());
			prestmt.setString(6, user.getAddress());
			prestmt.setString(7, username);
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
	 * 注册用户
	 * @param user
	 * @return （如果注册成功则返回1）
	 */
	public int add(User user)  {
		Connection con =DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into user values(null,?,?,?,?,?,?)";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, user.getUserName());
			prestmt.setString(2, user.getPassword());
			prestmt.setString(3, user.getSex());
			prestmt.setString(4, user.getPersonPhoto());
			prestmt.setString(5, user.getPayPassword());
			prestmt.setString(6, user.getAddress());
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
	 * 检查用户名和密码是否正确
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
public  int check(String username,String password) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	String sql="SELECT username,password FROM user WHERE username=? AND password=?";
	PreparedStatement prestmt = null;
	int result=0;
	ResultSet rs=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, username);
		prestmt.setString(2, password);
		rs=prestmt.executeQuery();
		while(rs.next()) {
			result=1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	DatabaseConnect.releaseConnection(con);
	return result;
	}
	
/**
 * 判断用户所选的用户类别是否正确
 * @param userName
 * @param password
 * @param roleName
 * @return
 */
public  int select(String userName,String password,String roleName) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	int result=0;
	String sql = "SELECT id  FROM userrole WHERE userid=(SELECT id  FROM user WHERE username=? and password=?) AND roleid=(SELECT id  FROM role WHERE rolename=?) ";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setString(1, userName);
		prestmt.setString(2, password);
		prestmt.setString(3, roleName);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			result=1;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	DatabaseConnect.releaseConnection(con);
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
	return result;
}
	/**
	 * 检查支付密码
	 * @param username
	 * @param paypassword
	 * @return
	 */
	public  int checkPaypassword(String username,String paypassword) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT username,paypassword FROM user WHERE username=? AND paypassword=?";
		PreparedStatement prestmt = null;
		int result=0;
		ResultSet rs=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, username);
			prestmt.setString(2, paypassword);
			rs=prestmt.executeQuery();
			while(rs.next()) {
				result=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);
		return result;
		}
}
