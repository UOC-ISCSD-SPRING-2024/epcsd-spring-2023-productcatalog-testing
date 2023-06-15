package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.application.rest.CategoryRESTController;
import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.domain.repository.ProductRepository;
import edu.uoc.epcsd.productcatalog.domain.service.CategoryService;
import edu.uoc.epcsd.productcatalog.domain.service.CategoryServiceImpl;
import edu.uoc.epcsd.productcatalog.domain.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;



import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryRESTController.class)
@ContextConfiguration(classes = CategoryControllerUnitTestConfig.class)
class CategoryControllerUnitTest {
    private static final String PHOTO_PRODUCTS = "Photo products";
    private static final String VIDEO_PRODUCTS = "Video products";
    private static final String REST_CATEGORIES_PATH = "/categories";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    void find_categories_should_return_all() throws Exception {

        Category categoryOne = Category.builder().name(PHOTO_PRODUCTS).build();
        Category categoryTwo = Category.builder().name(VIDEO_PRODUCTS).build();
        List<Category> categories = Arrays.asList(categoryOne, categoryTwo);

        when(categoryService.findAllCategories()).thenReturn(categories);

        mockMvc.perform(get(REST_CATEGORIES_PATH).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is(PHOTO_PRODUCTS)))
                .andExpect(jsonPath("$[1].name", is(VIDEO_PRODUCTS)));
    }
}