package com.wheel.gateway.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by rolyer on 17/11/16.
 */
@Configuration
@Import(Swagger2.class)
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    private static Logger logger = LoggerFactory.getLogger(WebMvcConfiguration.class);

    private final static String fileName = "front";
    private final static String frontDir = System.getProperty("user.dir") + "/../" + fileName + "/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/api-docs/**").addResourceLocations("classpath:/META-INF/openapi/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
