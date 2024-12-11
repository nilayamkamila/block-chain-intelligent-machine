package com.blockchains.ingest.proxy.config;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    @Bean
    public AmazonKinesis amazonKinesis() {
        ProfileCredentialsProvider awsCredentials = new ProfileCredentialsProvider();
        return AmazonKinesisClientBuilder.standard()
                .withCredentials(new AWSCredentialsProviderChain(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

}
