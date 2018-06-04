package me.zj22.gudao.server.web.listener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.zj22.gudao.server.web.utils.AccessTokenUtil;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by fengzheng on 2018/5/1.
 * 设置定时任务
 */
@Component
public class JobForWXAccessTokenListener implements ApplicationListener<ContextRefreshedEvent> {

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            Runnable runnable = new Runnable() {
                public void run() {
                    //定时设置accessToken
                    AccessTokenUtil.initAndSetAccessToken();
                }
            };
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runnable, 1, 7000, TimeUnit.SECONDS);
        }
    }
}