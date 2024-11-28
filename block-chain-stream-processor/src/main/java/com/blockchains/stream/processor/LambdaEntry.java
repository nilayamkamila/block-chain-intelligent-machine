package com.blockchains.stream.processor;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.KinesisEvent;
import com.blockchains.stream.processor.core.LambdaExecutor;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class LambdaEntry{

    private final ApplicationContext appContext;
    private final ConfigurableEnvironment environment;

    public LambdaEntry() {
        final SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class)
                .logStartupInfo(false);
        appContext = builder.build().run();
        environment = ((ConfigurableApplicationContext)appContext).getEnvironment();
    }

    public LambdaResponse handler(KinesisEvent event, Context context) {
        return (LambdaResponse)
                (appContext.getBean(LambdaExecutor.class))
                        .handleExecution(event, context);
    }
    ApplicationContext getContext() {
        return appContext;
    }
}
