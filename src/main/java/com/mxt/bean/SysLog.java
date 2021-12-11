package com.mxt.bean;

import java.util.Date;

public class SysLog {
    private Integer id;
    
    private Integer uid;
    
    private String from;
    
    private String ip;
    
    private String url;
    
    private Date date;
    
    private Integer errCode;
    
    private String className;
    
    private String methodName;
    
    private Date startTime;
    
    private Long spendTime;
    
    private String errMsg;
    
    private String params;
    
    public String getRealName() {
        return realName;
    }
    
    public void setRealName(String realName) {
        this.realName = realName;
    }
    
    private String realName;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUid() {
        return uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    
    public String getFrom() {
        return from;
    }
    
    public void setFrom(String from) {
        this.from = from == null ? null : from.trim();
    }
    
    public String getIp() {
        return ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public Integer getErrCode() {
        return errCode;
    }
    
    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }
    
    public String getMethodName() {
        return methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Long getSpendTime() {
        return spendTime;
    }
    
    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }
    
    public String getErrMsg() {
        return errMsg;
    }
    
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }
    
    public String getParams() {
        return params;
    }
    
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", from=").append(from);
        sb.append(", ip=").append(ip);
        sb.append(", url=").append(url);
        sb.append(", date=").append(date);
        sb.append(", errCode=").append(errCode);
        sb.append(", className=").append(className);
        sb.append(", methodName=").append(methodName);
        sb.append(", startTime=").append(startTime);
        sb.append(", spendTime=").append(spendTime);
        sb.append(", errMsg=").append(errMsg);
        sb.append(", params=").append(params);
        sb.append("]");
        return sb.toString();
    }
}