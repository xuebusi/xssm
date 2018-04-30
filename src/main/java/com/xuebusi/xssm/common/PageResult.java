package com.xuebusi.xssm.common;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public class PageResult<T> {

    /**
     * 分页数据
     */
    private T data;
    private int pageNum;
    private int pageSize;
    private long totalRecordCount;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(long totalRecordCount) {
        this.totalRecordCount = totalRecordCount;
    }
}
