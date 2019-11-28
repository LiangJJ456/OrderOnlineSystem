package com.LiangZhenJi.www.service;

import com.LiangZhenJi.www.dao.UserDao;
/**
 * 检查支付密码
 * @author l
 *
 */
public class CheckPaypasswordService {
	public static int check(String username,String paypassword) {
		int result=0;
		UserDao userDao=new UserDao();
		result=userDao.checkPaypassword(username, paypassword);
		return result;
	}
}
