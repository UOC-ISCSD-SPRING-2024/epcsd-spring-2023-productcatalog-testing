package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.application.rest.ItemRESTController;
import edu.uoc.epcsd.productcatalog.application.rest.ProductRESTController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// define a configuration class
@Configuration
// enable scanning of the controllers, but exclude the controllers that will not be used in the test
@ComponentScan(basePackages = "edu.uoc.epcsd.productcatalog.application.rest", excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ItemRESTController.class, ProductRESTController.class}))
public class CategoryControllerUnitTestConfig {

    public static void main(String[] args) {
        SpringApplication.run(CategoryControllerUnitTestConfig.class, args);
    }
}
