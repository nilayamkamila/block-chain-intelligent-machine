package com.blockchains.ingest.proxy.services;

import com.blockchains.stream.data.models.CryptoCoinUserToken;

import java.util.concurrent.CompletableFuture;

public interface IAsyncService {
    CompletableFuture<String> process(final CryptoCoinUserToken cryptoCoinUserToken) throws InterruptedException;
}
