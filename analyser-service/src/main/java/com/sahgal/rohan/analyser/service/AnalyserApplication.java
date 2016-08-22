package com.sahgal.rohan.analyser.service;

import com.sahgal.rohan.io.IOConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.sahgal.rohan")
@Import({
        IOConfiguration.class
})
public class AnalyserApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnalyserApplication.class, args);
    }
}
