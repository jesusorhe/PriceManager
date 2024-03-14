package com.company.pricemanager.domain;

import com.company.pricemanager.domain.models.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class PriceMother {
    public static final Long brandId = 1L;
    public static final Long productId = 1L;
    public static final Integer priceList = 1;
    public static final LocalDateTime startDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
    public static final LocalDateTime endDate = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
    public static final Integer priority = 0;
    public static final BigDecimal priceValue = BigDecimal.valueOf(35.5);
    public static final String currency = "EUR";

    public static Price create(final Long brandId,
                               final Long productId,
                               final Integer priceList,
                               final LocalDateTime startDate,
                               final LocalDateTime endDate,
                               final Integer priority,
                               final BigDecimal price,
                               final String currency) {
        return Price.of(brandId, productId, priceList, startDate, endDate, priority, price, currency);
    }

    public static Price createWithValues() {
        return Price.of(brandId, productId, priceList, startDate, endDate, priority, priceValue, currency);
    }
}
