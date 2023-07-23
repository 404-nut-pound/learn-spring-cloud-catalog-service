package io.hskim.learnspringcloudcatalogservice.service;

import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogRequestDto;
import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogResponseDto;
import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogSearchDto;
import io.hskim.learnspringcloudcatalogservice.entity.CatalogEntity;
import io.hskim.learnspringcloudcatalogservice.repo.CatalogRepo;
import jakarta.ws.rs.NotFoundException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CatalogService {

  private final CatalogRepo catalogRepo;

  public CatalogResponseDto postCatalog(CatalogRequestDto catalogRequestDto) {
    return catalogRepo
      .save(
        CatalogEntity
          .builder()
          .productName(catalogRequestDto.getProductName())
          .stock(catalogRequestDto.getStock())
          .unitPrice(catalogRequestDto.getUnitPrice())
          .build()
      )
      .toDto();
  }

  public Page<CatalogResponseDto> getCatalogList(
    CatalogSearchDto catalogSearchDto,
    Pageable pageable
  ) {
    return catalogRepo
      .findAll(
        Example.of(
          CatalogEntity
            .builder()
            .productName(catalogSearchDto.getProductName())
            .build(),
          ExampleMatcher
            .matchingAny()
            .withMatcher("productName", matcher -> matcher.contains())
        ),
        pageable
      )
      .map(CatalogEntity::toDto);
  }

  public CatalogEntity getCatalog(String catalogId) {
    return catalogRepo
      .findById(UUID.fromString(catalogId))
      .orElseThrow(() -> new NotFoundException());
  }
}
