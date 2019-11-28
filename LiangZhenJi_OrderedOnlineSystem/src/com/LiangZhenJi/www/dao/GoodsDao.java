package com.LiangZhenJi.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.util.DatabaseConnect;

public class GoodsDao {
	/**
	 * 返回所有商品信息
	 * @return
	 */
	public  List<Goods> getAll()  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		List<Goods> goodsList=new ArrayList<Goods>();
		String sql = "SELECT goodsname,price,goodsphoto,well_reputed,sales,goodskind,shop FROM goods";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Goods goods=null;
		try {
			prestmt = con.prepareStatement(sql);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String goodsName = rs.getString("goodsname");
				int price = rs.getInt("price");
				String goodsPhoto = rs.getString("goodsphoto");
				int wellReputed = rs.getInt("well_reputed");
				int sales = rs.getInt("sales");
				String goodsKind = rs.getString("goodskind");
				String shop = rs.getString("shop");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
				goods.setGoodsPhoto(goodsPhoto);
				goods.setWellReputed(wellReputed);
				goods.setSales(sales);
				goods.setGoodsKind(goodsKind);
				goods.setShop(shop);
				goodsList.add(goods);
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
		return goodsList;

	}
	/**
	 * 找到店铺的的商品
	 * @param shopName
	 * @return
	 */
	public  List<Goods> shopNameFindGoods(String shopName)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		List<Goods> shopGoodsList=new ArrayList<Goods>();
		String sql = "SELECT goodsname,price,goodsphoto,goodskind FROM goods WHERE shop=?";
		PreparedStatement prestmt=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, shopName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs=null;
		Goods goods=null;
		try {
			rs = prestmt.executeQuery();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		}
		try {
			while(rs.next()) {
				String goodsName = rs.getString("goodsname");
				int price = rs.getInt("price");
				String goodsPhoto = rs.getString("goodsphoto");
				String goodsKind = rs.getString("goodskind");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
				goods.setGoodsPhoto(goodsPhoto);
				goods.setGoodsKind(goodsKind);
				shopGoodsList.add(goods);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		return shopGoodsList;

	}
	/**
	 * 通过商品找到店铺名
	 * @param goodsName
	 * @return
	 */
	public  String goodsNameFindshop(String goodsName)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String shopName=null;
		String sql = "SELECT shop FROM goods WHERE goodsname=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				shopName=rs.getString("shop");
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
		return shopName;

	}
	
