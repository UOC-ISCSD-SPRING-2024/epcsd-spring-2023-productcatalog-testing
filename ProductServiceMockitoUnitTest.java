package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Product;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ItemService;
import edu.uoc.epcsd.productcatalog.domain.service.ProductService;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceMockitoUnitTest {

    private final Long PHOTO_CATEGORY_ID = 1L;
    private final Long EXISTING_PRODUCT_ID = 123L;
    private final Long NON_EXISTING_PRODUCT_ID = 456L;

    @Mock
    private ItemService itemService;

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    void initService() {
        productService = new ProductServiceImpl(itemService, productRepository);
    }

    @Test
    void should_find_product_by_id() {

        Product existingProduct = Product.builder().name("Canon EOS R3").description("Cámara de fotos Canon EOS R3")
                .dailyPrice(100D).brand("Canon").model("EOS R3")
                .categoryId(PHOTO_CATEGORY_ID).id(EXISTING_PRODUCT_ID).build();
        when(productRepository.findProductById(EXISTING_PRODUCT_ID)).thenReturn(Optional.of(existingProduct));
        Optional<Product> retrievedProduct = productService.findProductById(EXISTING_PRODUCT_ID);

        assertThat(retrievedProduct.isPresent()).isTrue();
        assertEquals(existingProduct, retrievedProduct.get());
    }

    @Test
    void should_not_find_product_by_id() {

        when(productRepository.findProductById(NON_EXISTING_PRODUCT_ID)).thenReturn(Optional.empty());
        Optional<Product> retrievedProduct = productService.findProductById(NON_EXISTING_PRODUCT_ID);

        assertThat(retrievedProduct.isEmpty()).isTrue();
    }
}
