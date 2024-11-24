package com.blockchains.stream.processor.utils;

import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.stream.processor.client.S3ClientObject;
import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class S3ClientObjectUtility {

    private static S3ClientObject s3ClientObject = new S3ClientObject();
    private static final String bucketName = "blockchain-kinesis-data-stream";
    private static final String key = "blockchain-kinesis-data-stream-realtime.csv";
    /*public static void main(String[] args){
        boolean uploadedStstus = uploadObjects("Hello", "blockchain-kinesis");
        System.out.println(uploadedStstus);
    }*/
    public static boolean uploadObjects(List<CryptoCoinUserToken> listCryptoCoinUserTokens){
        try
        {
            String data = StringUtils.EMPTY;
            //listCryptoCoinUserTokens.stream().forEach(rowData-> data += rowData + "\n");
            for (CryptoCoinUserToken cryptoCoinUserToken:listCryptoCoinUserTokens) {
                data += cryptoCoinUserToken + "\n";
            }
            byte[] contentAsBytes = data.getBytes("UTF-8");
            ByteArrayInputStream contentsAsStream = new ByteArrayInputStream(contentAsBytes);
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            PutObjectResponse putObjectResponse =
                    s3ClientObject.getS3Client().putObject(putOb, RequestBody.fromBytes(contentAsBytes));
            return true;
        } catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
