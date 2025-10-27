package com.psi2.donaciones.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;                    // ← add
import org.springframework.context.annotation.Configuration; 
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Value("${storage.images-dir:file:/var/app/images/}")
    private String imagesDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations(imagesDir);  // e.g., "file:/var/app/images/"
    }

}
