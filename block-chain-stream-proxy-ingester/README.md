# Block Chain Stream Proxy Ingester

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

$ cd block-token-stream-proxy-ingester

$ docker run \
--rm -it \
-p 127.0.0.1:4566:4566 \
-v /var/run/docker.sock:/var/run/docker.sock \
-v /Users/<username>/IdeaProjects/block-chain-intelligent-machine/block-token-stream-proxy-ingester/target:/opt/code/localstack/target \
localstack/localstack

First Time Create Lambda Function:
awslocal lambda create-function --function-name block-chain-stream-proxy-ingester-aws --runtime java17 --handler com.blockchains.ingest.proxy.IngestProxyLambdaHandler::handleRequest --role arn:aws:iam::000000000000:role/lambda-role --zip-file fileb://./target/block-chain-stream-proxy-ingester-1.0-SNAPSHOT.jar --timeout 300
awslocal lambda invoke --function-name block-chain-stream-proxy-ingester-aws --cli-binary-format raw-in-base64-out --payload file://src/main/resources/alb-event.json output.txt
awslocal lambda invoke --function-name block-chain-stream-proxy-ingester-aws --cli-binary-format raw-in-base64-out --payload file://src/main/resources/apigw-proxy-event.json output.txt
awslocal lambda update-function-code --function-name  block-chain-stream-proxy-ingester-aws --zip-file fileb://./target/block-chain-stream-proxy-ingester-1.0-SNAPSHOT.jar
```

## How to run it (AWS)
```
aws lambda create-function --function-name block-chain-stream-proxy-ingester-aws --runtime java17 --handler com.blockchains.ingest.proxy.IngestProxyLambdaHandler::handleRequest --role arn:aws:iam::337550871092:role/AWS_SERVICE_ROLE --zip-file fileb://./target/block-chain-stream-proxy-ingester-1.0-SNAPSHOT.jar --timeout 300
aws lambda invoke --function-name block-chain-stream-proxy-ingester-aws --cli-binary-format raw-in-base64-out --payload file://src/main/resources/alb-event.json output.txt
aws lambda invoke --function-name block-chain-stream-proxy-ingester-aws --cli-binary-format raw-in-base64-out --payload file://src/main/resources/apigw-proxy-event.json output.txt
aws lambda update-function-code --function-name  block-chain-stream-proxy-ingester-aws --zip-file fileb://./target/block-chain-stream-proxy-ingester-1.0-SNAPSHOT.jar
```
###ALB Registration :
```
aws elbv2 create-target-group --name albtargets-proxy-ingester-aws --target-type lambda

aws lambda add-permission \
--function-name block-chain-stream-proxy-ingester-aws \
--statement-id load-balancer \
--principal elasticloadbalancing.amazonaws.com \
--action lambda:InvokeFunction \
--source-arn arn:aws:elasticloadbalancing:us-east-1:337550871092:targetgroup/albtargets-proxy-ingester-aws/4530c6578b2e9c6a
aws elbv2 register-targets \
--target-group-arn arn:aws:elasticloadbalancing:us-east-1:337550871092:targetgroup/albtargets-proxy-ingester-aws/4530c6578b2e9c6a \
--targets Id=arn:aws:lambda:us-east-1:337550871092:function:block-chain-stream-proxy-ingester-aws

aws elbv2 modify-target-group-attributes --target-group-arn arn:aws:elasticloadbalancing:us-east-1:337550871092:targetgroup/albtargets-proxy-ingester-aws/4530c6578b2e9c6a --attributes Key=lambda.multi_value_headers.enabled,Value=true

aws elbv2 create-load-balancer --name alb-proxy-ingester-aws \
--subnets subnet-0aeafdd11dc32c1e1 subnet-075c16114dcef8324 --security-groups sg-b01457d7

aws elbv2 create-listener --load-balancer-arn arn:aws:elasticloadbalancing:us-east-1:337550871092:loadbalancer/app/alb-proxy-ingester-aws/bd4937876ed721ce \
--protocol HTTP --port 80  \
--default-actions Type=forward,TargetGroupArn=arn:aws:elasticloadbalancing:us-east-1:337550871092:targetgroup/albtargets-proxy-ingester-aws/4530c6578b2e9c6a
```