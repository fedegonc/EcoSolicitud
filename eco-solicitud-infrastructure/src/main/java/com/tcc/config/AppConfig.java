package com.tcc.config;

import com.tcc.application.RutaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public RutaService rutaService() {
        return new RutaService();
    }
}
