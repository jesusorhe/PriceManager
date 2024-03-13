package com.company.pricemanager.infrastructure.entities;

import com.company.pricemanager.infraestructure.entities.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;

public class PriceResponseMother {
    public static final Long brandId = 1L;
    public static final Long productId = 1L;
    public static final Integer priceList = 1;
    public static final LocalDateTime startDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
    public static final LocalDateTime endDate = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
    public static final BigDecimal priceValue = BigDecimal.valueOf(35.5);

    public static PriceResponse create(Long brandId,
                                       Long productId,
                                       Integer priceList,
                                       LocalDateTime startDate,
                                       LocalDateTime endDate,
                                       BigDecimal priceValue) {
        return new PriceResponse(brandId, productId, priceList, startDate, endDate, priceValue);
    }
    public static PriceResponse createWithValues() {
        return create(brandId, productId, priceList, startDate, endDate, priceValue);
    }
}
