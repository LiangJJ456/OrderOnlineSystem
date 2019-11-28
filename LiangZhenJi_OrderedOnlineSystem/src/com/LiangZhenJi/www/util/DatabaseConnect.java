package com.LiangZhenJi.www.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class DatabaseConnect {
	// 数据库地址
		private static String dburl = "jdbc:mysql://localhost:3306/ordered_online_system";
		// 用户名
		private static String dbUserName = "root";
		// 密码
		private static String dbPassword = "123456";
		
		private  static final LinkedList<Connection> dateList =new LinkedList<>();//创建连接池
		
		/**
		 * 取出连接池中一个连接，删除第一个连接返回
		 * @return
		 */
		public  static Connection getcon() {
			final Connection conn;
			if(dateList.size()==0) {
				//初始化连接数量，一次创建10个连接
				for(int i=0;i<10;i++) {
					Connection com=null ;
					try {
						Class.forName("com.mysql.jdbc.Driver");
						com = DriverManager.getConnection(dburl, dbUserName, dbPassword);
						dateList.add(com);//将连接加入连接池中
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				 conn=dateList.removeFirst();
			}
			else {
				 conn=dateList.removeFirst();
			}
			return conn;
		}
		
		/**
		 * 将连接放回连接池
		 * @param conn
		 */
		public static void releaseConnection(Connection conn) {
			dateList.add(conn);
		}
}
