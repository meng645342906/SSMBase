package com.dadao.bean;

import java.util.Date;

public class SysRole {
    private Integer id;

    private String name;

    private String des;

    private Integer seq;

    private Date createDate;

    private Integer status;
    
    private Long selected;
    
    
    public Long getSelected() {
        return selected;
    }
    
    public void setSelected(Long selected) {
        this.selected = selected;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", des=").append(des);
        sb.append(", seq=").append(seq);
        sb.append(", createDate=").append(createDate);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}