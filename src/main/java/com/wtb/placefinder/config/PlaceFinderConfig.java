package com.wtb.placefinder.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@ComponentScan(value = {"com.wtb.placefinder","com.wtb.placefinder.gateway", "com.wtb.placefinder.service"})
@EnableWebMvc
public class PlaceFinderConfig extends WebMvcAutoConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "restTemplate")
    public RestTemplate csRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
