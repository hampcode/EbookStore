package com.example.m3.config;

import com.example.m3.converters.BookConverter;
import com.example.m3.converters.OrderConverter;
import com.example.m3.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConverterConfig {


    @Bean
    public BookConverter getBookConverter() {
        return new BookConverter();
    }

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }

    @Bean
    public OrderConverter getOrderConverter() {
        return new OrderConverter(getBookConverter(),getUserConverter());
    }


}