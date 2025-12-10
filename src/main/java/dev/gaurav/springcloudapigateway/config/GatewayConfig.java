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

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("spring-cloud-gateway-service-demo", r -> r.path("/api/v1/**")
                        .uri(restMvcServiceUrl))
                .build();
    }
}
