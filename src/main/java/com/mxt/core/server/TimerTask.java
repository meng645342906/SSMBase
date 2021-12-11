package com.mxt.core.server;

import com.mxt.core.util.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
    /**
     * 执行gc操作
     */
    @Scheduled(cron = "10 0/30 * ? * *")
    public void doGC() {
        System.err.println(DateUtils.getNowDate() + "：" + "调用gc()方法。");
        System.gc();
    }
}