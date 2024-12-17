package com.blockchains.tokens.data;

import com.blockchains.tokens.data.utility.AppUtility;
import com.blockchains.tokens.data.utility.CommandUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class StartApplication implements CommandLineRunner{

    @Autowired
    CommandUtility commandUtility;

    @Autowired
    AppUtility appUtility;



    private final Logger LOG = LoggerFactory.getLogger(StartApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args).close();
    }



    @Override
    public void run(String... args) {
        LOG.info("EXECUTING : command line runner");
        commandUtility.executeProcess(2000);
        appUtility.executeProcess();
        LOG.info("COMPLETED : command line runner");
    }








}