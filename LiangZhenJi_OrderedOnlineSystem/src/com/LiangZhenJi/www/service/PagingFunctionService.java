package com.LiangZhenJi.www.service;

import java.util.List;

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.po.Goods;
import com.LiangZhenJi.www.po.Page;
import com.LiangZhenJi.www.po.Shop;

/**
 * 分页功能，将page类实例化
 * @author l
 *
 */
public class PagingFunctionService {
	/**
	 *  获取没排序的相应商品页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Goods> getGoodsWithPage(int pageNum,int pageSize){
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> goodsList=goodsDao.getAll();
		int totalRecord=goodsList.size();
		Page<Goods> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetGoodsListByPageService.getGoodsList(startIndex, pageSize));//获得每页商品数据
		return pg;
	}
	/**
	 * 获取模糊搜索出的相应商品页面
	 * @param pageNum
	 * @param pageSize
	 * @param msg
	 * @return
	 */
	public static Page<Goods> getLikeGoodsWithPage(int pageNum,int pageSize,String msg){
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> goodsList=goodsDao.likeFind(msg);
		int totalRecord=goodsList.size();
		Page<Goods> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetGoodsListByPageService.getLikeGoodsList(startIndex, pageSize,msg));//获得每页商品数据
		return pg;
	}
	/**
	 * 获取价格排序的相应商品页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Goods> getGoodsWithPageByPrice(int pageNum,int pageSize){
		List<Goods> goodsList=SelectGoodsService.getSortByPrice();
		int totalRecord=goodsList.size();
		Page<Goods> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetGoodsListByPageService.getGoodsListByPrice(startIndex, pageSize));//获得每页商品数据
		return pg;
	}
	/**
	 * 获取销量排序的相应商品页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Goods> getGoodsWithPageBySales(int pageNum,int pageSize){
		List<Goods> goodsList=SelectGoodsService.getSortBySales();
		int totalRecord=goodsList.size();
		Page<Goods> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetGoodsListByPageService.getGoodsListBySales(startIndex, pageSize));//获得每页商品数据
		return pg;
	}
	/**
	 * 获取好评度排序的相应商品页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Goods> getGoodsWithPageByReputed(int pageNum,int pageSize){
		List<Goods> goodsList=SelectGoodsService.getSortByReputed();
		int totalRecord=goodsList.size();
		Page<Goods> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetGoodsListByPageService.getGoodsListByReputed(startIndex, pageSize));//获得每页商品数据
		return pg;
	}
	/**
	 * 获取没排序的相应店家页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Shop> getShopWithPage(int pageNum,int pageSize){
		ShopDao shopDao=new ShopDao();
		List<Shop> shopList=shopDao.findAll();
		int totalRecord=shopList.size();
		Page<Shop> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetShopListByPageService.getShopList(startIndex, pageSize));//获得每页店家数据
		return pg;
	}
	/**
	 * 获取模糊搜索的相应店家页面
	 * @param pageNum
	 * @param pageSize
	 * @param msg
	 * @return
	 */
	public static Page<Shop> getLikeShopWithPage(int pageNum,int pageSize,String msg){
		ShopDao shopDao=new ShopDao();
		List<Shop> shopList=shopDao.likeFind(msg);
		int totalRecord=shopList.size();
		Page<Shop> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetShopListByPageService.getLikeShopList(startIndex, pageSize,msg));//获得每页店家数据
		return pg;
	}
	/**
	 * 获取销量排序的相应店家页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Shop> getShopWithPageBySales(int pageNum,int pageSize){
		List<Shop> shopList=SelectShopService.getSortBySales();
		int totalRecord=shopList.size();
		Page<Shop> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetShopListByPageService.getShopListBySales(startIndex, pageSize));//获得每页店家数据
		return pg;
	}
	/**
	 *  获取好评度排序的相应店家页面
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public static Page<Shop> getShopWithPageByReputed(int pageNum,int pageSize){
		List<Shop> shopList=SelectShopService.getSortByReputed();
		int totalRecord=shopList.size();
		Page<Shop> pg=new Page<>(pageNum, pageSize, totalRecord);
		int startIndex=pg.getStartIndex();
		pg.setList(GetShopListByPageService.getShopListByReputed(startIndex, pageSize));//获得每页店家数据
		return pg;
	}
	
}
