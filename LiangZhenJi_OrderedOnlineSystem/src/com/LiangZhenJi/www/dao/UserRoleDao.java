package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.LiangZhenJi.www.util.DatabaseConnect;

public class UserRoleDao {
	/**
	 * 注册成为商家
	 * @param userName
	 * @return
	 */
	public static int addSeller(String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into userrole values(null,?,'2')";
		PreparedStatement prestmt=null;
		UserDao userDao=new UserDao();
		int userId=userDao.userNameFindId(userName);
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId );//注意要先获得用户的id
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
	 * @param userName
	 * @return
	 */
	public static int addUser(String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into userrole values(null,?,'1')";
		PreparedStatement prestmt=null;
		UserDao userDao=new UserDao();
		int userId=userDao.userNameFindId(userName);
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,userId );//注意要先获得用户的id
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
