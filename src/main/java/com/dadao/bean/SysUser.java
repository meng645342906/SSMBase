package com.dadao.bean;

import java.util.Date;

public class SysUser {
    private Integer id;
    
    private String name;
    
    private String pwd;
    
    private String realName;
    
    private String des;
    
    private Integer status;
    
    private String icon;
    
    private String email;
    
    private Date createDate;
    
    private String phone;
    
    private String token;
    
    private String roleNames;
    
    public SysUser() {
    }
    
    public SysUser(Integer id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }
    
    public String getRoleNames() {
        return roleNames;
    }
    
    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
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
    
    public String getPwd() {
        return pwd;
    }
    
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
    
    public String getDes() {
        return des;
    }
    
    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
    
    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pwd=").append(pwd);
        sb.append(", realName=").append(realName);
        sb.append(", des=").append(des);
        sb.append(", status=").append(status);
        sb.append(", icon=").append(icon);
        sb.append(", email=").append(email);
        sb.append(", createDate=").append(createDate);
        sb.append(", phone=").append(phone);
        sb.append(", token=").append(token);
        sb.append("]");
        return sb.toString();
    }
}