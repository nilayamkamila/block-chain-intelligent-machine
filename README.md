# Block Chain Intelligent Machine(In Progress)

Article links
* mvn clean install(https://github.com/nilayamkamila/block-chain-stream-process.git/)
* (https://github.com/nilayamkamila/block-chain-stream-process.git/)

## Technologies used:
* Spring Boot 3.1.2
* Spring Data JPA
* H2 in-memory database
* Java 17
* Kinesis Stream

## How to run it
```

$ git clone  https://github.com/nilayamkamila/block-chain-stream-process.git

$ cd block-token-stream-process

$ ./mvnw spring-boot:run

```
```
Create a Kinesis Steam and provide the name 
Refer: createRecordsRequest.setStreamName("<Your Stream Name>");
Provide your access key and secret key in StartApplication.java
Refer: BasicAWSCredentials awsCredentials = new BasicAWSCredentials("<Your Access Key>", "<Your Secret Key>");;

```


