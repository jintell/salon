package com.jade.app.salon.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // https://spring.io/guides/gs/rest-service-cors/
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "OPTION", "PUT", "DELETE")
                .allowedHeaders("*"); // We can externalize this for easy config
    }
}
