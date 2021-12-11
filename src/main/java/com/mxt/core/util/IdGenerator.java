package com.mxt.core.util;

public class IdGenerator {

    private static int idIncreate = 0;
    private static String serverid = "000";
    
    /**
     * id生成器
     * @return id
     */
    public static String createId() {
    	StringBuffer sbID =  new StringBuffer(DateUtils.getLongTime());
    	sbID.append(serverid);
        idIncreate = idIncreate >= 9999 ? 0 : idIncreate + 1;
        sbID.append(String.format("%04d",idIncreate));
        return sbID.toString();
    }
}
