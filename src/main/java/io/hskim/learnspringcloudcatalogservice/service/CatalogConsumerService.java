package io.hskim.learnspringcloudcatalogservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.hskim.learnspringcloudcatalogservice.entity.CatalogEntity;
import io.hskim.learnspringcloudcatalogservice.repo.CatalogRepo;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogConsumerService {

  private final CatalogRepo catalogRepo;

  private final JsonMapper jsonMapper = JsonMapper.builder().build();

  @KafkaListener(topics = { "example-catalog-topic" })
  public void updateStock(String kafkaMessage) {
    try {
      Map<String, Object> kafkaMessageMap = jsonMapper.readValue(
        kafkaMessage,
        new TypeReference<Map<String, Object>>() {}
      );

      CatalogEntity findCatalogEntity = catalogRepo
        .findById(UUID.fromString(kafkaMessageMap.get("productId").toString()))
        .orElseThrow();

      findCatalogEntity.setStock(
        findCatalogEntity.getStock() -
        Integer.parseInt(kafkaMessageMap.getOrDefault("stock", "0").toString())
      );
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}
