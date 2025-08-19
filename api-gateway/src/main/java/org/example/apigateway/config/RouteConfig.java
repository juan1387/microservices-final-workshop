package org.example.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
    @Value("${banco-service.url}")
    private String bancoUrl;
    @Value("${banco-service.id}")
    private String bancoId;
    @Value("${banco-service.path}")
    private String bancoPath;

    @Value("${cuentas-service.url}")
    private String cuentaUrl;
    @Value("${cuentas-service.id}")
    private String cuentaId;
    @Value("${cuentas-service.path}")
    private String cuentaPath;

    @Value("${transa-service.url}")
    private String transaUrl;
    @Value("${transa-service.id}")
    private String transaId;
    @Value("${transa-service.path}")
    private String transaPath;

    @Bean
    public RouteLocator createRouteLocator(RouteLocatorBuilder builder){
        System.out.println("id: "+bancoId+" "+ bancoUrl+bancoPath);
        System.out.println("id: "+cuentaId+" "+ cuentaUrl+cuentaPath);
        System.out.println("id: "+transaId+" "+ transaUrl+transaPath);
        return builder.routes()
                .route(bancoId, route -> route.path(bancoPath).uri(bancoUrl))
                .route(cuentaId, route -> route.path(cuentaPath).uri(cuentaUrl))
                .route(transaId, route -> route.path(transaPath).uri(transaUrl))
                /*.route("ms-cuentas", route -> route.path("api/cuentas/**").uri("http://locahost:8084"))
                .route("ms-transferencias", route -> route.path("api/transferencias/**").uri("http://locahost:8085"))
                .route("ms-transaccions", route -> route.path("api/transaccions/**").uri("http://locahost:8086"))*/
                .build();
    }
}
