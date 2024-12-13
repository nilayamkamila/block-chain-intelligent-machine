package com.blockchains.tokens.data.utility;

import com.blockchains.stream.data.models.CryptoCoinUserToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.stream.IntStream;

@Component
public class AppUtility {

    @Autowired
    WebClient webClient;
    @Autowired
    BlockTokensGenerator blockTokensGenerator;

    private final Logger LOG = LoggerFactory.getLogger(AppUtility.class);

    public void executeProcess() {
        String employeeMono = webClient.get()
                .uri("/health")
                .retrieve()
                .bodyToMono(String.class).block();
        System.out.println(employeeMono);
        IntStream.range(1, 2).forEach( i-> {
            CryptoCoinUserToken cryptoCoinUserToken =
                    webClient.post()
                            .uri("/coiningestservices")
                            .body(BodyInserters.fromValue(blockTokensGenerator.getCryptoCoinUserToken()))
                            .retrieve()
                            .bodyToMono(CryptoCoinUserToken.class)
                            .timeout(Duration.ofSeconds(60))
                            .block();
            System.out.println(cryptoCoinUserToken);
        });
    }
}
