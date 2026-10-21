package com.acme.employeehub.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    private final RestClientLoggingInterceptor restClientLoggingInterceptor;
    private final int connectTimeoutMs;
    private final int readTimeoutMs;

    public RestClientConfig(
            RestClientLoggingInterceptor restClientLoggingInterceptor,
            @Value("${employee.hub.service.connect-timeout-ms:3000}") int connectTimeoutMs,
            @Value("${employee.hub.service.read-timeout-ms:3000}") int readTimeoutMs) {
        this.restClientLoggingInterceptor = restClientLoggingInterceptor;
        this.connectTimeoutMs = connectTimeoutMs;
        this.readTimeoutMs = readTimeoutMs;
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeoutMs);
        requestFactory.setReadTimeout(readTimeoutMs);

        return RestClient.builder()
                .requestFactory(requestFactory)
                .requestInterceptor(restClientLoggingInterceptor);
    }
}