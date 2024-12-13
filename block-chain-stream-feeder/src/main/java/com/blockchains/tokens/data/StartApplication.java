package com.blockchains.tokens.data;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.tokens.data.utility.BlockTokensGenerator;
import com.blockchains.tokens.data.utility.CommandUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;


@SpringBootApplication
public class StartApplication implements CommandLineRunner{

    @Autowired
    CommandUtility commandUtility;

    @Autowired
    WebClient webClient;

    private final Logger LOG = LoggerFactory.getLogger(StartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args).close();
    }

    @Autowired
    BlockTokensGenerator blockTokensGenerator;

    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        commandUtility.executeProcess();
        String employeeMono = webClient.get()
                .uri("/health")
                .retrieve()
                .bodyToMono(String.class).block();
        System.out.println(employeeMono);
        CryptoCoinUserToken cryptoCoinUserToken =
                webClient.post()
                .uri("/coiningestservices")
                .body(BodyInserters.fromValue(blockTokensGenerator.getCryptoCoinUserToken()))
                .retrieve()
                .bodyToMono(CryptoCoinUserToken.class)
                        .timeout(Duration.ofSeconds(60))
                        .block();
        System.out.println(cryptoCoinUserToken);
    }








}