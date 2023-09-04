package com.lecture.reservation.common.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by HoYoung on 2023/08/31.
 */
@Configuration
public class SwggerConfig {

    @Bean
    public OpenApiCustomizer springShopOpenAPI() {
        return openApi -> {

//            openApi.components(new Components().addSchemas("ApiResponse", new Schema()));
        };
    }

}
