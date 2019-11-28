package com.LiangZhenJi.www.po;
//客户订单表
public class PersonOrder {
	private int id;
	private int userId;
	private int goodsId;
	private String ensureOrder;
	public String getEnsureOrder() {
		return ensureOrder;
	}
	public void setEnsureOrder(String ensureOrder) {
		this.ensureOrder = ensureOrder;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	

}
