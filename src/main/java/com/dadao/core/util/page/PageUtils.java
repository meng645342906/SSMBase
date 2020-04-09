package com.dadao.core.util.page;

import java.util.*;

public class PageUtils {
    
    /**
     * 拼接 SQL Where 语句
     *
     * @param conditions
     * @return
     */
    public static String getWhereSql(Set<Condition> conditions) {
        
        if (conditions != null && conditions.size() > 0) {
            StringBuilder bufWhere = new StringBuilder(" and  ");
            Iterator<Condition> ite = conditions.iterator();
            while (ite.hasNext()) {
                // 得到条件数据对象
                Condition cd = ite.next();
                // 拼接属性
                bufWhere.append(cd.getProperty());
                // 判断是否需要做为空特殊处理
                boolean isnull = cd.getValue() == null || cd.getValue().toString().trim().equals("");
                // 开始判断
                if (cd.getOper().equals(Operators.EQ)) {
                    if (isnull) {
                        bufWhere.append(" is  null ");
                    } else {
                        bufWhere.append("=");
                        bufWhere.append(getObject(cd.getValue()));
                    }
                    
                } else if (cd.getOper().equals(Operators.NOT_EQ)) {
                    if (isnull) {
                        bufWhere.append("  is not null ");
                    } else {
                        bufWhere.append("<>");
                        bufWhere.append(getObject(cd.getValue()));
                    }
                } else if (cd.getOper().equals(Operators.LE)) {
                    bufWhere.append("<=");
                    bufWhere.append(getObject(cd.getValue()));
                } else if (cd.getOper().equals(Operators.LT)) {
                    bufWhere.append("<");
                    bufWhere.append(getObject(cd.getValue()));
                } else if (cd.getOper().equals(Operators.GE)) {
                    bufWhere.append(">=");
                    bufWhere.append(getObject(cd.getValue()));
                } else if (cd.getOper().equals(Operators.GT)) {
                    bufWhere.append(">");
                    bufWhere.append(getObject(cd.getValue()));
                } else if (cd.getOper().equals(Operators.IN)) {
                    if (cd.getValue() instanceof List) {
                        StringBuilder ids = new StringBuilder();
                        for (Object item : (List) cd.getValue()) {
                            if (item instanceof String) {
                                ids.append("'").append(item).append("'").append(",");
                            } else {
                                ids.append(item).append(",");
                            }
                        }
                        if (ids.length() > 0) {
                            ids.deleteCharAt(ids.length() - 1);
                        }
                        bufWhere.append(" in (").append(ids.toString()).append(")");
                    } else {
                        bufWhere.append(" in (").append(getObject(cd.getValue())).append(")");
                    }
                    
                } else if (cd.getOper().equals(Operators.NOT_IN)) {
                    
                    if (cd.getValue() instanceof List) {
                        StringBuilder ids = new StringBuilder();
                        for (Object item : (List) cd.getValue()) {
                            if (item instanceof String) {
                                ids.append("'").append(item).append("'").append(",");
                            } else {
                                ids.append(item).append(",");
                            }
                        }
                        if (ids.length() > 0) {
                            ids.deleteCharAt(ids.length() - 1);
                        }
                        bufWhere.append(" not in (").append(ids.toString()).append(")");
                    } else {
                        bufWhere.append(" not in (").append(getObject(cd.getValue())).append(")");
                    }
                    
                } else if (cd.getOper().equals(Operators.LIKE)) {
                    if (cd.getValue() == null) {
                        bufWhere.append(" like '%%'");
                    } else {
                        bufWhere.append(" like '%").append(cd.getValue()).append("%'");
                    }
                    
                } else if (cd.getOper().equals(Operators.BETWEEN)) {
                    bufWhere.append("  between  ").append(getObject(cd.getFirstValue())).append("  and  ").append(getObject(cd.getValue()));
                }
                if (ite.hasNext()) {
                    bufWhere.append(" and  ");
                }
            }
            return bufWhere.toString();
        } else {
            return "";
        }
    }
    
    /**
     * 拼接 排序
     *
     * @param orderBys
     * @return
     */
    public static String getOrderBy(LinkedHashMap<String, String> orderBys) {
        if (orderBys == null || orderBys.size() < 1) {
            return "";
        }
        StringBuilder buf = new StringBuilder(" order by  ");
        Set<Map.Entry<String, String>> entrySet = orderBys.entrySet();
        Iterator<Map.Entry<String, String>> ite = entrySet.iterator();
        while (ite.hasNext()) {
            Map.Entry<String, String> en = ite.next();
            buf.append(en.getKey()).append(" ").append(en.getValue());
            if (ite.hasNext()) {
                buf.append(",");
            }
        }
        return buf.toString();
    }
    
    public static String getPage(PagePara page, Integer allCount) {
        int offset = page.getPage() * (page.getRows());
        if (offset > allCount) {
            offset =allCount % page.getRows();
        }
        return " limit " + (page.getPage() - 1) + ", " + offset;
    }
    
    private static Object getObject(Object value) {
        if (value instanceof Integer) {
            return value;
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else {
            return value;
        }
    }
    
}
