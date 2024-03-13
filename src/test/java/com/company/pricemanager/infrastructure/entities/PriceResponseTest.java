package com.company.pricemanager.infrastructure.entities;

import com.company.pricemanager.domain.PriceMother;
import com.company.pricemanager.infraestructure.entities.PriceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PriceResponseTest {
    @Test
    void testPriceResponseFromPriceSuccess() {
        // GIVEN
        var priceResponse = PriceResponseMother.createWithValues();
        var price = PriceMother.createWithValues();
        // WHEN
        var priceResponseFromPrice = PriceResponse.from(price);
        // THEN
        assertNotNull(priceResponseFromPrice);
        assertEquals(priceResponseFromPrice.getBrandId(), priceResponse.getBrandId());
        assertEquals(priceResponseFromPrice.getProductId(), priceResponse.getProductId());
        assertEquals(priceResponseFromPrice.getPriceList(), priceResponse.getPriceList());
        assertEquals(priceResponseFromPrice.getStartDate(), priceResponse.getStartDate());
        assertEquals(priceResponseFromPrice.getEndDate(), priceResponse.getEndDate());
        assertEquals(priceResponseFromPrice.getPrice(), priceResponse.getPrice());
    }
}
