# Block Chain Stream Processor

##Article links
* git clone https://github.com/nilayamkamila/block-chain-intelligent-machine.git/
* mvn clean install


## Technologies used:
* Docker
* awslocal(localstack)
* aws lambda

##Docker Install:
```
https://docs.docker.com/desktop/setup/install/mac-install/
```
##To Install awslocal
```
pip install awscli-local
```

## How to run it (Local)
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
awslocal lambda create-function --function-name block-chain-stream-processor-lambda-function --runtime java17 --handler com.blockchains.stream.processor.LambdaEntry::handler --role arn:aws:iam::000000000000:role/lambda-role --zip-file fileb://./target/block-chain-stream-processor-1.0-aws.jar
		   
awslocal lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload file://src/main/resources/input.txt output.txt

Next Time Update Lambda Function:
awslocal lambda update-function-code --function-name  block-chain-stream-processor-lambda-function --zip-file fileb://./target/block-chain-stream-processor-1.0-aws.jar

awslocal lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload file://src/main/resources/input.txt output.txt
```

## How to run it (AWS)
```
First Time Create Lambda Function:
aws lambda create-function --function-name block-chain-stream-processor-lambda-function --runtime java17 --handler com.blockchains.stream.processor.LambdaEntry::handler --role arn:aws:iam::337550871092:role/AWS_SERVICE_ROLE --zip-file fileb://./target/block-chain-stream-processor-1.0-aws.jar
awslocal lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload file://input.txt output.txt
awslocal lambda update-function-configuration --function-name  block-chain-stream-processor-lambda-function --handler com.blockchains.stream.processor.LambdaEntry::handler

Next Time Update Lambda Function:
aws lambda update-function-code --function-name  block-chain-stream-processor-lambda-function --zip-file fileb://./target/block-chain-stream-processor-1.0-aws.jar
aws lambda update-function-configuration --function-name  block-chain-stream-processor-lambda-function --handler com.blockchains.stream.processor.LambdaEntry::handler
aws lambda invoke --function-name block-chain-stream-processor-lambda-function --cli-binary-format raw-in-base64-out --payload file://src/main/resources/input.txt output.txt
```
###References:
- https://docs.aws.amazon.com/lambda/latest/dg/with-kinesis-example.html
- https://www.baeldung.com/java-aws-lambda