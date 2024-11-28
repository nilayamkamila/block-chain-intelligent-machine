package com.blockchains.stream.processor.core;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.stream.processor.LambdaResponse;
import com.blockchains.stream.processor.util.JsonUtils;
import com.blockchains.stream.processor.util.S3ClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.arrayToCommaDelimitedString;

@Component
public class LambdaKinesisEventExecutor implements LambdaExecutor<KinesisEvent, Context, LambdaResponse>{
    LambdaLogger logger = null;

    @Autowired
    ApplicationContext appContext;

    @Autowired
    JsonUtils jsonUtils;

    @Autowired
    S3ClientUtils s3ClientUtils;



    @Override
    public LambdaResponse handleExecution(KinesisEvent event, Context context){

            logger = context.getLogger();
            Environment environment = ((ConfigurableApplicationContext)appContext).getEnvironment();
            logger.log("Active Profile(s): " + arrayToCommaDelimitedString(environment.getActiveProfiles()));
            logger.log("Input: {}" + jsonUtils.toJson(event));
            logger.log("AppContext: {}" + appContext);
            logger.log("Event: {}" + jsonUtils.toJson(event));
            logger.log("Context: {}" +  jsonUtils.toJson(context));
            LambdaResponse lambdaResponse = new LambdaResponse();
        lambdaResponse.setApproximateArrivalTimestamp(Instant.now().toString());
            List<CryptoCoinUserToken> listCryptoCoinUserToken = new ArrayList<>();
            for (KinesisEvent.KinesisEventRecord record : event.getRecords()) {
                try {
                    String data = new String(record.getKinesis().getData().array());
                    CryptoCoinUserToken cryptoCoinUserToken= new ObjectMapper().readValue(data, CryptoCoinUserToken.class);
                    listCryptoCoinUserToken.add(cryptoCoinUserToken);
                    BeanUtils.copyProperties(lambdaResponse, record);
                    lambdaResponse.setStatus("SUCCESS");
                    lambdaResponse.setData(cryptoCoinUserToken);
                    logger.log(lambdaResponse.getStatus());
                }
                catch (JsonProcessingException ex) {
                    logger.log("JsonProcessingException: Crypto Currency Id: ");
                    logger.log("An error occurred: "+ ex.getMessage());
                    lambdaResponse.setData(ex.getMessage());
                    lambdaResponse.setStatus("FAILED");
                }
                catch (Exception ex) {
                    logger.log("Exception: Crypto Currency Id: ");
                    logger.log("An error occurred: "+ ex.getMessage());
                    lambdaResponse.setData(ex.getMessage());
                    lambdaResponse.setStatus("FAILED");
                }
            }
            logger.log(jsonUtils.toJson(listCryptoCoinUserToken));
            s3ClientUtils.uploadObjects(listCryptoCoinUserToken);
            logger.log("Successfully processed:" + event.getRecords().size() +" records ");
            return lambdaResponse;
    }
}
