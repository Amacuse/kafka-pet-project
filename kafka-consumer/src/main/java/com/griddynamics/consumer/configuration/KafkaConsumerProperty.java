package com.griddynamics.consumer.configuration;

public enum KafkaConsumerProperty {
    BOOTSTRAP_SERVERS("bootstrap.servers"),
    GROUP_ID("group.id"),
    AUTO_COMMIT_ENABLE("enable.auto.commit"),
    AUTO_COMMIT_INTERVAL("auto.commit.interval.ms"),
    KEY_DESERIALIZER("key.deserializer"),
    VALUE_DESERIALIZER("value.deserializer");

    private static final String PROPERTY_PREF = "kafka.consumer.";
    private String kafkaPropertyName;
    private String kafkaConsumerPropertyName;

    KafkaConsumerProperty(String kafkaPropertyName) {
        this.kafkaPropertyName = kafkaPropertyName;
        this.kafkaConsumerPropertyName = PROPERTY_PREF + kafkaPropertyName;
    }

    public String getKafkaPropertyName() {
        return kafkaPropertyName;
    }

    public String getKafkaConsumerPropertyName() {
        return kafkaConsumerPropertyName;
    }
}
