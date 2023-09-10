package io.hskim.learnspringcloudcatalogservice.repo;

import io.hskim.learnspringcloudcatalogservice.entity.CatalogEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepo extends JpaRepository<CatalogEntity, UUID> {
  Optional<CatalogEntity> findByProductName(String productName);
}
