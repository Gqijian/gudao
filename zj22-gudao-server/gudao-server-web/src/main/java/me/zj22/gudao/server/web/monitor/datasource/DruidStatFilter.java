package me.zj22.gudao.server.web.monitor.datasource;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Druid拦截器，用于查看Druid监控
 * @author Zhangchengwei
 * @since 2017/12/19
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/monitoring/druid/*")//忽略资源
        })
public class DruidStatFilter extends WebStatFilter {

}
