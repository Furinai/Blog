package cn.linter.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebResourceConfig extends WebMvcConfigurationSupport {

    @Value("${blog.upload.image.location}")
    private String location;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/image/**")
                .addResourceLocations("file:" + location);
    }
}
