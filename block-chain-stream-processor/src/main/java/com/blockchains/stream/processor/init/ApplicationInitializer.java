package com.blockchains.stream.processor.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class ApplicationInitializer {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public S3Client s3Client() {
        S3Client s3Client = S3Client
                .builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        return s3Client;
    }

}
