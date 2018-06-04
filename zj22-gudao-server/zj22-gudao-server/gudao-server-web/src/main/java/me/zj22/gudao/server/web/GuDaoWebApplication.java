package me.zj22.gudao.server.web;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Zhangchengwei
 * @since 2018-01-10
 */
@MapperScan(basePackages = {"me.zj22.gudao.server.web.dao.db"})
@ComponentScan({"me.zj22.gudao.server"})
@ServletComponentScan// 允许servlet
@EnableTransactionManagement
@SpringBootApplication
public class GuDaoWebApplication {// extends SpringBootServletInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(GuDaoWebApplication.class);

    static {
        // 默认编码设置
        System.getProperties().put("file.encoding", "UTF-8");
        // 初始化日志相关参数
        String userDir = System.getProperty("user.dir");
        String logFileNamePrefix = userDir.replaceAll("[^0-9a-zA-Z_]+", "_");
        System.setProperty("logFileNamePrefix", logFileNamePrefix);

        // 设置时区
        System.setProperty("user.timezone", "Asia/Shanghai");
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(GuDaoWebApplication.class);
//    }

    public static void main(String[] args) {
        SpringApplication.run(GuDaoWebApplication.class, args);
        LOG.info("gudao-server-web服务启动成功!!!!");
    }
}
