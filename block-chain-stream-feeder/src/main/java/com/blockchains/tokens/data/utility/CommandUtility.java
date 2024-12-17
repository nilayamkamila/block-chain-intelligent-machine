package com.blockchains.tokens.data.utility;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static com.blockchains.tokens.data.utility.AppConstants.KINESIS_MAX_API_RATE_LIMIT;

@Component
public class CommandUtility {

    private final Logger LOG = LoggerFactory.getLogger(CommandUtility.class);

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    BlockTokensGenerator blockTokensGenerator;

    @Autowired
    AmazonKinesis amazonKinesis;

    public void executeProcess(Integer numberOfRecords) {
        IntStream.range(0,  ((Double)Math.ceil(
                ((double)numberOfRecords/KINESIS_MAX_API_RATE_LIMIT))).intValue()
                ).forEach(i->{
        Integer remainingRecords = numberOfRecords - KINESIS_MAX_API_RATE_LIMIT * i;
        System.out.println("Record Process Initiated for Record #: " + (i+1));
        Optional.of(remainingRecords).filter(recordSize -> recordSize > KINESIS_MAX_API_RATE_LIMIT).ifPresent((value)->{processKinesisRecords(KINESIS_MAX_API_RATE_LIMIT);});
        Optional.of(remainingRecords).filter(recordSize -> !(recordSize > KINESIS_MAX_API_RATE_LIMIT)).ifPresent((value)->{processKinesisRecords(value);});
        System.out.println("Record Process Sent for Record #:" + (i+1) + " is Successful");
        try {
            Thread.sleep(30000);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        });
    }

    private void processKinesisRecords(int recordsSize) {
        System.out.println("Target Record Size to Process: " + recordsSize);
        List<PutRecordsRequestEntry> entries = getPutRecordsRequestEntries(recordsSize);
        PutRecordsRequest createRecordsRequest = new PutRecordsRequest();
        createRecordsRequest.setStreamName("blockchain-kinesis-data-stream");
        createRecordsRequest.setRecords(entries);
        amazonKinesis.putRecords(createRecordsRequest);
    }

    private List<PutRecordsRequestEntry> getPutRecordsRequestEntries(int numberOfRecords) {

        List<PutRecordsRequestEntry> entries = IntStream.range(1, numberOfRecords).mapToObj(ipSuffix -> {
            PutRecordsRequestEntry entry = new PutRecordsRequestEntry();
            CryptoCoinUserToken cryptoCoinUserToken = blockTokensGenerator.getCryptoCoinUserToken();
            try {
                String objectValue = objectMapper
                        .writeValueAsString(cryptoCoinUserToken);
                entry.setData(ByteBuffer.wrap(
                        (objectValue).getBytes()));
                entry.setPartitionKey("10001");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return entry;
        }).collect(Collectors.toList());
        return entries;
    }


}
