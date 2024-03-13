package com.company.pricemanager.domain;

import org.junit.jupiter.api.Test;

import static com.company.pricemanager.domain.PriceMother.*;
import static org.junit.jupiter.api.Assertions.*;

public class PriceTest {
    @Test
    void testCreateNewPriceWithAllParamsSuccess() {
        // WHEN
        var price = create(brandId, productId, priceList, startDate, endDate, priority, priceValue, currency);
        // THEN
        assertNotNull(price);
        assertEquals(price.getBrandId(), brandId);
        assertEquals(price.getProductId(), productId);
        assertEquals(price.getPriceList(), priceList);
        assertEquals(price.getStartDate(), startDate);
        assertEquals(price.getEndDate(), endDate);
        assertEquals(price.getPriority(), priority);
        assertEquals(price.getPrice(), priceValue);
        assertEquals(price.getCurrency(), currency);
    }

    @Test
    void testCreateNewPriceWithoutNonNullParamsException() {
        assertThrows(NullPointerException.class, () ->
                create(null, productId, priceList, startDate, endDate, priority, priceValue, currency));
        assertThrows(NullPointerException.class, () ->
                create(brandId, null, priceList, startDate, endDate, priority, priceValue, currency));
        assertThrows(NullPointerException.class, () ->
                create(brandId, productId, null, startDate, endDate, priority, priceValue, currency));
        assertThrows(NullPointerException.class, () ->
                create(brandId, productId, priceList, startDate, endDate, null, priceValue, currency));
        assertThrows(NullPointerException.class, () ->
                create(brandId, productId, priceList, startDate, endDate, priority, null, currency));
        assertThrows(NullPointerException.class, () ->
                create(brandId, productId, priceList, startDate, endDate, priority, priceValue, null));
    }
}
