package com.depo.apigateway.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    @Value("${header.key}")
    private String HEADER_KEY;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + HEADER_KEY);
    }
}
