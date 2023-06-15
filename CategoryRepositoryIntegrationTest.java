package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Category;
import edu.uoc.epcsd.productcatalog.domain.repository.CategoryRepository;
import edu.uoc.epcsd.productcatalog.infrastructure.repository.jpa.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = CategoryRepositoryIntegrationTestConfig.class)
public class CategoryRepositoryIntegrationTest {

    private final String CATEGORY_NAME = "Test category";

    private final String DESCRIPTION = "Test description";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenFindByName_thenReturnCategory() {

        Category category = Category.builder().name(CATEGORY_NAME).description(DESCRIPTION).build();
        CategoryEntity categoryEntity = CategoryEntity.fromDomain(category);
        entityManager.persistAndFlush(categoryEntity);
        Category fromDb = categoryRepository.findCategoryById(categoryEntity.getId()).get();
        assertThat(fromDb.equals(category));
    }
}
