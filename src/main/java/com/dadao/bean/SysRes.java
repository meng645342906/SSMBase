package com.dadao.bean;

public class SysRes {
    private Integer id;
    
    private Integer pid;
    
    private String name;
    
    private String des;
    
    private String url;
    
    private String iconCls;
    
    private Integer seq;
    
    private Integer type;
    
    private String modifydate;
    
    private Integer enabled;
    
    private Integer level;
    
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
    
    public Integer getPid() {
        return pid;
    }
    
    public void setPid(Integer pid) {
        this.pid = pid;
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
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    public String getIconCls() {
        return iconCls;
    }
    
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }
    
    public Integer getSeq() {
        return seq;
    }
    
    public void setSeq(Integer seq) {
        this.seq = seq;
    }
    
    public Integer getType() {
        return type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }
    
    public String getModifydate() {
        return modifydate;
    }
    
    public void setModifydate(String modifydate) {
        this.modifydate = modifydate;
    }
    
    public Integer getEnabled() {
        return enabled;
    }
    
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", des=").append(des);
        sb.append(", url=").append(url);
        sb.append(", iconCls=").append(iconCls);
        sb.append(", seq=").append(seq);
        sb.append(", type=").append(type);
        sb.append(", modifydate=").append(modifydate);
        sb.append(", enabled=").append(enabled);
        sb.append(", level=").append(level);
        sb.append("]");
        return sb.toString();
    }
}