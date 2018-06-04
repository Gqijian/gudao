package me.zj22.gudao.server.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 跨域的相关配置
 * @author Zhangchengwei
 * @since 2018/02/04
 */
@Configuration
public class CORSConfig {

    @Autowired
    private Environment env;

    @Bean
    public CorsFilter corsFilter() {
        // 处理接受跨域的请求地址列表
        String[] origins = env.getProperty("cors.access_control_allow_origin").split(",");
        List<String> allowedOrigins = new ArrayList<>(origins.length);
        for (String origin : origins) {
            allowedOrigins.add(origin.trim());
        }

        // 处理接受跨域的header
        String[] headers = env.getProperty("cors.access_control_allow_headers").split(",");
        List<String> allowedHeaders = new ArrayList<>(headers.length);
        for (String header : headers) {
            allowedHeaders.add(header.trim());
        }

        String[] methods = env.getProperty("cors.access-control-allow-methods").split(",");
        List<String> allowedMethods = new ArrayList<>(methods.length);
        for (String method : methods) {
            allowedMethods.add(method.trim());
        }

        boolean allowCredentials = Boolean.parseBoolean(env.getProperty("cors.access_control_allow_credentials"));

        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource
                = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(allowCredentials);
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setAllowedMethods(allowedMethods);
        urlBasedCorsConfigurationSource
                .registerCorsConfiguration(
                        env.getProperty("cors.allow_base_url"),
                        corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
