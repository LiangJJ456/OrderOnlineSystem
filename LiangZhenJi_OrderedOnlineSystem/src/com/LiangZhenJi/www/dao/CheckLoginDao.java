package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.CheckLogin;
import com.LiangZhenJi.www.po.User;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class CheckLoginDao {
	/**
	 * 查看未审核的用户
	 * @return
	 */
	public  List<User> getUnCheckList() {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		List<User> checkList=new ArrayList<User>();
		String sql = "SELECT username,password,sex,personphoto,paypassword,address FROM check_login WHERE checked=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, "未审核");
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
				checkList.add(user);
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
		return checkList;

	}
	/**
	 * 获得已通过审核的用户信息
	 * @param checkname
	 * @return
	 */
	public User getUserInformation(String checkname) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT username,password,sex,personphoto,paypassword,address FROM check_login WHERE username=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		User user=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, checkname);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String userName = rs.getString("username");
				String password = rs.getString("password");
				String sex = rs.getString("sex");
				String personPhoto = rs.getString("personphoto");
				String payPassword = rs.getString("paypassword");
				String address = rs.getString("address");
				user = new User();
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
	 * 审核通过
	 * @param userName
	 * @return
	 */
	public int checked(String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "update check_login set checked='审核通过' WHERE username=? ";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, userName);
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
	 * 用户发送注册信息给管理员审核
	 * @param checkLogin
	 * @return 1
	 */
	public int add(CheckLogin checkLogin)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into check_login values(null,?,?,?,?,?,?,'未审核')";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, checkLogin.getUserName());
			prestmt.setString(2, checkLogin.getPassword());
			prestmt.setString(3, checkLogin.getSex());
			prestmt.setString(4, checkLogin.getPersonPhoto());
			prestmt.setString(5, checkLogin.getPayPassword());
			prestmt.setString(6, checkLogin.getAddress());
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
