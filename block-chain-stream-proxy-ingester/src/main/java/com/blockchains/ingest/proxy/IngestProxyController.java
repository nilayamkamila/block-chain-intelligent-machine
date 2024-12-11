package com.blockchains.ingest.proxy;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.ingest.proxy.services.IAsyncService;
import com.blockchains.ingest.proxy.utils.IngresProxyUtility;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		ingresProxyUtility.validateRequestPayload(cryptoCoinUserToken);
		System.out.println(objectMapper.writeValueAsString(cryptoCoinUserToken));
		asyncService.process(cryptoCoinUserToken);
		return cryptoCoinUserToken;
	}
}
