package com.blockchains.tokens.data.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class BlockChainService {

    @Autowired
    private BlockChainDataRepository blockChainDataRepository;

    public BlockChainData save(String title, BigDecimal price, Date publishedDate) {

        BlockChainData blockChainData = new BlockChainData();
        blockChainData.setTitle(title);
        blockChainData.setPrice(price);
        blockChainData.setPublishDate(publishedDate);

        return blockChainDataRepository.save(blockChainData);

    }

    public List<BlockChainData> findAll() {
        save("All", new BigDecimal(10.01), new Date());
        return blockChainDataRepository.findAll();
    }

}
