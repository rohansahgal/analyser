package com.sahgal.rohan.io;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rohansahgal on 8/22/16.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class IOConfiguration {

    @Bean
    public CloseableHttpClient createHttpClient() {
        return HttpClients.createDefault();
    }
}
