package com.acme.employeehub.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private final RestClientLoggingInterceptor restClientLoggingInterceptor;

    public RestClientConfig(RestClientLoggingInterceptor restClientLoggingInterceptor) {
        this.restClientLoggingInterceptor = restClientLoggingInterceptor;
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder()
                .requestInterceptor(restClientLoggingInterceptor);
    }
}