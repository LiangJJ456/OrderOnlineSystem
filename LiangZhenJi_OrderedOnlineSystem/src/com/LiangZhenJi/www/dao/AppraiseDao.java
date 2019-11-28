package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.Appraise;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class AppraiseDao {
	/**
	 * 查看商品的评论
	 * @param goodsName
	 * @return
	 */
public  List<Object[]> watch(String goodsName) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	List<Object[]> appraiseList=new ArrayList<Object[]>();
	GoodsDao goodsDao=new GoodsDao();
	int goodsId=goodsDao.goodsNameFindId(goodsName);
	String sql = "SELECT userid,grade,comment,comment_photo FROM appraise WHERE goodsid=? ";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	Object[] obj=null ;
	UserDao userDao=new UserDao();
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setInt(1,goodsId);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			int userId = rs.getInt("userid");
			String userName = userDao.idFindUserName(userId);//获取用户名
			int grade = rs.getInt("grade");
			String comment = rs.getString("comment");
			String commentPhoto = rs.getString("comment_photo");
			obj = new Object[4];
			obj[0] = userName;
			obj[1] = grade;
			obj[2] = comment;
			obj[3] = commentPhoto;
			appraiseList.add(obj);
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
	if(rs!=null) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return appraiseList;

}
/**
 * 用户评论商品
 * @param userName
 * @param goodsName
 * @param appraise
 * 注意用户评论商品后会刷新商品和店铺的好评度
 * @return
 */
public  int add(String userName,String goodsName,Appraise appraise) {
	Connection con =DatabaseConnect.getcon();// 获取数据连接
	GoodsDao goodsDao=new GoodsDao();
	UserDao userDao=new UserDao();
	int goodsId=goodsDao.goodsNameFindId(goodsName);
	int userId=userDao.userNameFindId(userName);
	int result=0;
	String sql="insert into appraise values(null,?,?,?,?,?)";
	PreparedStatement prestmt=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setInt(1,userId);
		prestmt.setInt(2,goodsId);
		prestmt.setInt(3,appraise.getGrade());
		prestmt.setString(4,appraise.getComment());
		prestmt.setString(5,appraise.getCommentPhoto());
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
 * 获得用户评论
 * @param userName
 * @param goodsName
 * @return
 */
public  Appraise getUserAppraise(String userName,String goodsName) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	UserDao userDao=new UserDao();
	GoodsDao goodsDao=new GoodsDao();
	int goodsId=goodsDao.goodsNameFindId(goodsName);
	int userId=userDao.userNameFindId(userName);
	Appraise appraise=null;
	String sql="SELECT grade,comment,comment_photo FROM appraise WHERE userid=? and goodsid=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setInt(1,userId);
		prestmt.setInt(2,goodsId);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			int grade = rs.getInt("grade");
			String comment = rs.getString("comment");
			String commentPhoto = rs.getString("comment_photo");
			appraise=new Appraise();
			appraise.setGrade(grade);
			appraise.setComment(comment);
			appraise.setCommentPhoto(commentPhoto);
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
	return appraise;
	
}
/**
 * 获得某用户对某商品的评分
 * @param userName
 * @param goodsName
 * @return
 */
public  int getGrade(String userName,String goodsName) {
	Connection con =DatabaseConnect.getcon();// 获取数据连接
	UserDao userDao=new UserDao();
	GoodsDao goodsDao=new GoodsDao();
	int goodsId=goodsDao.goodsNameFindId(goodsName);
	int userId=userDao.userNameFindId(userName);
	int grade=0;
	String sql="SELECT grade FROM appraise WHERE userid=? and goodsid=?";
	PreparedStatement prestmt=null;
	ResultSet rs=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setInt(1,userId);
		prestmt.setInt(2,goodsId);
		rs = prestmt.executeQuery();
		while(rs.next()) {
			grade=rs.getInt("grade");
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
	return grade;
	
}
/**
 * 当网站管理员发现商家存在刷单的行为时会删除其商品的评论
 * @param userName
 * @param goodsName
 * 注意刷单后商家和商品的好评度会减少
 * @return
 */
public  int delete(String userName,String goodsName) {
	Connection con = DatabaseConnect.getcon();// 获取数据连接
	UserDao userDao=new UserDao();
	GoodsDao goodsDao=new GoodsDao();
	int goodsId=goodsDao.goodsNameFindId(goodsName);
	int userId=userDao.userNameFindId(userName);
	int result=0;
	String sql="delete from appraise where goodsid=? and userid=?";
	PreparedStatement prestmt=null;
	try {
		prestmt = con.prepareStatement(sql);
		prestmt.setInt(1,goodsId);
		prestmt.setInt(2,userId);
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
