package com.uktech.kladovka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uktech.*"})
@EntityScan(basePackages = {"com.uktech.*"})
@EnableJpaRepositories(basePackages = {"com.uktech.*"})
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name="scheduling.enable", matchIfMissing = true)
class SchedulingConfig {

}
