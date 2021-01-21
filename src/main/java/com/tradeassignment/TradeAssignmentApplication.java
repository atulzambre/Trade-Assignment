package com.tradeassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring boot application bootstrap main class.
 * @author atulzambre
 * @version 1.0
 * @since 2021-01-21
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class TradeAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeAssignmentApplication.class, args);
    }

}
