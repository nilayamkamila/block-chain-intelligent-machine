package com.blockchains.ingest.proxy.services;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.ingest.proxy.exceptions.ProxyAsyncRuntimeException;
import com.blockchains.ingest.proxy.exceptions.ProxyException;
import com.blockchains.ingest.proxy.utils.ProxyConstants;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AyncKinesisService implements IAsyncService{
    private static final Logger LOG = LoggerFactory.getLogger(AyncKinesisService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmazonKinesis amazonKinesis;

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> process(final CryptoCoinUserToken cryptoCoinUserToken) throws InterruptedException {
        LOG.info("Processing CryptoCoinUserToken for the Currency Id: {} ", cryptoCoinUserToken.getCryptoCurrency().getCurrencyId());
        Thread.sleep(2000);
        PutRecordsRequestEntry entry = new PutRecordsRequestEntry();
        try {
            String objectValue = objectMapper
                    .writeValueAsString(cryptoCoinUserToken);
            entry.setData(ByteBuffer.wrap(
                    (objectValue).getBytes()));
            entry.setPartitionKey("10001");
        }catch(JsonProcessingException jpe){
            LOG.info(this.getClass().getName() + ":" + jpe.getMessage());
            throw new ProxyException(jpe.getMessage());
        }
        Optional.ofNullable(entry).orElseThrow(()->new ProxyAsyncRuntimeException(ProxyConstants.APPLICATION_ASYNC_EXCEPTION));
        PutRecordsRequest createRecordsRequest = new PutRecordsRequest();
        createRecordsRequest.setStreamName("blockchain-kinesis-data-stream");
        createRecordsRequest.setRecords(Collections.singletonList(entry));
        amazonKinesis.putRecords(createRecordsRequest);
        LOG.info("Completed CryptoCoinUserToken Process for the Currency Id: {} ", cryptoCoinUserToken.getCryptoCurrency().getCurrencyId());
        return CompletableFuture.completedFuture("Processing CryptoCoinUserToken Completed");
    }
}
