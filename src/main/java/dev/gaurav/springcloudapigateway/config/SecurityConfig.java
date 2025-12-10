package dev.gaurav.springcloudapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Disable CSRF for APIs
                .authorizeExchange(exchanges -> exchanges
                        // Allow unauthenticated access to specific paths (e.g., login, swagger)
                        .pathMatchers("/public/**", "/actuator/**").permitAll()
                        // All other paths require a valid Token
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {}) // Enable JWT validation using the config from application.yml
                );

        return http.build();
    }
}
