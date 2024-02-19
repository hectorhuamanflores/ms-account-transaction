package com.bootcamp.nttdata;

import feign.Contract;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
//@EnableFeignClients()
@EnableDiscoveryClient
public class MsaccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaccountApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public WebClient.Builder getWebClient(){
        return  WebClient.builder();
    }

}
