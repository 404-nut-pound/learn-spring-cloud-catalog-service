package io.hskim.learnspringcloudcatalogservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class CatalogDto {

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @ToString
  @EqualsAndHashCode
  public static class CatalogSearchDto {

    @Builder.Default
    private String productName = "";
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @ToString
  @EqualsAndHashCode
  public static class CatalogRequestDto {

    @NotBlank(groups = { ValidatedPatchGroup.class })
    private String catalogId;

    @NotBlank(groups = { ValidatedPostGroup.class, ValidatedPatchGroup.class })
    @Size(
      max = 100,
      groups = { ValidatedPostGroup.class, ValidatedPatchGroup.class }
    )
    private String productName;

    @PositiveOrZero(
      groups = { ValidatedPostGroup.class, ValidatedPatchGroup.class }
    )
    private int stock;

    @PositiveOrZero(
      groups = { ValidatedPostGroup.class, ValidatedPatchGroup.class }
    )
    private int unitPrice;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  @ToString
  @EqualsAndHashCode
  public static class CatalogResponseDto {

    private String catalogId;

    private String productName;

    private int stock;

    private int unitPrice;

    private String updatedAt;
  }

  public interface ValidatedPostGroup {}

  public interface ValidatedPatchGroup {}
}
