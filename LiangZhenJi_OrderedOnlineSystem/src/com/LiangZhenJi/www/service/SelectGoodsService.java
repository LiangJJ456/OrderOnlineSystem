package com.LiangZhenJi.www.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.LiangZhenJi.www.dao.GoodsDao;
import com.LiangZhenJi.www.po.Goods;

/**
 * 筛选器
 * 根据用户筛选商品的条件返回合适的商品
 * 筛选条件可以是商品的价格，销量和好评度
 * @author l
 *
 */

public class SelectGoodsService {
	/**
	 * 根据价格对商品由低到高排序
	 * @return
	 */
	public static List<Goods> getSortByPrice()  {
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> priceList=goodsDao.getAll();//获得全部商品列单
		/**
		 * 排序部分
		 */
		Collections.sort(priceList, new Comparator<Goods>() {  
			  
            @Override  
            public int compare(Goods goods1, Goods goods2) {  
                int i = goods1.getPrice() - goods2.getPrice();  //由低到高排序
                if(i == 0){  
                     int j=goods2.getSales() - goods1.getSales();//如果价格相同再根据销量由高到低排序
                     if(j==0) {
                    	 return goods2.getWellReputed()-goods1.getWellReputed();//如果销量相同再根据好评度由高到低排序
                     }
                     return j;
                }  
                return i;  
            }  
        });
		return priceList;
	}
	/**
	 * 根据销量对商品由高到低排序
	 * @return
	 */
	public static List<Goods> getSortBySales()  {
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> salesList=goodsDao.getAll();//获得全部商品列单
		/**
		 * 排序部分
		 */
		Collections.sort(salesList, new Comparator<Goods>() {  
			  
            @Override  
            public int compare(Goods goods1, Goods goods2) {  
                int i = goods2.getSales() - goods1.getSales();  //由高到低排序
                if(i == 0){  
                     int j=goods1.getPrice() - goods2.getPrice();//如果销量相同再根据价格由低到高排序
                     if(j==0) {
                    	 return goods2.getWellReputed()-goods1.getWellReputed();//如果价格相同再根据好评度由高到低排序
                     }
                     return j;
                }  
                return i;  
            }  
        });
		return salesList;
	}
	
	/**
	 * 根据好评度对商品由低到高排序
	 * @return
	 */
	public static List<Goods> getSortByReputed()  {
		GoodsDao goodsDao=new GoodsDao();
		List<Goods> reputedList=goodsDao.getAll();//获得全部商品列单
		/**
		 * 排序部分
		 */
		Collections.sort(reputedList, new Comparator<Goods>() {  
			  
            @Override  
            public int compare(Goods goods1, Goods goods2) {  
                int i = goods2.getWellReputed() - goods1.getWellReputed();  //由高到低排序
                if(i == 0){  
                     int j=goods1.getPrice() - goods2.getPrice();//如果好评度相同再根据价格由低到高排序
                     if(j==0) {
                    	 return goods2.getSales()-goods1.getSales();//如果价格相同再根据销量由高到低排序
                     }
                     return j;
                }  
                return i;  
            }  
        });
		return reputedList;
	}
}
