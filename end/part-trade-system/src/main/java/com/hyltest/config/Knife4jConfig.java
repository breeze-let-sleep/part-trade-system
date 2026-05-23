package com.hyltest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j 配置类
 */
@Configuration
public class Knife4jConfig {

    /**
     * 配置 OpenAPI 文档基础信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("零件交易系统")
                        .description("使用spring boot3+vue3搭建的单体项目")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("迷迷糊糊睡着了")
                                .email("test@demo.com"))
                                //其他信息可参考启动时接口文档页面-主页信息
                )
                .components(new Components()
                                    .addSecuritySchemes("Authorization",
                                                        new SecurityScheme()
                                                                .type(SecurityScheme.Type.HTTP)
                                                                .scheme("bearer")
                                    )
                );
                //添加全局认证
                /*
                虽然添加了全局认证，但是当打开接口文档时，进入接口调试页面，仍然会出现无法设置自定义请求头参数的情况？
                原因：knife4j是在swagger上做了二次封装，如果使用swagger就可以正确设置，但是knife4j就不行，
                不知是bug还是是有意为之，使控制更加自主
                解决方案：在需要设置请求体参数的接口类上添加@SecurityRequirement(name = "Authorization"),
                即可实现自定义请求头参数的设置
                 */
//                .addSecurityItem(new SecurityRequirement().addList("Authorization"));
    }
}