package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/eureka**", "/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html",
                                        "/user-service/v3/api-docs","/user-service/swagger-ui**",
                                        "/product-service/v3/api-docs","/product-service/swagger-ui**",
                                        "/order-service/v3/api-docs","/order-service/swagger-ui**",
                                        "/inventory-service/v3/api-docs","/inventory-service/swagger-ui**").permitAll()
                                .pathMatchers(HttpMethod.POST, "/auth**").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer().jwt();
        return http.build();
    }

}
