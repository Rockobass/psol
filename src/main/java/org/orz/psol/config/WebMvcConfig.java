package org.orz.psol.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 配置静态资源
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/templates/**","/static/**","/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico","/doc.html","/webjars/bycdao-ui/**","/v2/api-docs","/swagger-resources/**","/webjars/**","/swagger-ui.html").addResourceLocations("classpath:/static/","classpath:/META-INF/resources/","classpath:/resources/","classpath:/public/","classpath:/static/pics/","classpath:/static/pics/products");
        super.addResourceHandlers(registry);
    }
}