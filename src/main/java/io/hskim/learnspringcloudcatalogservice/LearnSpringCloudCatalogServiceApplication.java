package io.hskim.learnspringcloudcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LearnSpringCloudCatalogServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(
      LearnSpringCloudCatalogServiceApplication.class,
      args
    );
  }
}
