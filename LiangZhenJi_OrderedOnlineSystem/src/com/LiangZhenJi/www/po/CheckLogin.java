package com.LiangZhenJi.www.po;
/**
 * 审核注册
 * @author l
 *
 */
public class CheckLogin {
	private int id;
	private String userName;
	private String password;
	private String sex;
	private String personPhoto;
	private String payPassword;
	private String address;
	private String check;//记录审核状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPersonPhoto() {
		return personPhoto;
	}
	public void setPersonPhoto(String personPhoto) {
		this.personPhoto = personPhoto;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	
}
