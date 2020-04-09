package com.dadao.core.util.page;

import java.util.List;

public class JqGridModel {
    /**
     * 总记录数
     */
    private long records;
    /**
     * 第几页
     */
    private int page;
    /**
     * 总页数
     */
    private int total;
    
    private List rows;
    
    public JqGridModel() {
    }
    
    public JqGridModel(long records, int page, int total, List rows) {
        this.records = records;
        this.page = page;
        this.total = total;
        this.rows = rows;
    }
    
    public long getRecords() {
        return records;
    }
    
    public void setRecords(long records) {
        this.records = records;
    }
    
    public int getPage() {
        return page;
    }
    
    public void setPage(int page) {
        this.page = page;
    }
    
    public int getTotal() {
        return total;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }
    
    public List getRows() {
        return rows;
    }
    
    public void setRows(List rows) {
        this.rows = rows;
    }
    
}
