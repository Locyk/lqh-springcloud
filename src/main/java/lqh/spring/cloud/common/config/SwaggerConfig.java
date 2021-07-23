package lqh.spring.cloud.common.config;


import lqh.spring.cloud.common.entity.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: Locyk
 * @time: 2021/7/12 0012 下午 3:47
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig{
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.macro.mall.controller")
                .title("mall后台系统")
                .description("mall后台相关接口文档")
                .contactName("macro")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
