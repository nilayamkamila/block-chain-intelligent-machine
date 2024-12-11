package com.blockchains.ingest.proxy.exceptions;

public class ProxyAsyncRuntimeException extends RuntimeException{
    public ProxyAsyncRuntimeException(String errorMessage) {
        super(errorMessage);
    }
}
