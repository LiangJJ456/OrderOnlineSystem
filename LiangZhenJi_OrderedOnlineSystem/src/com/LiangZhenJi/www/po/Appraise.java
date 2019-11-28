package com.LiangZhenJi.www.po;
//客户评论表
public class Appraise {
		private int id;
		private int userId;
		private int goodsId;
		private int grade;//客户对商品的评分，范围为1到10
		private String comment;//客户评论文字
		private String commentPhoto;
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
		public String getComment() {
		return comment;
	}
		public void setComment(String comment) {
		this.comment = comment;
	}
		public String getCommentPhoto() {
		return commentPhoto;
	}
		public void setCommentPhoto(String commentPhoto) {
		this.commentPhoto = commentPhoto;
	}
		public int getGrade() {
		return grade;
	}
		public void setGrade(int grade) {
		this.grade = grade;
	}

}
