package io.hskim.learnspringcloudcatalogservice.controller;

import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogRequestDto;
import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogResponseDto;
import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogSearchDto;
import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.ValidatedPostGroup;
import io.hskim.learnspringcloudcatalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog")
@RequiredArgsConstructor
public class CatalogController {

  private final CatalogService catalogService;

  @PostMapping
  public CatalogResponseDto postCatalog(
    @Validated(
      value = ValidatedPostGroup.class
    ) @RequestBody CatalogRequestDto catalogRequestDto
  ) {
    return catalogService.postCatalog(catalogRequestDto);
  }

  @GetMapping
  public Page<CatalogResponseDto> getCatalogList(
    CatalogSearchDto catalogSearchDto,
    Pageable pageable
  ) {
    return catalogService.getCatalogList(catalogSearchDto, pageable);
  }

  @GetMapping(value = "/{catalogId}")
  public CatalogResponseDto getCatalog(@PathVariable String catalogId) {
    return catalogService.getCatalog(catalogId).toDto();
  }
}
