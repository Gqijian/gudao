package me.zj22.gudao.server.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据库相关配置
 * @author Zhangchengwei
 * @since 2018-02-04
 */
@Configuration
public class DataSourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        LOG.debug("注入druid！！！");
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(env.getProperty("datasource.url"));
        datasource.setUsername(env.getProperty("datasource.username"));
        datasource.setPassword(env.getProperty("datasource.password"));
        datasource.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        datasource.setInitialSize(Integer.valueOf(env.getProperty("datasource.druid.initial-size")));
        datasource.setMinIdle(Integer.valueOf(env.getProperty("datasource.druid.min-idle")));
        datasource.setMaxWait(Long.valueOf(env.getProperty("datasource.druid.max-wait")));
        datasource.setMaxActive(Integer.valueOf(env.getProperty("datasource.druid.max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(env.getProperty("datasource.druid.min-evictable-idle-time-millis")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            LOG.error("数据库连接初始化异常.", e);
        }
        return datasource;
    }

}
