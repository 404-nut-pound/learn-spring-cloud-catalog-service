package io.hskim.learnspringcloudcatalogservice.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> propertiesMap = new ConcurrentHashMap<>();
    propertiesMap.put(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
      "127.0.0.1:9092"
    );
    propertiesMap.put(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupId");
    propertiesMap.put(
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
      StringDeserializer.class
    );
    propertiesMap.put(
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
      StringDeserializer.class
    );

    return new DefaultKafkaConsumerFactory<>(propertiesMap);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();

    concurrentKafkaListenerContainerFactory.setConsumerFactory(
      consumerFactory()
    );

    return concurrentKafkaListenerContainerFactory;
  }
}
