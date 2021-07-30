# Kafka producer / Kafka consumer

Easy create templates using POSTMAN

Curl example to produce a message: </br>
`curl --location --request POST 'http://localhost:5000/messages/TOPIC_NAME/SOME_KEY?bootstrapServer=PLAINTEXT://localhost:9092' \
--header 'Content-Type: application/json' \
--data-raw '{
"test": "test",
"hello": "world"
}'`

</br></br>

Curl example to consume messages: </br>
`curl --location --request GET 'localhost:5000/messages/TEST_TOPIC?bootstrapServer=PLAINTEXT://localhost:9092'`

</br></br>
`bootstrapServer` - is not required query param. Default `bootstrapServer` is `PLAINTEXT://localhost:9092`

Run in docker using
`docker pull heorhiizhukov/kafka-manager:latest`
