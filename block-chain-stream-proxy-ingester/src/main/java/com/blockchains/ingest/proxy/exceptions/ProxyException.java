package com.blockchains.ingest.proxy.exceptions;

public class ProxyException extends RuntimeException{
    public ProxyException(String errorMessage) {
        super(errorMessage);
    }
}
