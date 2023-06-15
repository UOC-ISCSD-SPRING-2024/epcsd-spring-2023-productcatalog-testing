package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.ItemRepositoryImpl;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.ProductRepositoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ItemRepositoryImpl.class, ProductRepositoryImpl.class}))
public class CategoryRepositoryIntegrationTestConfig {
}
