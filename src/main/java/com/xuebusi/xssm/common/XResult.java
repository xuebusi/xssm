package com.xuebusi.xssm.common;

import java.util.List;

/**
 * Created by 学布斯 on 2017/12/18.
 */
public class XResult {

    /**
     * 总条数
     */
    private long total;
    /**
     * 分页数据
     */
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "XResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
