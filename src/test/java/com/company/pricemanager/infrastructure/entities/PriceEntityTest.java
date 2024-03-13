package com.company.pricemanager.infrastructure.entities;

import com.company.pricemanager.domain.PriceMother;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.company.pricemanager.domain.PriceMother.*;
import static com.company.pricemanager.domain.PriceMother.currency;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PriceEntityTest {

    @Test
    void testPriceEntityToPriceSuccess() {
        // GIVEN
        var priceEntity = PriceEntityMother.createWithValues();
        var price = PriceMother.createWithValues();
        // WHEN
        var priceFromEntity = priceEntity.toPrice();
        // THEN
        assertNotNull(priceFromEntity);
        assertEquals(price.getBrandId(), priceFromEntity.getBrandId());
        assertEquals(price.getProductId(), priceFromEntity.getProductId());
        assertEquals(price.getPriceList(), priceFromEntity.getPriceList());
        assertEquals(price.getStartDate(), priceFromEntity.getStartDate());
        assertEquals(price.getEndDate(), priceFromEntity.getEndDate());
        assertEquals(price.getPriority(), priceFromEntity.getPriority());
        assertEquals(price.getPrice(), priceFromEntity.getPrice());
        assertEquals(price.getCurrency(), priceFromEntity.getCurrency());
    }
}
