# Kafka producer / Kafka consumer

Easy create templates using POSTMAN

Curl example to produce a message: </br>
`curl --location --request POST 'http://localhost:5000/topics/TOPIC_NAME/keys/SOME_KEY?bootstrapServer=PLAINTEXT://localhost:9092' \
--header 'Content-Type: application/json' \
--data-raw '{
"headers": {
"header1": "value",
"header2": "value"
},
"body": "SOME_STRING"
}'`

</br></br>

Curl example to consume messages: </br>
`curl --location --request GET 'localhost:5000/topics/SOME_TOPIC?bootstrapServer=PLAINTEXT://localhost:9092'`

</br></br>
`bootstrapServer` - is not required query param. Default `bootstrapServer` is `PLAINTEXT://localhost:9092`

Run in docker using
`docker pull heorhiizhukov/kafka-manager:latest`

docker-compose example:

````
version: '1.0'
services:
  kafkamanager:
    image: heorhiizhukov/kafka-manager:1.0-SNAPSHOT
    ports:
      - "5000:5000"
````
