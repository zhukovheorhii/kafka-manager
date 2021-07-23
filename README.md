# Kafka manager / Kafka producer

Curl example to produce a message: </br>
`curl --location --request POST 'http://localhost:5000/messages/TOPIC_NAME/SOME_KEY?bootstrapServer=PLAINTEXT://localhost:9092' \
--header 'Content-Type: application/json' \
--data-raw '{
"test": "test",
"hello": "world"
}'`

Default BootstrapServer is `PLAINTEXT://localhost:9092`

Easy create templates using POSTMAN
