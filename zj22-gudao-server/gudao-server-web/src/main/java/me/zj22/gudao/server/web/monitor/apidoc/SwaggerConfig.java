package me.zj22.gudao.server.web.monitor.apidoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * SwaggerConfig
 * @author Zhangchengwei
 * @since 2017/12/19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.zj22.gudao.server"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("张成伟",
                "http://blog.zj22.me",
                "981134442@qq.com");
        return new ApiInfo(
                "咕叨酱服务接口文档中心",  // 标题
                "咕叨酱服务接口文档中心, 可以查看服务所有对外接口并测试",  // 描述
                "1.0-SNAPSHOT",
                "http://www.apache.org/licenses/LICENSE-2.0.html",
                contact,
                "Apache License2",
                "http://www.apache.org/licenses/LICENSE-2.0.html",
                new ArrayList<>()
        );
    }

}
