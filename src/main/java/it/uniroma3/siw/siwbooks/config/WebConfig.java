package it.uniroma3.siw.siwbooks.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Configura Spring per servire le immagini dalla directory uploads
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:uploads/images/");
    }
}
