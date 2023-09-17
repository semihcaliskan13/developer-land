package com.depo.apigateway.util;

import com.depo.apigateway.interceptor.AuthenticationRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomFeignClient {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new AuthenticationRequestInterceptor();
    }
}
