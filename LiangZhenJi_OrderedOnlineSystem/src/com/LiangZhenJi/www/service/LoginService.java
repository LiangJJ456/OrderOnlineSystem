package com.LiangZhenJi.www.service;

import com.LiangZhenJi.www.dao.UserDao;

public class LoginService {
	
	/**
	 * 检查用户名和密码是否正确
	 * @param username
	 * @param password
	 * @return
	 * @throws SQLException
	 */
public static int check(String username,String password) {
	int result=0;
	UserDao userDao=new UserDao();
	result=userDao.check(username, password);
	return result;
	}
	
/**
 * 判断用户所选的用户类别是否正确
 * @param userName
 * @param password
 * @param roleName
 * @return
 */
public static int select(String userName,String password,String roleName) {
	int result=0;
	UserDao userDao=new UserDao();
	result=userDao.select(userName, password, roleName);
	return result;
}

}
