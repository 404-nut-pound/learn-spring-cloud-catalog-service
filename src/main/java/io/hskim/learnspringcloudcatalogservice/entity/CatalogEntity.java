package io.hskim.learnspringcloudcatalogservice.entity;

import io.hskim.learnspringcloudcatalogservice.dto.CatalogDto.CatalogResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "ba_catalog")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter(value = AccessLevel.PROTECTED)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = false, exclude = {})
public class CatalogEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  @Comment(value = "상품 ID")
  private UUID catalogId;

  @Column(length = 100, nullable = false)
  @Comment(value = "상품 이름")
  private String productName;

  @Setter
  @Comment(value = "상품 재고")
  private int stock;

  @Setter
  @Comment(value = "상품 단가")
  private int unitPrice;

  @CreatedDate
  @Column(nullable = false)
  @ColumnDefault(value = "now()")
  @Comment(value = "수정일시")
  private LocalDateTime updatedAt;

  public void setProductName(String productName) {
    if (StringUtils.hasText(productName)) {
      this.productName = productName;
    }
  }

  public CatalogResponseDto toDto() {
    return CatalogResponseDto
      .builder()
      .catalogId(this.catalogId.toString())
      .productName(this.productName)
      .stock(this.stock)
      .unitPrice(this.unitPrice)
      .updatedAt(
        this.updatedAt != null
          ? this.updatedAt.format(
              DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            )
          : ""
      )
      .build();
  }
}
