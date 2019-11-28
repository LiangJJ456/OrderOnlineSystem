package com.LiangZhenJi.www.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.LiangZhenJi.www.dao.ShopDao;
import com.LiangZhenJi.www.po.Shop;

/**
 * 筛选器
 * 根据用户筛选店家的条件返回合适的店家
 * 筛选条件可以是店家的销量和好评度
 * @author l
 *
 */

public class SelectShopService {
	/**
	 * 根据销量对店家由高到低排序
	 * @return
	 */
	public static List<Shop> getSortBySales()  {
		ShopDao shopDao=new ShopDao();
		List<Shop> salesList=shopDao.findAll();//获得全部店家列单
		/**
		 * 排序部分
		 */
		Collections.sort(salesList, new Comparator<Shop>() {  
			  
            @Override  
            public int compare(Shop shop1, Shop shop2) {  
                int i = shop2.getSales() - shop1.getSales();  //由高到低排序
                if(i == 0){  
                    	 return shop2.getWellReputed()-shop1.getWellReputed();//如果销量相同再根据好评度由高到低排序
                     }  
                return i;  
            }  
        });
		return salesList;
	}
	
	/**
	 * 根据好评度对店家由低到高排序
	 * @return
	 */
	public static List<Shop> getSortByReputed()  {
		ShopDao shopDao=new ShopDao();
		List<Shop> reputedList=shopDao.findAll();//获得全部商品列单
		/**
		 * 排序部分
		 */
		Collections.sort(reputedList, new Comparator<Shop>() {  
			  
            @Override  
            public int compare(Shop shop1, Shop shop2) {  
                int i = shop2.getWellReputed() - shop1.getWellReputed();  //由高到低排序
                if(i == 0){  
                    	 return shop2.getSales()-shop1.getSales();//如果好评度相同再根据销量由高到低排序
                }  
                return i;  
            }  
        });
		return reputedList;
	}
}
