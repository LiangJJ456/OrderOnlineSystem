package com.LiangZhenJi.www.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * 获取相应页面的商品
 * @param startIndex 商品的索引
 * @param pageSize   每页的商品数
 * @return
 * @author l
 *
 */

public class GetGoodsListByPageService {
	/**
	 * 获取没排序的相应页面的商品
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Goods> getGoodsList(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Goods> goodsList=new ArrayList<Goods>();
		GoodsDao goodsDao=new GoodsDao();
		Iterator<Goods> allList=goodsDao.getAll().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				goodsList.add(allList.next());
				if(goodsList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return goodsList;
	}
	/**
	 * 获取模糊查询的相应页面的商品
	 * @param startIndex
	 * @param pageSize
	 * @param msg 用户输入的搜索信息
	 * @return
	 */
	public static List<Goods> getLikeGoodsList(int startIndex,int pageSize,String msg){
		int startIndexCompare=0;
		List<Goods> goodsList=new ArrayList<Goods>();
		GoodsDao goodsDao=new GoodsDao();
		Iterator<Goods> allList=goodsDao.likeFind(msg).iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				goodsList.add(allList.next());
				if(goodsList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return goodsList;
	}
	/**
	 * 获取价格排序的相应页面的商品
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Goods> getGoodsListByPrice(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Goods> goodsList=new ArrayList<Goods>();
		Iterator<Goods> allList=SelectGoodsService.getSortByPrice().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				goodsList.add(allList.next());
				if(goodsList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return goodsList;
	}
	/**
	 * 获取销量排序的相应页面的商品
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Goods> getGoodsListBySales(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Goods> goodsList=new ArrayList<Goods>();
		Iterator<Goods> allList=SelectGoodsService.getSortBySales().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				goodsList.add(allList.next());
				if(goodsList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return goodsList;
	}
	/**
	 * 获取好评度排序的相应页面的商品
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Goods> getGoodsListByReputed(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Goods> goodsList=new ArrayList<Goods>();
		Iterator<Goods> allList=SelectGoodsService.getSortByReputed().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				goodsList.add(allList.next());
				if(goodsList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return goodsList;
	}

}
