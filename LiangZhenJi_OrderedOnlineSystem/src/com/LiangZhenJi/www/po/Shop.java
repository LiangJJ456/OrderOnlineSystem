package com.LiangZhenJi.www.po;

public class Shop {
	private int id;
	private String shopName;
	private String shopPhoto;
	private int wellReputed;
	private int sales;
	private int seller;//卖家id

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getWellReputed() {
		return wellReputed;
	}

	public void setWellReputed(int wellReputed) {
		this.wellReputed = wellReputed;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}

	public Shop() {
		// TODO Auto-generated constructor stub
	}

	public String getShopPhoto() {
		return shopPhoto;
	}

	public void setShopPhoto(String shopPhoto) {
		this.shopPhoto = shopPhoto;
	}

}
