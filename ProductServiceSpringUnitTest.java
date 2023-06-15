package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
public class ProductServiceSpringUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void whenValidId_thenProductShouldBeFound() {
        Product product = Product.builder().id(123L).build();
        product.setName("Photo Camera");
        product.setDescription("Photo camera of Samsung");
        product.setDailyPrice(150.0);
        product.setBrand("Samsung");
        product.setModel("35C");
        product.setCategoryId(1L);

        Long productId = product.getId();

        Mockito.when(productRepository.findProductById(productId)).thenReturn(Optional.of(product));

        Optional<Product> productFound = productService.findProductById(productId);

        assertThat(productFound.isPresent()).isTrue();
        assertThat(productFound.get().getId()).isEqualTo(productId);
    }

    @Test
    public void whenInvalidId_thenProductShouldNotBeFound() {
        Long productId = 5555L;

        Mockito.when(productRepository.findProductById(productId)).thenReturn(null);
        Optional<Product> productFound = productService.findProductById(productId);

        assertThat(productFound).isNull();
    }

}