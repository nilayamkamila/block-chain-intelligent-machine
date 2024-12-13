package com.blockchains.ingest.proxy;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.ingest.proxy.exceptions.ProxyException;
import com.blockchains.ingest.proxy.services.IAsyncService;
import com.blockchains.ingest.proxy.utils.IngresProxyUtility;
import com.blockchains.ingest.proxy.utils.ProxyConstants;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
public class IngestProxyController {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	IAsyncService asyncService;

	@Autowired
	private IngresProxyUtility ingresProxyUtility;

	private final Logger log = LoggerFactory.getLogger(IngestProxyController .class);

	private String getLocalDateTime()
	{
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
		  LocalDateTime now = LocalDateTime.now();
		  return dtf.format(now);
	}
	
	@RequestMapping(path = "/health", method = RequestMethod.GET)
	public String getNumberOfDogs() {
		System.out.println("Health OK: " + getLocalDateTime());
		return "Health OK: " + getLocalDateTime();
	}
	@RequestMapping(path = "/coiningestservices"
			, method = RequestMethod.POST
			, produces = {MediaType.APPLICATION_JSON_VALUE}
			, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public CryptoCoinUserToken postBody(@RequestBody CryptoCoinUserToken cryptoCoinUserToken) throws Exception{
		CompletableFuture<String> completableFuture = null;
		try {
			IngestProxyLambdaHandler.getContext().getLogger().log("IngresProxyUtility.postBody() :" + cryptoCoinUserToken);
			ingresProxyUtility.validateRequestPayload(cryptoCoinUserToken);
			IngestProxyLambdaHandler.getContext().getLogger().log(objectMapper.writeValueAsString(cryptoCoinUserToken));
			IngestProxyLambdaHandler.getContext().getLogger().log("asyncService.process Initiated");
			completableFuture = asyncService.process(cryptoCoinUserToken);
			IngestProxyLambdaHandler.getContext().getLogger().log("asyncService.process Completed");
		}catch(Exception ex){
			IngestProxyLambdaHandler.getContext().getLogger().log("IngresProxyUtility Exception Occurred: " + ex.getMessage());
		}
		finally{
			Optional.ofNullable(completableFuture
					.get(30000, TimeUnit.MILLISECONDS))
					//.orElseThrow(()-> new ProxyException(ProxyConstants.STREAM_WRITE_TIMEOUT_FAILED));
					.orElse(ProxyConstants.STREAM_WRITE_TIMEOUT_FAILED);
		}
		return cryptoCoinUserToken;
	}
}
