package com.dadao.bean;

public class SysRoleRes {
    private Integer id;

    private Integer resId;

    private Integer roleId;
    
    public SysRoleRes() {
    }
    
    public SysRoleRes( Integer resId, Integer roleId) {
        this.resId = resId;
        this.roleId = roleId;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", resId=").append(resId);
        sb.append(", roleId=").append(roleId);
        sb.append("]");
        return sb.toString();
    }
}