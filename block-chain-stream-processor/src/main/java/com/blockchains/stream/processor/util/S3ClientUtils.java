package com.blockchains.stream.processor.util;

import com.blockchains.stream.data.models.CryptoCoinUserToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

@Component
public class S3ClientUtils {

    @Autowired
    private S3Client s3Client;

    private static final String bucketName = "blockchain-kinesis-data-stream";
    private static final String key = "blockchain-kinesis-data-stream-realtime.csv";
    /*public static void main(String[] args){
        boolean uploadedStstus = uploadObjects("Hello", "blockchain-kinesis");
        System.out.println(uploadedStstus);
    }*/
    public boolean uploadObjects(List<CryptoCoinUserToken> listCryptoCoinUserTokens){
        try
        {
            String data = "currencyId,openingValue,closingValue,highestValue,lowestValue,adjClose,volumeStocks,userIdentity,userFollowers,userFriends,userVerified,userLocation,userCreditRating,certifiedAuthorityTrusted,tokenTrusted\n";
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
                    s3Client.putObject(putOb, RequestBody.fromBytes(contentAsBytes));
            return true;
        } catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
