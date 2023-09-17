package com.depo.apigateway.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)

public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;
    private final AuthorityFilter authorityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/api/v1/projects/**").hasRole("SA")
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/v1/users/**").hasRole("SA")
                        .requestMatchers(HttpMethod.GET,"/api/v1/users/username/**").authenticated()
                        .requestMatchers("/api/v1/requirements/**").hasAnyRole("SA", "KT")
                        .requestMatchers("/api/v1/roles/**").hasRole("SA")
                        .requestMatchers("/api/v1/authorities/**").hasRole("SA")
                        .requestMatchers(HttpMethod.GET, "/api/v1/codebases/{id}/**").hasAnyRole("SA", "KT", "DL")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/codebases/{id}/requirements/**").hasAnyRole("SA", "KT")
                        .requestMatchers(HttpMethod.POST, "/api/v1/codebases/{id}/requirements/**").hasAnyRole("SA", "KT")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/codebases/{id}/requirements/**").hasAnyRole("SA", "KT")
                        .requestMatchers( "/api/v1/codebases/{id}/authorities/**").hasAnyRole("SA")
                        .requestMatchers("/api/v1/codebases/**").hasRole("SA"))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(authorityFilter, JwtFilter.class);

        return http.build();
    }
}
