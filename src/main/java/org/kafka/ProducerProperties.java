package org.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;

public class ProducerProperties extends Properties {
    private String bootstrapServer = "PLAINTEXT://localhost:9092";

    public ProducerProperties(String bootstrapServer) {
        if (null != bootstrapServer && !bootstrapServer.isBlank()) {
            this.bootstrapServer = bootstrapServer;
        }
        this.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.bootstrapServer);
        this.put(ProducerConfig.CLIENT_ID_CONFIG, "kafka-manager" + UUID.randomUUID());
        this.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    }
}
