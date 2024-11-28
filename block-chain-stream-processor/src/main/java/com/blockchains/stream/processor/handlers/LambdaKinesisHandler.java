
package com.blockchains.stream.processor.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class LambdaKinesisHandler implements RequestHandler<KinesisEvent, Response> {

    public LambdaKinesisHandler(){}
    @Override
    public Response handleRequest(final KinesisEvent event, final Context context) {
        LambdaLogger logger = context.getLogger();
        Response response = null;
        if (event.getRecords().isEmpty()) {
            logger.log("Empty Kinesis Event received");
            return null;
        }
        List<CryptoCoinUserToken> listCryptoCoinUserToken = new ArrayList<>();
        for (KinesisEvent.KinesisEventRecord record : event.getRecords()) {
            try {
                String data = new String(record.getKinesis().getData().array());
                CryptoCoinUserToken cryptoCoinUserToken= new ObjectMapper().readValue(data, CryptoCoinUserToken.class);
                listCryptoCoinUserToken.add(cryptoCoinUserToken);
                response = new Response("eventId: "
                        + record.getEventID()
                        + "\neventSource: "
                        + record.getEventSource()
                        + "\neventSourceARN: "
                        + record.getEventSourceARN()
                        + "\ninvokeIdentityArn: "
                        + record.getInvokeIdentityArn()
                        + "\nrecord.sequenceNumber: "
                        + record.getKinesis().getSequenceNumber()
                        + "\nrecord.partitionKey: "
                        + record.getKinesis().getPartitionKey()
                        + "\nrecord.approximateArrivalTimestamp: "
                        + record.getKinesis().getApproximateArrivalTimestamp()
                        + "\nsignature : "
                        + cryptoCoinUserToken.getSignature()
                        + "\nprocessedData :"
                        + data);
                logger.log(response.processedData());
            }
            catch (JsonProcessingException ex) {
                logger.log("JsonProcessingException: Crypto Currency Id: ");
                logger.log("An error occurred: "+ ex.getMessage());
                response = new Response(ex.getMessage());
            }
            catch (Exception ex) {
                logger.log("Exception: Crypto Currency Id: ");
                logger.log("An error occurred: "+ ex.getMessage());
                response = new Response(ex.getMessage());
            }
        }


        return response;
    }
}

record Response(String processedData) {}

