package com.blockchains.tokens.data;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.blockchains.stream.data.models.CoinUser;
import com.blockchains.stream.data.models.CreditData;
import com.blockchains.stream.data.models.CryptoCoinUserToken;
import com.blockchains.stream.data.models.CryptoCurrency;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.javafaker.Faker;
import com.blockchains.tokens.data.interfaces.BlockChainData;
import com.blockchains.tokens.data.interfaces.BlockChainDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }

    @Autowired
    BlockChainDataRepository blockChainDataRepository;

    @Bean
    public AmazonKinesis buildAmazonKinesis() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("<Your Access Key>", "<Your Secret Key>");
        return AmazonKinesisClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public CommandLineRunner startup() {
        System.out.println("startUp Initialized - 1!" + buildAmazonKinesis());
        BufferedWriter writer = null;

        List<PutRecordsRequestEntry> entries = IntStream.range(1, 101).mapToObj(ipSuffix -> {
            PutRecordsRequestEntry entry = new PutRecordsRequestEntry();
            CryptoCoinUserToken cryptoCoinUserToken2 = getCryptoCoinUserToken();

            String data = cryptoCoinUserToken2.toString();
            //stringBuffer.append();
            String blockTokensStrings = "NILAYAM KAMILA";
            System.out.println(blockTokensStrings + "::::" + data);
            StringBuffer stringBuffer = new StringBuffer();


            stringBuffer.append(data);
            try {
                //writer.write(data);
                //writer.newLine();
                //blockTokensStrings = objectMapper().writeValueAsString(cryptoCoinUserToken);
                //System.out.println(blockTokensStrings);
                //System.out.println((faker().currency().code() +"::" + faker().currency().name()));
                //System.out.println((faker().crypto().md5() +"::" + faker().crypto().sha512()));
                //System.out.println(objectMapper().writeValueAsString(cryptoCoinUserToken2));
                JsonNode  jsonTree = new ObjectMapper()
                        .readTree(new ObjectMapper()
                                .writerWithDefaultPrettyPrinter()
                                .writeValueAsString(cryptoCoinUserToken2));
                System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(cryptoCoinUserToken2));

                /*CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
                //JsonNode firstObject = jsonTree.elements().next();
                JsonNode firstObject = jsonTree;
                //csvSchemaBuilder.addColumn("cryptoCurrency");
                firstObject.fieldNames().forEachRemaining(fieldName -> {
                    csvSchemaBuilder.addColumn(fieldName);
                });
                CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
                CsvMapper csvMapper = new CsvMapper();
                csvMapper.configure(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS, true);
                csvMapper.writerFor(JsonNode.class)
                        .with(csvSchema)
                        .writeValue(new File("src/main/resources/CryptoCoinUserTokens.csv"), jsonTree);*/

            }catch(Exception ex){
                ex.printStackTrace();
            }
            try {
                String objectValue = objectMapper()
                        .writeValueAsString(cryptoCoinUserToken2);
                entry.setData(ByteBuffer.wrap(
                        (objectValue).getBytes()));
                entry.setPartitionKey("10001");
                CryptoCoinUserToken cryptoCoinUserToken3= new ObjectMapper().readValue(objectValue, CryptoCoinUserToken .class);
                System.out.println(cryptoCoinUserToken3.getSignature());
            }catch(Exception ex){
            ex.printStackTrace();
        }
            return entry;
        }).collect(Collectors.toList());
        try {
            writer = new BufferedWriter(new FileWriter(
                    "src/main/resources/CryptoCoinUserTokens2.csv"));
            writer.write("currencyId,openingValue,closingValue,highestValue,lowestValue,adjClose,volumeStocks,userIdentity,userFollowers,userFriends,userFollowers,userVerified,userLocation,userCreditRating,certifiedAuthorityTrusted,tokenTrusted");
            writer.newLine();
            for(int i=0; i<100; i++) {
                String data = getCryptoCoinUserToken().toString();
                writer.write(data);
                writer.newLine();
            }
            writer.close();
        }catch(Exception ex){

        }finally{

        }


        PutRecordsRequest createRecordsRequest = new PutRecordsRequest();
        createRecordsRequest.setStreamName("<Your Stream Name>");
        createRecordsRequest.setRecords(entries);

        buildAmazonKinesis().putRecords(createRecordsRequest);
        System.out.println("startUp Initialized - 1=2! Sent Success");
        return args -> {

            blockChainDataRepository.save(
                    new BlockChainData("Book A",
                            BigDecimal.valueOf(9.99),
                            new Date())
            );

            System.out.println("Database initialized!");

        };



    }

    private CreditData[] getUsersCreditData(int numberOfCreditInfo){
        CreditData[] creditData = new CreditData[numberOfCreditInfo];

        for (int i=0; i<numberOfCreditInfo; i++){
            creditData[i] = new CreditData(faker().business().creditCardType()
                    , faker().business().creditCardNumber()
                    ,faker().business().creditCardExpiry()
                    ,faker().commerce().productName());
        }
          return creditData;
    }

    /*
    String userIdentity,
                String userName,
                Date userCreated,
                long userFollowers,
                long userFriends,
                String userVerified,
                String userLocation,
                String userText,
                String userCreditRating,
                CreditData[] creditData
     */
    private CoinUser getCoinUser(){
        return new CoinUser(UUID.randomUUID().toString()
                , faker().name().name()
        , faker().date().birthday()
        ,Math.round(faker().random().nextDouble()* Math.ceil(100000))
        ,Math.round(faker().random().nextDouble()* Math.ceil(10000))
        , String.valueOf(faker().bool().bool())
        , faker().address().latitude() + ","+ faker().address().longitude()
        , faker().letterify("activity on account, favourite and activity")
        , Math.random()
        , getUsersCreditData(Math.max(0,10))
        );
    }

    /*
    String currencyId,
                      String code,
                      String name,
                      double openingValue,
                      double closingValue,
                      double highestValue,
                      double lowestValue,
                      double adjClose,
                      long volumeStocks,
                      String md5,
                      String sha512,
                      CoinUser currentUser
     */
    private CryptoCurrency getCryptoCurrency(){
        String randomId = UUID.randomUUID().toString();
        return new CryptoCurrency(randomId.substring(0, randomId.indexOf("-"))
                , faker().currency().code()
                , faker().currency().name()
                , faker().random().nextDouble()*Math.ceil(10000000)
                , faker().random().nextDouble()*Math.ceil(10000000)
                , faker().random().nextDouble()*Math.ceil(10000000)
                , faker().random().nextDouble()*Math.ceil(10000000)
                , faker().random().nextDouble()*Math.ceil(10000000)
                , Math.round(faker().random().nextDouble()* Math.ceil(1000000))
                , faker().crypto().md5()
                , faker().crypto().sha512()
                , getCoinUser());
    }

    /*
    CoinUser coinUser
            , CryptoCurrency cryptoCurrency
            , double assignedId
            , String certifiedAuthority
            , boolean certifiedAuthorityTrusted
            , boolean tokenTrusted
            , String signature
     */
    private CryptoCoinUserToken getCryptoCoinUserToken(){
        return new CryptoCoinUserToken(getCryptoCurrency()
        , Math.abs(1230000.0)
        , faker().company().name()
        , faker().bool().bool()
        , faker().bool().bool()
        , faker().app().author());
    }

}