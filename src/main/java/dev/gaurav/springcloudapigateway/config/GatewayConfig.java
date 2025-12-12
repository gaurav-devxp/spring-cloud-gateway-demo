package dev.gaurav.springcloudapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    // Inject the URL from application.yml
    @Value("${app.rest-mvc-service.url}")
    private String restMvcServiceUrl;

    @Value("${app.webflux-service.url}")
    private String webfluxServiceUrl;

    @Value("${app.webflux-fn-service.url}")
    private String webfluxFnServiceUrl;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("spring-rest-mvc-demo", r -> r.path("/api/v1/**")
                        .uri(restMvcServiceUrl))
                .route("spring-webflux-demo", r -> r.path("/api/v2/**")
                        .uri(webfluxServiceUrl))
                .route("spring-webflux-fn-demo", r -> r.path("/api/v3/**")
                        .uri(webfluxFnServiceUrl))
                .build();
    }
}
