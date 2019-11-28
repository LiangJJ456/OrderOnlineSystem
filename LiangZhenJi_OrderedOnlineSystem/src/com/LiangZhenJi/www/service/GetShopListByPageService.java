package com.LiangZhenJi.www.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.po.Shop;

/**
 * 获取相应页面的店家
 * @param startIndex 店家的索引
 * @param pageSize   每页的店家数
 * @return
 * @author l
 *
 */
public class GetShopListByPageService {
	/**
	 * 获取没排序的相应页面的店家
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Shop> getShopList(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Shop> shopList=new ArrayList<Shop>();
		ShopDao shopDao=new ShopDao();
		Iterator<Shop> allList=shopDao.findAll().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				shopList.add(allList.next());
				if(shopList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return shopList;
	}
	/**
	 * 获取模糊查询的相应页面的店家
	 * @param startIndex
	 * @param pageSize
	 * @param msg 用户输入的信息
	 * @return
	 */
	public static List<Shop> getLikeShopList(int startIndex,int pageSize,String msg){
		int startIndexCompare=0;
		List<Shop> shopList=new ArrayList<Shop>();
		ShopDao shopDao=new ShopDao();
		Iterator<Shop> allList=shopDao.likeFind(msg).iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				shopList.add(allList.next());
				if(shopList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return shopList;
	}
	/**
	 * 获取销量排序的相应页面的店家
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Shop> getShopListBySales(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Shop> shopList=new ArrayList<Shop>();
		Iterator<Shop> allList=SelectShopService.getSortBySales().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				shopList.add(allList.next());
				if(shopList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return shopList;
	}
	/**
	 * 获取好评度排序的相应页面的店家
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public static List<Shop> getShopListByReputed(int startIndex,int pageSize){
		int startIndexCompare=0;
		List<Shop> shopList=new ArrayList<Shop>();
		Iterator<Shop> allList=SelectShopService.getSortByReputed().iterator();
		while(allList.hasNext()) {
			startIndexCompare++;
			if(startIndexCompare>=startIndex) {
				shopList.add(allList.next());
				if(shopList.size()==pageSize) {
					break;
				}
			}else {
				allList.next();
			}
		}
		return shopList;
	}

}
