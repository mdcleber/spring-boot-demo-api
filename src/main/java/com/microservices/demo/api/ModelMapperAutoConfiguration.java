package com.microservices.demo.api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ModelMapper.class)
public class ModelMapperAutoConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}