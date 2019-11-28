package com.LiangZhenJi.www.po;
/**
 * 审核商品
 * @author l
 *
 */
public class CheckBusiness {
		private int id;
		private String seller;
		private String shopName;
		private String shopPhoto;
		private String check;//审核状态
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getSeller() {
			return seller;
		}
		public void setSeller(String seller) {
			this.seller = seller;
		}
		public String getShopName() {
			return shopName;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getCheck() {
			return check;
		}
		public void setCheck(String check) {
			this.check = check;
		}
		public String getShopPhoto() {
			return shopPhoto;
		}
		public void setShopPhoto(String shopPhoto) {
			this.shopPhoto = shopPhoto;
		}

}
