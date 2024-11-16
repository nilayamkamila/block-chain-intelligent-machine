# Spring Boot CommandLineRunner Example

Article links
* mvn clean install(https://github.com/nilayamkamila/block-chain-intelligent-machine.git/)
* (https://github.com/nilayamkamila/block-chain-intelligent-machine.git/)

## Technologies used:
Docker Install:
https://docs.docker.com/desktop/setup/install/mac-install/

To Install awslocal
pip install awscli-local

Lambda Function Reference: https://www.baeldung.com/java-aws-lambda

## How to run it
```
$ git clone  https://github.com/nilayamkamila/block-chain-intelligent-machine.git

$ cd block-chain-intelligent-machine

$ mvn clean install

$ cd block-token-stream-processor

$ docker run \
--rm -it \
-p 127.0.0.1:4566:4566 \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /Users/<username>/IdeaProjects/block-chain-intelligent-machine/block-chain-stream-processor/target:/opt/code/localstack/target \
localstack/localstack

First Time Create Lambda Function:
awslocal lambda create-function --function-name block-chain-stream-processor-lambda-function --runtime java17 --handler com.blockchains.stream.processor.handlers.LambdaHandler --role arn:aws:iam::000000000000:role/lambda-role --zip-file fileb://./target/block-chain-stream-processor-1.0.jar
		   
awslocal lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload '{ "name":  "Name", "source": "How do I view articles ad-free and in dark mode on Baeldung?", "data": "Hello"}' output.txt

Next Time Update Lambda Function:
awslocal lambda update-function-code --function-name  block-chain-stream-processor-lambda-function --zip-file fileb://./target/block-chain-stream-processor-1.0.jar

awslocal lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload '{ "name":  "Nilayam Kamila", "source": "How do I view articles ad-free and in dark mode on Baeldung?", "data": "Hello"}' output.txt
```


