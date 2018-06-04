package me.zj22.gudao.server.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @Program:zj22-gudao-server
 * @Description:
 * @Author Gqjian
 * @Create 2018/2/13 21:17:32
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{

    public void addInterceptors(InterceptorRegistry registry){

        //registry.addInterceptor();
    }
}
