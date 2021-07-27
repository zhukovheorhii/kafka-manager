package org.kafka.controller;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.kafka.ProducerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProducerController {
    @PostMapping("/messages/{topic}/{key}")
    public ResponseEntity newMessage(
            @RequestParam(value = "bootstrapServer", required = false) String bootstrapServer,
            @PathVariable String topic,
            @PathVariable String key, @RequestBody String value
    ) {
        String response = "SUCCESS";
        try {
            produceMessage(bootstrapServer, topic, key, value);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }


    private void produceMessage(String bootstrapServer, String topic, String key, String value) {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(new ProducerProperties(bootstrapServer));
        ProducerRecord<String, String> data = new ProducerRecord<String, String>(topic, key, value);
        data.headers().add("contentType", "application/json".getBytes());
        producer.send(data);
        producer.close();
    }
}
