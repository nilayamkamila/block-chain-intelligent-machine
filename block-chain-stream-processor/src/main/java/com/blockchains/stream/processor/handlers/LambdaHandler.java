package com.blockchains.stream.processor.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.logging.LogLevel;

public class LambdaHandler implements RequestHandler<Request, Response> {

    public LambdaHandler(){}
    @Override
    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Processing Stream Feeder from " + request.name(), LogLevel.INFO);
        logger.log("Processing Stream Feeder from " + request.source(), LogLevel.INFO);
        logger.log("Processing Stream Feeder from " + request.data(), LogLevel.INFO);
        return new Response("Response from Processed Stream Feeder Data: " + request.data());
    }
}
record Request(String name, String source, String data) {}
record Response(String processedData) {}

