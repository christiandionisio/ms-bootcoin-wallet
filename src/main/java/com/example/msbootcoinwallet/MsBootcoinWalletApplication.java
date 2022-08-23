package com.example.msbootcoinwallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsBootcoinWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBootcoinWalletApplication.class, args);
    }

}
