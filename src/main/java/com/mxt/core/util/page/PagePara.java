package com.mxt.core.util.page;

public class PagePara {
    /**
     * 页数
     */
    private Integer page;
    /**
     * 每页数量
     */
    private Integer rows;
    
    /**
     * 所需排序字段
     */
    private String sidx;
    /**
     * 排序方式
     */
    private String sord;
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
    }
    
    public Integer getRows() {
        return rows;
    }
    
    public void setRows(Integer rows) {
        this.rows = rows;
    }
    
    public String getSidx() {
        return sidx;
    }
    
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }
    
    public String getSord() {
        return sord;
    }
    
    public void setSord(String sord) {
        this.sord = sord;
    }
}
