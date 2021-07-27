package org.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;
import java.util.UUID;

public class ConsumerProperties extends Properties {
    private String bootstrapServer = "PLAINTEXT://localhost:9092";

    public ConsumerProperties(String bootstrapServer) {
        if (null != bootstrapServer && !bootstrapServer.isBlank()) {
            this.bootstrapServer = bootstrapServer;
        }
        this.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServer);
        this.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-manager-consumer" + UUID.randomUUID());
        this.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        this.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        this.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10000");
        this.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, ConsumerConfig.DEFAULT_FETCH_MAX_BYTES * 5);

    }
}
