package com.blockchains.stream.processor.client;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;


public class S3ClientObject {
    public S3Client getS3Client() {
        S3Client s3Client = S3Client
                .builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        return s3Client;
    }
}
