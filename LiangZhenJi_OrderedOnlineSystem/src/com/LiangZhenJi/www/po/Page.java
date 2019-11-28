package com.LiangZhenJi.www.po;


import java.util.List;
/**
 * 页面类，用以分页功能
 * @author l
 *
 * @param <T>
 */
public class Page<T> {
    private int pageNum;    //当前页
    private int pageSize;    //每页显示的商品个数
    private int totalRecord;    //总的记录商品个数
    private int totalPage;    //总页数
    private int startIndex;    //开始索引     
    private List<T> list;       //放置商品的list
    private int start;
    private int end;
  
    public Page(int pageNum,int pageSize,int totalRecord) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;
        if(totalRecord%pageSize==0){
            this.totalPage = totalRecord / pageSize;
        }else{
            this.totalPage = totalRecord / pageSize +1;
        }
        //开始索引
        this.startIndex = (pageNum-1)*pageSize+1;
        this.start = 1;
        this.end = 5;      //显示5页，这里可以设置
        if(totalPage <=5){
            this.end = this.totalPage;
        }else {
            this.start = pageNum - 2;
            this.end = pageNum + 2;
            
            if(start <=0){
                this.start = 1;
                this.end = 5;
            }
            if(end > this.totalPage){
                this.end = totalPage;
                this.start = end - 5;
            }
        }
    }
    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
    

}
