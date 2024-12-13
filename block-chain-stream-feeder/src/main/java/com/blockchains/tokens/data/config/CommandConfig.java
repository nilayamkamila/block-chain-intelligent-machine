package com.blockchains.tokens.data.config;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandConfig {
    @Bean
    public AmazonKinesis amazonKinesis() {
        ProfileCredentialsProvider awsCredentials = new ProfileCredentialsProvider();
        return AmazonKinesisClientBuilder.standard()
                .withCredentials(new AWSCredentialsProviderChain(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