	/**
	 * 通过商品名查询id
	 * @param goodsName
	 * @return
	 */
	public  int goodsNameFindId(String goodsName)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT id FROM goods WHERE goodsname=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		int goodsId=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				goodsId = rs.getInt("id");
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
		return goodsId;

	}
	/**
	 * 通过id找商品信息
	 * @param goodsId
	 * @return
	 */
	public  Goods idFindGoodsName(int goodsId)  {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT goodsname,goodsphoto FROM goods WHERE id=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		String goodsName=null;
		String goodsPhoto=null;
		Goods goods=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setInt(1,goodsId);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				goods=new Goods();
				goodsName = rs.getString("goodsname");
				goodsPhoto=rs.getString("goodsphoto");
				goods.setGoodsName(goodsName);
				goods.setGoodsPhoto(goodsPhoto);
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
		return goods;

	}
	/**
	 * 查询商品信息
	 * @param goodsName
	 * @return
	 */
	public  Goods Find(String goodsName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "SELECT price,goodsphoto,well_reputed,sales,goodskind,shop FROM goods WHERE goodsname=?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Goods goods=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				int price = rs.getInt("price");
				String goodsPhoto = rs.getString("goodsphoto");
				int wellReputed = rs.getInt("well_reputed");
				int sales = rs.getInt("sales");
				String goodsKind = rs.getString("goodskind");
				String shop = rs.getString("shop");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
				goods.setGoodsPhoto(goodsPhoto);
				goods.setWellReputed(wellReputed);
				goods.setSales(sales);
				goods.setGoodsKind(goodsKind);
				goods.setShop(shop);
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
		return goods;
		
	}
	/**
	 * 搜索商品，支持模糊查找
	 * @param msg，msg可以是商品名字或者商品类别
	 * @return
	 */
	public  List<Goods> likeFind(String msg) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		List<Goods> goodsList=new ArrayList<Goods>();
		String sql = "SELECT goodsname,price,goodsphoto,well_reputed,sales,goodskind,shop FROM goods WHERE goodsname LIKE ? or goodskind LIKE ?";
		PreparedStatement prestmt=null;
		ResultSet rs=null;
		Goods goods=null;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1,"%"+ msg+"%");
			prestmt.setString(2,"%"+ msg+"%");
			rs = prestmt.executeQuery();
			while(rs.next()) {
				String goodsName = rs.getString("goodsname");
				int price = rs.getInt("price");
				String goodsPhoto = rs.getString("goodsphoto");
				int wellReputed = rs.getInt("well_reputed");
				int sales = rs.getInt("sales");
				String goodsKind = rs.getString("goodskind");
				String shop = rs.getString("shop");
				goods=new Goods();
				goods.setGoodsName(goodsName);
				goods.setPrice(price);
				goods.setGoodsPhoto(goodsPhoto);
				goods.setWellReputed(wellReputed);
				goods.setSales(sales);
				goods.setGoodsKind(goodsKind);
				goods.setShop(shop);
				goodsList.add(goods);
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
		return goodsList;
		
	}
	/**
	 * 添加商品
	 * @param goods
	 * @return
	 */
	public  int add(Goods goods) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "insert into goods values(null,?,?,?,'0','0',?,?)";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goods.getGoodsName());
			prestmt.setInt(2, goods.getPrice());
			prestmt.setString(3, goods.getGoodsPhoto());
			prestmt.setString(4, goods.getGoodsKind());
			prestmt.setString(5, goods.getShop());
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
	 * 删除商品，同时要删除商品的订单还有评论
	 * @param goodsName
	 * @return 3（如果删除成功就要返回3）
	 */
	public  int delete(String goodsName){
		Connection con =DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT id FROM goods WHERE goodsname=?";
		String sql2="delete from appraise where goodsid=?";//删除商品评论
		String sql3="delete from person_order where goodsid=?";//删除商品的订单
		String sql4 = "delete from goods where goodsname=?";//删除商品信息
		PreparedStatement prestmt=null;
		PreparedStatement prestmt2=null;
		PreparedStatement prestmt3=null;
		PreparedStatement prestmt4=null;
		ResultSet rs=null;
		int result=0;
		int result2=0;
		int result3=0;
		int goodsId=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				goodsId = rs.getInt("id");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prestmt2 = con.prepareStatement(sql2);
			prestmt2.setInt(1, goodsId );
			result = prestmt2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prestmt3 = con.prepareStatement(sql3);
			prestmt3.setInt(1,  goodsId);
			result2 = prestmt3.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prestmt4 = con.prepareStatement(sql4);
			prestmt4.setString(1, goodsName);
			result3 = prestmt4.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
			if(prestmt2!=null) {
				prestmt2.close();
			}
			if(prestmt3!=null) {
				prestmt3.close();
			}
			if(prestmt4!=null) {
				prestmt4.close();
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
		return result+result2+result3;
		
	}
	/**
	 * 修改商品信息
	 * @param goods
	 * @param goodsname(这个商品名是之前的商品名字)
	 * @return 1（成功）
	 */
	public int updateGoodsInformation(Goods goods,String goodsname) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql = "update goods set goodsname=?,price=?,goodsphoto=? WHERE goodsname=? ";
		PreparedStatement prestmt=null;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goods.getGoodsName());
			prestmt.setInt(2, goods.getPrice());
			prestmt.setString(3, goods.getGoodsPhoto());
			prestmt.setString(4, goodsname);
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
	 * 当商家确定订单后，商品的销量就会增加
	 * @param goodsName
	 * @return 1（增加成功）
	 */
	public int addSales(String goodsName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT sales FROM goods WHERE goodsname=?";//先找到商品对应的销量
		String sql2="update goods set sales=? where goodsname=?";//更新商品的销售量
		PreparedStatement prestmt=null;
		PreparedStatement prestmt2=null;
		ResultSet rs=null;
		int sales=-1;
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				sales = rs.getInt("sales");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sales!=-1) {
			sales=sales+1;
			try {
				prestmt2 = con.prepareStatement(sql2);
				prestmt2.setInt(1, sales);
				prestmt2.setString(2,goodsName);
				result = prestmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
			if(prestmt2!=null) {
				prestmt2.close();
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
		return result;
	}
	/**
	 *用户评论后刷新商品的好评度
	 * @param goodsName
	 * @param userName
	 * @return
	 */
	public int addReputed(String goodsName,String userName) {
		Connection con = DatabaseConnect.getcon();// 获取数据连接
		String sql="SELECT well_reputed FROM goods WHERE goodsname=?";//先找到商品对应的好评度
		String sql2="update goods set well_reputed=? where goodsname=?";//更新商品的好评度
		PreparedStatement prestmt=null;
		PreparedStatement prestmt2=null;
		ResultSet rs=null;
		int wellReputed=0;
		AppraiseDao appraiseDao=new AppraiseDao();
		int grade=appraiseDao.getGrade(userName, goodsName);//获取用户评论后的分数
		int result=0;
		try {
			prestmt = con.prepareStatement(sql);
			prestmt.setString(1, goodsName);
			rs = prestmt.executeQuery();
			while(rs.next()) {
				wellReputed = rs.getInt("well_reputed");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(wellReputed!=0) {
			wellReputed=wellReputed+grade;
			try {
				prestmt2 = con.prepareStatement(sql2);
				prestmt2.setInt(1, wellReputed);
				prestmt2.setString(2,goodsName);
				result = prestmt2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DatabaseConnect.releaseConnection(con);//把数据连接放回连接池
		try {
			if(prestmt!=null) {
				prestmt.close();
			}
			if(prestmt2!=null) {
				prestmt2.close();
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
		return result;
	}
}
