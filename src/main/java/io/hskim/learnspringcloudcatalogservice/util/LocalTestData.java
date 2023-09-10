package io.hskim.learnspringcloudcatalogservice.util;

import io.hskim.learnspringcloudcatalogservice.entity.CatalogEntity;
import io.hskim.learnspringcloudcatalogservice.repo.CatalogRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LocalTestData {

  private final InitLocalTestDataService initLocalTestDataService;

  @PostConstruct
  void init() {
    initLocalTestDataService.init();
  }

  @Component
  @Transactional
  @RequiredArgsConstructor
  public static class InitLocalTestDataService {

    private final CatalogRepo catalogRepo;

    public void init() {
      catalogRepo
        .findByProductName("일본 여행 상품")
        .orElseGet(() ->
          catalogRepo.save(
            CatalogEntity
              .builder()
              .productName("일본 여행 상품")
              .stock(100)
              .unitPrice(1000000)
              .build()
          )
        );

      catalogRepo
        .findByProductName("필리핀 여행 상품")
        .orElseGet(() ->
          catalogRepo.save(
            CatalogEntity
              .builder()
              .productName("필리핀 여행 상품")
              .stock(120)
              .unitPrice(1200000)
              .build()
          )
        );

      catalogRepo
        .findByProductName("대만 여행 상품")
        .orElseGet(() ->
          catalogRepo.save(
            CatalogEntity
              .builder()
              .productName("대만 여행 상품")
              .stock(150)
              .unitPrice(1500000)
              .build()
          )
        );
    }
  }
}
