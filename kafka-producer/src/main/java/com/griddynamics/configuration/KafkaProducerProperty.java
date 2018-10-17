package com.griddynamics.configuration;

public enum KafkaProducerProperty {
    BOOTSTRAP_SERVERS("bootstrap.servers"),
    ACKS("acks"),
    BATCH_SIZE("batch.size"),
    LINGER("linger.ms"),
    BUFFER_MEMORY("buffer.memory"),
    KEY_SERIALIZER("key.serializer"),
    VALUE_SERIALIZER("value.serializer");

    private static final String PROPERTY_PREF = "kafka.producer.";
    private String kafkaPropertyName;
    private String kafkaProducerPropertyName;

    KafkaProducerProperty(String kafkaPropertyName) {
        this.kafkaPropertyName = kafkaPropertyName;
        this.kafkaProducerPropertyName = PROPERTY_PREF + kafkaPropertyName;
    }

    public String getKafkaPropertyName() {
        return kafkaPropertyName;
    }

    public String getKafkaProducerPropertyName() {
        return kafkaProducerPropertyName;
    }
}
