package org.example.cuentaservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${service.banco.url}")
    private String urlserviceBanco;

    @Bean
    public WebClient bancoApi(WebClient.Builder builder){
        return builder.baseUrl(urlserviceBanco).build();
    }
}
