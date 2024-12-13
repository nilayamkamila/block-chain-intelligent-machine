package com.blockchains.tokens.data.utility;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CommandUtility {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BlockTokensGenerator blockTokensGenerator;

    @Autowired
    AmazonKinesis amazonKinesis;

    public void executeProcess() {
        List<PutRecordsRequestEntry> entries = getPutRecordsRequestEntries(100);
        PutRecordsRequest createRecordsRequest = new PutRecordsRequest();
        createRecordsRequest.setStreamName("blockchain-kinesis-data-stream");
        createRecordsRequest.setRecords(entries);
        //amazonKinesis.putRecords(createRecordsRequest);
        System.out.println("startUp Initialized - 1=2! Sent Success");
    }

    private List<PutRecordsRequestEntry> getPutRecordsRequestEntries(int numberOfRecords) {
        List<PutRecordsRequestEntry> entries = IntStream.range(1, numberOfRecords).mapToObj(ipSuffix -> {
            PutRecordsRequestEntry entry = new PutRecordsRequestEntry();
            CryptoCoinUserToken cryptoCoinUserToken = blockTokensGenerator.getCryptoCoinUserToken();
            try {
                String objectValue = objectMapper
                        .writeValueAsString(cryptoCoinUserToken);
                System.out.println(objectValue);
                entry.setData(ByteBuffer.wrap(
                        (objectValue).getBytes()));
                entry.setPartitionKey("10001");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return entry;
        }).collect(Collectors.toList());
        return entries;
    }


}
