package org.kafka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.kafka.ConsumerProperties;
import org.kafka.domain.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;

@RestController
public class ConsumerController {
    private static final ObjectMapper om = new ObjectMapper();

    @GetMapping("/messages/{topic}")
    public ResponseEntity getMessages(
            @RequestParam(value = "bootstrapServer", required = false) String bootstrapServer,
            @PathVariable String topic
    ) {
        try {
            List<Message> messages = new ArrayList<>();
            ConsumerRecords<String, String> response = consumeMessage(bootstrapServer, topic);
            response.forEach(record -> {
                Message message = new Message();
                message.setKey(record.key());
                message.setVale(om.convertValue(record.value(), String.class));
                Arrays.asList(record.headers().toArray()).forEach(header -> {
                    Map<String, String> map = new HashMap<>();
                    map.put(header.key(), new String(header.value()));
                    message.setHeaders(map);
                });
                messages.add(message);
            });
            return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
        } catch (Exception e) {
            String failMessage = e.getMessage();
            return new ResponseEntity<String>(failMessage, HttpStatus.BAD_REQUEST);
        }

    }

    private ConsumerRecords<String, String> consumeMessage(String bootstrapServer, String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(new ConsumerProperties(bootstrapServer));
        consumer.subscribe(Collections.singleton(topic));
        ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(10));
        consumer.close();
        return messages;
    }
}
