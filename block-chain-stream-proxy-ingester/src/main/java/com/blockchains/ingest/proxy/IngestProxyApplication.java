package com.blockchains.ingest.proxy;

import com.blockchains.ingest.proxy.exceptions.ApiError;
import com.blockchains.ingest.proxy.exceptions.ProxyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class IngestProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestProxyApplication.class, args);
	}
}
