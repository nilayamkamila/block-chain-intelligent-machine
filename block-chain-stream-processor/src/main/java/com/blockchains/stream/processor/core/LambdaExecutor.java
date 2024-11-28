package com.blockchains.stream.processor.core;

public interface LambdaExecutor<KinesisEvent, Context, LambdaResponse> {
    public LambdaResponse handleExecution(KinesisEvent inputEvent, Context context);
}
