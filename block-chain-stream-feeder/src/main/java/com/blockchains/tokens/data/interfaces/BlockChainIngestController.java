package com.blockchains.tokens.data.interfaces;


import com.blockchains.stream.data.models.CreditData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.rmi.ServerException;
import java.util.List;

@RestController
public class BlockChainIngestController {

    @Autowired
    private BlockChainService blockChainService;

    @GetMapping("/books")
    public List<BlockChainData> findAll() {
        return blockChainService.findAll();
    }

    @PostMapping(path = "/creditdata",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditData> createUser(@RequestBody CreditData creditData, HttpServletRequest request) {

            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(creditData.getCreditCardType())
                    .toUri();
            return ResponseEntity.created(location).body(creditData);
    }
}

