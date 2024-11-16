package com.blockchains.tokens.data;

import com.blockchains.tokens.data.interfaces.BlockChainData;
import com.blockchains.tokens.data.interfaces.BlockChainDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    BlockChainDataRepository blockChainDataRepository;

    @Override
    public void run(String... args) {
        blockChainDataRepository.save(
                new BlockChainData("Reserved Blockchain Data A",
                        BigDecimal.valueOf(9.99),
                        new Date())
        );
        System.out.println("Reserved Blockchain Database initialized!");
    }
}
