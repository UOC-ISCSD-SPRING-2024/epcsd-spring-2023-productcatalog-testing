package edu.uoc.epcsd.productcatalog;

import edu.uoc.epcsd.productcatalog.domain.Item;
import edu.uoc.epcsd.productcatalog.domain.ItemStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ItemUnitTest {

    @Test
    void item_status_should_be_coherent() {
        Item item = Item.builder().serialNumber("CEOSR3xv4miWq8C9TCf5").build();
        item.setStatus(ItemStatus.NON_OPERATIONAL);
        assertThat(item.getStatus()).isEqualTo(ItemStatus.NON_OPERATIONAL);
    }
}