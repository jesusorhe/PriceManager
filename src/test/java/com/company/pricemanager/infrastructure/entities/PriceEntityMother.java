package com.company.pricemanager.infrastructure.entities;

import com.company.pricemanager.infraestructure.entities.PriceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;

public class PriceEntityMother {
    public static final UUID id = UUID.fromString("67e2f2ed-4fe9-43b0-9797-e3560fd494e5");
    public static final Long brandId = 1L;
    public static final Long productId = 1L;
    public static final Integer priceList = 1;
    public static final LocalDateTime startDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
    public static final LocalDateTime endDate = LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59);
    public static final Integer priority = 0;
    public static final BigDecimal priceValue = BigDecimal.valueOf(35.5);
    public static final String currency = "EUR";

    public static PriceEntity create(final UUID id,
                                     final Long brandId,
                                     final Long productId,
                                     final Integer priceList,
                                     final LocalDateTime startDate,
                                     final LocalDateTime endDate,
                                     final Integer priority,
                                     final BigDecimal price,
                                     final String currency) {
        return new PriceEntity(id, brandId, productId, priceList, startDate, endDate, priority, price, currency);
    }

    public static PriceEntity createWithValues() {
        return create(id, brandId, productId, priceList, startDate, endDate, priority, priceValue, currency);
    }
}
